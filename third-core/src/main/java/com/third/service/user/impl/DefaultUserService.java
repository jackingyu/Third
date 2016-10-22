package com.third.service.user.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.third.dao.user.UserDao;
import com.third.model.User;
import com.third.service.user.UserService;

public class DefaultUserService implements UserService{
   
	private UserDao userDao;
	
	public User getUserById(String userId) {
		
		User user = new User();
         user.setName("d");
         user.setPassword("xxx");
         user.setId(userId);
        List<User> user1 = userDao.findByNamedQuery("selectUserById","yuxiang");
        System.out.println(user1.get(0).getName());
		return user;
	}

	public void saveUser(User user) {
		//userMapper.insert(user);
	}
	
	
	public User getCurrentUser(){
		String username =  (String) SecurityContextHolder.getContext()
			    .getAuthentication().getPrincipal();
		
		return getUserById(username);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
