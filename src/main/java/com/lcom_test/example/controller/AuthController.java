package com.lcom_test.example.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lcom_test.example.config.JwtUtils;
import com.lcom_test.example.domain.Board;
import com.lcom_test.example.domain.Cart;
import com.lcom_test.example.domain.Comment;
import com.lcom_test.example.domain.Heart;
import com.lcom_test.example.domain.HomeImg;
import com.lcom_test.example.domain.Images;
import com.lcom_test.example.domain.Order;
import com.lcom_test.example.domain.Pagination;
import com.lcom_test.example.domain.Product;
import com.lcom_test.example.domain.User;
import com.lcom_test.example.domain.UserInfo;
import com.lcom_test.example.request.JoinRequest;
import com.lcom_test.example.request.LoginRequest;
import com.lcom_test.example.response.JwtResponse;
import com.lcom_test.example.response.ListResponse;
import com.lcom_test.example.service.BoardService;
import com.lcom_test.example.service.UserService;
import com.lcom_test.example.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserService userService;
	
	@Autowired BoardService boardService;
	
	@Autowired ProductService productService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
		
		logger.info("test" + loginRequest);
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		User user = (User) authentication.getPrincipal();		
		logger.info("dddd" + authentication.getPrincipal()); 
		List<String> roles = user.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt,
												 user.getUsername(),
												 user.getName(),
												 user.getPhone(),
												 roles));

//		return ResponseEntity.ok(new JwtResponse(jwt, 
//												 user.getUsername(),
//												 user.getName(),
//												 user.getPhone(),
//												 roles);
	}
	@PostMapping("/signup")
	   public ResponseEntity<?> sinupUser(@Validated @RequestBody JoinRequest joinRequest) {
	      
	      String encodedPassword = new BCryptPasswordEncoder().encode(joinRequest.getPassword());
	      User user = new User();
	      
	      //유저 데이터 세팅
	      user.setUsername(joinRequest.getUsername());
	      user.setName(joinRequest.getName());
	      user.setPassword(encodedPassword);
	      user.setPhone(joinRequest.getPhone());
	      user.setAccountNonExpired(true);
	      user.setEnabled(true);
	      user.setAccountNonLocked(true);
	      user.setCredentialsNonExpired(true);
	      user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
	      
	      //유저 생성
	      userService.createUser(user);
	      //유저 권한 생성
	      userService.createAuthority(user);
	      
	      return new ResponseEntity<>("success", HttpStatus.OK);
	   }
	
	@GetMapping("/unpackToken")
	public ResponseEntity<?> unpackToken(HttpServletRequest request) {
		
		String token = new String();
		token =  request.getHeader("Authorization");
		
		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			token =  token.substring(7, token.length());
		}
		String username = JwtUtils.getUserEmailFromToken(token);
		UserInfo user = userService.readUser_refresh(username);

		 return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/home")
	public ResponseEntity<?> home(Images images) {
			List<Images> imageslist = boardService.selectImagesList();
			return ResponseEntity.ok(new ListResponse<Images>(imageslist));
	}
	
	@PostMapping("/boardwrite")
	public ResponseEntity<?> boardwrite(@RequestBody Board board){
		boardService.insertBoard(board);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	
	
	//업로드
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public ResponseEntity<?> upload(@RequestParam("uploadFile") MultipartFile multipartFile, Board board){
//		String path = "C:/Users/l9-morning/Documents/lcom_test/src/main/resources/static/images/"; 
//		String path = "C:/Users/user/Documents/GitHub/lcom_test/src/main/resources/static/images/";	노트북
//		String path = "C:/Users/82105/Documents/GitHub/lcom_test/src/main/resources/static/images/"; 데탑
//		String path = "C:/Users/l9-morning/Documents/lcom_test/src/vue-spring-jeon/public/images/";
		String path = "C:/Users/user/Documents/GitHub/lcom_test/src/vue-spring-jeon/public/images/";
		String thumbPath = path + "thumb/";
		String filename = multipartFile.getOriginalFilename();
		String ext = filename.substring(filename.lastIndexOf(".")+1);
		
		File file = new File(path + filename);
		File thmbFile = new File(thumbPath + filename);
		
		try {
		// 원본파일 저장
			InputStream input = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(input, file);
			
		// 썸네일 생성
			BufferedImage imageBuf = ImageIO.read(file);
			int fixWidth =  500;
			double ratio = imageBuf.getWidth() / (double)fixWidth;
			int thumbWidth = fixWidth;
			int thumbHeight = (int)(imageBuf.getHeight() / ratio);
			BufferedImage thumbImageBf = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D g  = thumbImageBf.createGraphics();
			Image thumbImage = imageBuf.getScaledInstance(thumbWidth, thumbHeight, Image.SCALE_SMOOTH);
			g.drawImage(thumbImage, 0,0,thumbWidth, thumbHeight, null);
			g.dispose();
			ImageIO.write(thumbImageBf, ext, thmbFile);
			
			boardService.insertBoard(board);
			
		} catch(IOException e) {
			FileUtils.deleteQuietly(file);
			e.printStackTrace();
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@GetMapping({"/boardlist", "/boardlist/{pageOpt}"})
	public ResponseEntity<?> boardlist(Board board,
			@PathVariable Optional<Integer> pageOpt) {
		
		int page = pageOpt.isPresent() ? pageOpt.get() : 1;
		int boardcount = boardService.getBoardCount();
		
		Pagination pagination = new Pagination(page, boardcount);
		
		logger.info(board.toString());
			List<Board> boardlist = boardService.selectBoardList(pagination);
			logger.info(boardlist.toString());
			return ResponseEntity.ok(new ListResponse<Board>(
					pagination,boardlist));
	}
	
	@GetMapping({"/boarddetail", "/boarddetail/{bId}", "/boarddetail/{bId}/{pageOpt}"})
	public ResponseEntity<?> boarddetail(
			@PathVariable int bId,
			@PathVariable Optional<Integer> pageOpt,
			Board board, Comment comment){
		
		logger.debug("bId:"+bId);
		int page = pageOpt.isPresent() ? pageOpt.get() : 1;
		int commentcount = boardService.getCommentCount();
		
		Pagination pagination = new Pagination(page, commentcount);
		
		board = boardService.getBoard(bId);
		comment.setPagination(pagination);
		board.setPagination(pagination);
		
		List<Comment> commentlist = boardService.selectCommentList(comment);
		board.setCommentList(commentlist);
		logger.info(board.toString());		
		return new ResponseEntity<>(board, HttpStatus.OK);
	}
	
	
	@PostMapping({"/boarddelete", "/boarddelete/{pageOpt}"})
	public ResponseEntity<?> deleteBoard(
			@RequestBody Board board,
			@PathVariable Optional<Integer> pageOpt){
		logger.info(board.toString());
		boardService.deleteBoard(board);
		
		int page = pageOpt.isPresent() ? pageOpt.get() : 1;
		int boardcount = boardService.getBoardCount();
		
		Pagination pagination = new Pagination(page, boardcount);
		List<Board> boardlist = boardService.selectBoardList(pagination);
			return ResponseEntity.ok(new ListResponse<Board>(
					pagination,boardlist));
	}
	
	@PostMapping({"/boardedit"})
	public ResponseEntity<?> boardedit(@RequestBody Board board){
		boardService.insertEdit(board);
		return new ResponseEntity<>(board, HttpStatus.OK);
	}
	
	@PostMapping("/boardreply")
	public ResponseEntity<?> boardreply(@RequestBody Board board){		
		boardService.insertBoard(board);
		return new ResponseEntity<>("success", HttpStatus.OK);
	} // boardwrite와 합치기
	
	
	@PostMapping({"/commentwrite", "/commentwrite/{pageOpt}"})
	public ResponseEntity<?> boardcomment(@RequestBody Comment comment, Board board,
			@PathVariable Optional<Integer> pageOpt){
		
		int page = pageOpt.isPresent() ? pageOpt.get() : 1;
		int commentcount = boardService.getCommentCount();
		
		Pagination pagination = new Pagination(page, commentcount);
		comment.setPagination(pagination);
		boardService.insertComment(comment);
		List<Comment> commentlist = boardService.selectCommentList(comment); // insert 이후 Comment list를 보내주기 위해
		
		board.setCommentList(commentlist);
		board.setPagination(pagination);
		
		return new ResponseEntity<>(board, HttpStatus.OK);
	}
	
	@PostMapping({"/commentdelete", "/commentdelete/{pageOpt}"})
	public ResponseEntity<?> commentdelete(@RequestBody Comment comment, Board board,
			@PathVariable Optional<Integer> pageOpt){
		
		boardService.deleteComment(comment); // 1. 댓글 삭제
		
		int page = pageOpt.isPresent() ? pageOpt.get() : 1;
		int commentcount = boardService.getCommentCount();
		
		Pagination pagination = new Pagination(page, commentcount); // Pagination에 따라 삭제할 수 있도록 Pagination 객체 생성
		comment.setPagination(pagination);	// selectCommentList의 ?,? 값 대입을 위해 설정
		List<Comment> commentlist = boardService.selectCommentList(comment); // 2. 삭제된 댓글을 제외한 나머지 리스트 뽑기 위해 설정
		
		board.setPagination(pagination); 
		board.setCommentList(commentlist); // board 내부의 Pagination, CommentList를 사용하기 위해 설정
		
		return new ResponseEntity<>(board, HttpStatus.OK); // board 객체 return
		
	}
	
	@PostMapping({"/commentedit", "/commentedit/{pageOpt}"})
	public ResponseEntity<?> commentedit(@RequestBody Comment comment, Board board,
			@PathVariable Optional<Integer> pageOpt){
		
		boardService.updateComment(comment);
		
		int page = pageOpt.isPresent() ? pageOpt.get() : 1;
		int commentcount = boardService.getCommentCount();
		
		Pagination pagination = new Pagination(page, commentcount);
		
		comment.setPagination(pagination);
		
		List<Comment> commentlist = boardService.selectCommentList(comment);
		
		board.setPagination(pagination);
		board.setCommentList(commentlist);
		
		return new ResponseEntity<>(board, HttpStatus.OK);
	}
	
	
	@GetMapping({"/commentlist", "commentlist/{bId}", "commentlist/{bId}/{pageOpt}"})
	public ResponseEntity<?> commentlist(Comment comment,
			@PathVariable int bId,
			@PathVariable Optional<Integer> pageOpt){
		
		int page = pageOpt.isPresent() ? pageOpt.get() : 1;
		int commentcount = boardService.getCommentCount();
		
		Pagination pagination = new Pagination(page, commentcount);
		comment.setPagination(pagination);
		
		logger.info(comment.toString());
		List<Comment> commentlist = boardService.selectCommentList(comment);
		logger.info(commentlist.toString());
		return ResponseEntity.ok(new ListResponse<Comment>(pagination,commentlist));
	}
	
	
	@GetMapping({"/latestitems", "/latestitems/{pageOpt}","/latestitems/{pageOpt}/{username}"})
	public ResponseEntity<?> latestitems(Product product, // 여기서 Vo에 입력한 pHeart의 기본값 false가 정의된다.
			@PathVariable int pageOpt,
			@PathVariable String username){
		product.setPageOpt(pageOpt);
		product.setUsername(username);
		List<Product> itemslist = productService.selectProductList(product);
		logger.info(itemslist.toString());
		return ResponseEntity.ok(new ListResponse<Product>(itemslist));
	} // 최신상품리스트
	
	@GetMapping({"/hotitems", "/hotitems/{pageOpt}","/hotitems/{pageOpt}/{username}"})
	public ResponseEntity<?> hotitems(Product product, // 여기서 Vo에 입력한 pHeart의 기본값 false가 정의된다.
			@PathVariable int pageOpt,
			@PathVariable String username){
		product.setPageOpt(pageOpt);
		product.setUsername(username);
		List<Product> itemslist = productService.selectHotList(product);
		logger.info(itemslist.toString());
		return ResponseEntity.ok(new ListResponse<Product>(itemslist));
	} // 인기상품리스트
	
	@GetMapping("/homePage")
	public ResponseEntity<?> homePage(HomeImg homeimg){
		List<HomeImg> homeimglist = productService.selectHomeImgList(homeimg);
		return ResponseEntity.ok(new ListResponse<HomeImg>(homeimglist));
	} // 홈페이지 이미지 가져오기
	
	@GetMapping({"/productrank"})
	public ResponseEntity<?> productrank(Product product){
		List<Product> itemslist = productService.selectProductList(product);
		logger.info(itemslist.toString());
		return ResponseEntity.ok(new ListResponse<Product>(itemslist));
	} // 최신상품리스트
	
	@GetMapping({"/itemdetail", "/itemdetail/{pName}"})
	public ResponseEntity<?> itemdetail(
			@PathVariable String pName,
			Product product){
		
		logger.debug("pName:"+pName);
		product = productService.getProduct(pName);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@PostMapping({"/putcart", "/putcart/{username}"})
	public ResponseEntity<?> putcart(@RequestBody Product product, Cart cart,
			@PathVariable String username){
		// Cart cart의 username 에 받아온 username 값 들어감 (cart의 변수이름과 일치시켜야함)
		logger.debug("username:"+username);
		cart.setpId(product.getpId());
		
		productService.insertPutCart(cart);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@GetMapping({"/putcartlist", "putcartlist/{username}", "putcartlist/{username}/{pageOpt}"})
	public ResponseEntity<?> putcartlist(Cart cart, 
			@PathVariable String username,
			@PathVariable int pageOpt){ //pageOpt는 Optional 을 뜻하므로 그냥 page라고 쓰는게 나음
		// Cart cart의 username 에 받아온 username 값 들어감 (cart의 변수이름과 일치시켜야함)
		cart.setPageOpt(pageOpt);
		cart.setUsername(username);
		List<Cart> cartlist = productService.selectCartList(cart);		
		logger.debug("username:"+cart.getUsername());
		return ResponseEntity.ok(new ListResponse<Cart>(cartlist));
	}
	
	@GetMapping({"/orderlist","/orderlist/{username}"})
	public ResponseEntity<?> orderlist(Order order,
			@PathVariable String username){
		order.setUsername(username);
		List<Order> orderlist = productService.selectOrderList(order);
		return ResponseEntity.ok(new ListResponse<Order>(orderlist));		
	}
	
	@PostMapping("/deletecart") 
	public ResponseEntity<?> deleteCart(
			@RequestBody Cart cart){
		logger.info(cart.toString());
			productService.deleteCart(cart.getCtId());
		
			return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@PostMapping({"/likeproduct","/likeproduct/{username}"})
	public ResponseEntity<?> likeproduct(@RequestBody Product product,
			@PathVariable String username){
		productService.likeProduct(product.getpId()); // product pLike + 1
		product.setUsername(username);
		productService.insertHeart(product); //pId, username 보내주기 위해 객체 전송, vue_heart에 insert
		List<Product> productlist = productService.selectProductList(product); // 테스트 로직
		return ResponseEntity.ok(new ListResponse<Product>(productlist));
		
	}
	
	@PostMapping({"/cancellike","/cancellike/{username}"})
	public ResponseEntity<?> cancellike(@RequestBody Product product,
			@PathVariable String username){
		productService.cancelLike(product.getpId()); // product pLike - 1
		product.setUsername(username);
		productService.deleteHeart(product);
		List<Product> productlist = productService.selectProductList(product);
		return ResponseEntity.ok(new ListResponse<Product>(productlist));
	}
	
	@PostMapping("/buyproduct")
	public ResponseEntity<?> buyproduct(@RequestBody Order order){
		productService.buyProduct(order);
		productService.updateQuantity(order);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	
	
	
//	@PostMapping("/kakaopay")
//	@ResponseBody
//	public String kakaopay(@RequestBody Product product){
//		try {
//			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
//			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
//			huc.setRequestMethod("POST");
//			huc.setRequestProperty("Authorization", "KakaoAK 9ebd839a995a6df19b86b9dd787e0b87");
//			huc.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//			huc.setDoOutput(true);
//			logger.debug("Product="+product);
//			String parameter = 
//					"cid=TC0ONETIME&"
//					+ "partner_order_id=partner_order_id"
//					+ "&partner_user_id=partner_user_id&"
//					+ "item_name=초코파이&"
//					+ "quantity=1&"
//					+ "total_amount=2200&"
//					+ "vat_amount=200&"
//					+ "tax_free_amount=0&"
//					+ "approval_url=http://localhost:8080/success&"
//					+ "fail_url=http://localhost:8080/fail&"
//					+ "cancel_url=http://localhost:8080/cancel";
//			
//			OutputStream output = huc.getOutputStream();
//			DataOutputStream dataoutput = new DataOutputStream(output); 
//			dataoutput.writeBytes(parameter);
//			dataoutput.close();
//			
//			int result = huc.getResponseCode();
//			
//			InputStream input;
//			if(result == 200) {
//				input = huc.getInputStream();
//			} else {
//				input = huc.getErrorStream();
//			}
//			InputStreamReader inputread = new InputStreamReader(input);
//			BufferedReader buffer = new BufferedReader(inputread);
//			return buffer.readLine();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "(\"result\":\"NO\")";
//	}
//	
//	
//	
}