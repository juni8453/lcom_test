package com.lcom_test.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lcom_test.example.config.JwtUtils;
import com.lcom_test.example.domain.Board;
import com.lcom_test.example.domain.Pagination;
import com.lcom_test.example.domain.User;
import com.lcom_test.example.domain.UserInfo;
import com.lcom_test.example.response.JwtResponse;
import com.lcom_test.example.response.ListResponse;
import com.lcom_test.example.service.UserService;

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
	
	@GetMapping({"/adminPage", "adminPage/{pageOpt}"})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?>  AccessAdmin(HttpServletRequest request, 
		@PathVariable Optional<Integer> pageOpt){
		
		int page = pageOpt.isPresent() ? pageOpt.get() : 1;
		int usercount = userService.getUserCount();
		
		Pagination pagination = new Pagination(page, usercount);
		
		logger.info(request.toString());			
			List<UserInfo> userList = userService.read_user_list(pagination);
			logger.info(userList.toString());	
//			  return new ResponseEntity<>(userList, HttpStatus.OK);
			return ResponseEntity.ok(new ListResponse(
					userList, pagination));
	}
//	return ResponseEntity.ok(new JwtResponse(jwt, 
//			 user.getUsername(), 
//			 user.getName(),  
//			 roles));
	
	@PostMapping("/userdelete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUser(
			@RequestBody User user){
		logger.info(user.toString());
			userService.deleteUser(user);
			userService.deleteAuth(user);
			return new ResponseEntity<>("success", HttpStatus.OK);
	}
}
