package com.lcom_test.example.service;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lcom_test.example.domain.Pagination;
import com.lcom_test.example.domain.User;
import com.lcom_test.example.domain.UserInfo;
import com.lcom_test.example.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//System.out.println(username);
		User user = userMapper.readUser(username);
		user.setAuthorities(getAuthorities(username));	
		return user;
	}

	@Override
	public User readUser(String username) {
		return userMapper.readUser(username);
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities(String username) {
		List<GrantedAuthority> authorities = userMapper.readAuthorities(username);
		return authorities;
	}

	 @Override
	   public void createUser(User user) {
	      userMapper.createUser(user);
	   }

	@Override
	public void createAuthority(User user) {
		userMapper.createAuthority(user);
	}
	
	@Override
	public List<UserInfo>read_user_list(Pagination pagination) {
		return userMapper.read_user_list(pagination);
	}
	
	@Override
	public List<UserInfo>read_user_list(User user) {
		return userMapper.read_user_list(user);
	}
	
	//유저읽기 새로고침
	public UserInfo readUser_refresh(String username) {
		return userMapper.readUser_refresh(username);
	}

	@Override
	public void deleteUser(User user) {
		userMapper.deleteUser(user);
	}

	@Override
	public void deleteAuth(User user) {
		userMapper.deleteAuth(user);
		
	}

	@Override
	public int getUserCount() {
		return userMapper.getUserCount();
	}


}
