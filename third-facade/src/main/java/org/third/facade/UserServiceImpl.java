package org.third.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.third.domain.User;
import org.third.mybatis.mapper.UserMapper;

public class UserServiceImpl {
	
	@Autowired
	private UserMapper userMapper;
	
    public User getUser(String uid){
    	return userMapper.getUser(uid);
    }

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
    
    
}
