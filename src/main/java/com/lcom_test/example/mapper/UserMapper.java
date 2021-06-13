package com.lcom_test.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;
import com.lcom_test.example.domain.User;
import com.lcom_test.example.domain.UserInfo;

@Mapper
public interface UserMapper {
	//유저읽기
			public User readUser(String username);
		//유저읽기 새로고침
			public UserInfo readUser_refresh(String username);
		 //유저생성
			public void createUser(User user);
		//회원목록 불러오기
			public List<UserInfo>read_user_list(String keyword);
			
			public List<UserInfo> read_user_list(User user);
			
		 // 권한 읽기
			public List<GrantedAuthority> readAuthorities(String username);
		 // 권한 생성
			public void createAuthority(User user);
			
			public void deleteUser(User user);
			
			public void deleteAuth(User user);
			
}