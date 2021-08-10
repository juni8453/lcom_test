package com.lcom_test.example.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lcom_test.example.config.JwtUtils;
import com.lcom_test.example.domain.Board;
import com.lcom_test.example.domain.Images;
import com.lcom_test.example.domain.Pagination;
import com.lcom_test.example.domain.Product;
import com.lcom_test.example.domain.User;
import com.lcom_test.example.domain.UserInfo;
import com.lcom_test.example.response.JwtResponse;
import com.lcom_test.example.response.ListResponse;
import com.lcom_test.example.service.UserService;
import com.lcom_test.example.service.BoardService;
import com.lcom_test.example.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;
		
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/insertproducttest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> insertproducttest(
			@RequestPart("uploadFile") MultipartFile[] files,
			Product product, Images images
			){
//		String path = "C:/Users/l9-morning/Documents/lcom_test/src/vue-spring-jeon/public/images/";
		String path = "C:/Users/user/Documents/GitHub/lcom_test/src/vue-spring-jeon/public/images/";	
//		String path = "C:/Users/82105/Documents/GitHub/lcom_test/src/vue-spring-jeon/public/images/";
		String thumbPath = path + "thumb/";
		
		// 파일 이름 중복 방지 (년월일초 추가)
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date time = new Date();
		String dateresult = dateFormat.format(time);
		
		for(MultipartFile file : files) {
//			String filename = images.getiPk() + file.getOriginalFilename();
			String filename = dateresult + file.getOriginalFilename();
			String ext = filename.substring(filename.lastIndexOf(".")+1);
			File multifile = new File(path + filename);
			File thumbFile = new File(thumbPath + filename);
			System.out.println(filename);
			
			try {
				// 원본 파일 저장
				InputStream input = file.getInputStream();
				FileUtils.copyInputStreamToFile(input, multifile);
				
				// 썸네일 생성
				BufferedImage imageBuf = ImageIO.read(multifile);
				int fixWidth =  500;
				double ratio = imageBuf.getWidth() / (double)fixWidth;
				int thumbWidth = fixWidth;
				int thumbHeight = (int)(imageBuf.getHeight() / ratio);
				BufferedImage thumbImageBf = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_3BYTE_BGR);
				Graphics2D g  = thumbImageBf.createGraphics();
				Image thumbImage = imageBuf.getScaledInstance(thumbWidth, thumbHeight, Image.SCALE_SMOOTH);
				g.drawImage(thumbImage, 0,0,thumbWidth, thumbHeight, null);
				g.dispose();
				ImageIO.write(thumbImageBf, ext, thumbFile);
				
				productService.insertProduct(product);		// 제품 내용 디비에 삽입 (중복방지 SQL문)
				product.setiName(filename);					// DB의 i_name에 값을 넣기 위해 년월일초와 합친 파일 이름을 product에 넣어준다.
				productService.insertImage(product); 		// 이미지 내용 디비에 삽입 (pName값 넘겨주기)
				
			}  catch(IOException e) {
				FileUtils.deleteQuietly(multifile);
				e.printStackTrace();
			}
		
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	} // 다중 파일 업로드 Test

	
	// 이 부분 삭제해보기 (8_11일에)
	@PostMapping("/insertproduct")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> insertproduct(Product product,
			@RequestParam("uploadFile") MultipartFile multipartFile, Images images){
//		String path = "C:/Users/user/Documents/GitHub/lcom_test/src/vue-spring-jeon/public/images/";
//		String path = "C:/Users/82105/Documents/GitHub/lcom_test/src/vue-spring-jeon/public/images/";
		String path = "C:/Users/user/Documents/GitHub/lcom_test/src/vue-spring-jeon/public/images/";
//		String path = "C:/Users/82105/Documents/GitHub/lcom_test/src/vue-spring-jeon/public/images/";
//		String path = "C:/Users/l9-morning/Documents/lcom_test/src/vue-spring-jeon/public/images/";
//		
		String thumbPath = path + "thumb/";
		String filename = images.getiPk() + multipartFile.getOriginalFilename();
		String ext = filename.substring(filename.lastIndexOf(".")+1);
		
		File file = new File(path + filename);
		File thumbFile = new File(thumbPath + filename);
		
		
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
			ImageIO.write(thumbImageBf, ext, thumbFile);
			
			
		} catch(IOException e) {
			FileUtils.deleteQuietly(file);
			e.printStackTrace();
		}
		
		productService.insertProduct(product);
		boardService.insertImage(images);
		boardService.updatepId(images);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	
	@GetMapping({"/adminPage", "adminPage/{pageOpt}"})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?>  AccessAdmin(HttpServletRequest request, 
		@PathVariable Optional<Integer> pageOpt){
		
		int page = pageOpt.isPresent() ? pageOpt.get() : 1;
		int usercount = userService.getUserCount();
		
		Pagination pagination = new Pagination(page, usercount);
		
		logger.info(request.toString());			
			List<UserInfo> userlist = userService.read_user_list(pagination);
			logger.info(userlist.toString());	
			return ResponseEntity.ok(new ListResponse<UserInfo>(pagination, userlist));
	}

	@PostMapping({"/userdelete", "/userdelete/{pageOpt}"})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUser(
			@RequestBody User user,
			@PathVariable Optional<Integer> pageOpt){
		logger.info(user.toString());
			userService.deleteUser(user);
			userService.deleteAuth(user);
			
			int page = pageOpt.isPresent() ? pageOpt.get() : 1;
			int usercount = userService.getUserCount();
			
			Pagination pagination = new Pagination(page, usercount);
			List<UserInfo> userlist = userService.read_user_list(pagination);
				return ResponseEntity.ok(
						new ListResponse<UserInfo>(pagination, userlist));
	}
	
	@PostMapping("/deleteproduct") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteProduct(
			@RequestBody Product product){
		logger.info(product.toString());
			productService.deleteProduct(product.getpId());
			
			return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@GetMapping("/productrank")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putcartlist(Product product){
		List<Product> ranklist = productService.selectRankList(product);		
		return ResponseEntity.ok(new ListResponse<Product>(ranklist));
	}
	
}
