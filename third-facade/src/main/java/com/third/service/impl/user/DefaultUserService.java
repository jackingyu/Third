package com.third.service.impl.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.third.dao.impl.UserMapper;
import com.third.model.User;
import com.third.model.UserGroup;
import com.third.service.user.UserService;

public class DefaultUserService implements UserService{

	private UserMapper userMapper;

	
	public User getUserById(String userId) {
		
		User user = userMapper.getUserById(userId);
		UserGroup usergroup = userMapper.getUsergroup(userId);
		System.out.println("user group "+usergroup.getName());
		
		return user;
	}

	public void saveUser(User user) {
		//userMapper.insert(user);
	}
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public User getCurrentUser(){
		String username =  (String) SecurityContextHolder.getContext()
			    .getAuthentication().getPrincipal();
		
		return getUserById(username);
	}
	

}
