package com.lcom_test.example.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lcom_test.example.config.JwtUtils;
import com.lcom_test.example.domain.Board;
import com.lcom_test.example.domain.Comment;
import com.lcom_test.example.domain.Pagination;
import com.lcom_test.example.domain.User;
import com.lcom_test.example.domain.UserInfo;
import com.lcom_test.example.request.JoinRequest;
import com.lcom_test.example.request.LoginRequest;
import com.lcom_test.example.response.JwtResponse;
import com.lcom_test.example.response.ListResponse;
import com.lcom_test.example.service.BoardService;
import com.lcom_test.example.service.UserService;

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
												 roles));
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
		
		List<Comment> commentlist = boardService.selectCommentList(comment);
		board.setCommentList(commentlist);
		logger.info(board.toString());		
		return new ResponseEntity<>(board, HttpStatus.OK);
	}
	
	@PostMapping("/boardwrite")
	public ResponseEntity<?> boardwirte(@RequestBody Board board){
		boardService.insertBoard(board);
		return new ResponseEntity<>("success", HttpStatus.OK);
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
	}
	
	
	@PostMapping("/commentwrite")
	public ResponseEntity<?> boardcomment(@RequestBody Comment comment){
		boardService.insertComment(comment);
		List<Comment> commentlist = boardService.selectCommentList(comment); // insert 이후 Comment list를 보내주기 위해
		return new ResponseEntity<>(commentlist, HttpStatus.OK); 
	}
	
//	@GetMapping({"/commentlist", "commentlist/{bId}"})
//	public ResponseEntity<?> commentlist(Comment comment,
//			@PathVariable int bId){
//		logger.info(comment.toString());
//		List<Comment> commentlist = boardService.selectCommentList(comment);
//		logger.info(commentlist.toString());
//		return ResponseEntity.ok(new ListResponse<Comment>(commentlist));
//	}
}