package com.third.service.user.impl;


import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.third.dao.user.UserDao;
import com.third.dao.user.UserGroupDao;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.user.UserService;


public class DefaultUserService implements UserService
{

	private UserDao userDao;
	private UserGroupDao userGroupDao;

	public UserModel getUserById(String userId)
	{
		UserModel user = userDao.findUserById(userId);
		return user;
	}

	public UserModel getCurrentUser()
	{
		//String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//TODO:need to refact the mockup logic
		return getUserById("yuxiang");
	}

	public void createUser(UserModel user)
	{
		userDao.save(user);
	}

	public void createUserGroup(UserGroupModel userGroup)
	{
		userGroupDao.save(userGroup);
	}


	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}

	public UserGroupModel getUserGroupById(String useGroupdId)
	{
		return null;
	}

	public void setUserGroupDao(UserGroupDao userGroupDao)
	{
		this.userGroupDao = userGroupDao;
	}

	public boolean isExist(String userId)
	{
		UserModel userModel = new UserModel();
		return userDao.contains(userModel);
	}

	@Override
	public void saveUser(UserModel user)
	{
		userDao.update(user);
	}

	@Override
	public void saveUserGroup(UserGroupModel userGroup)
	{
		userGroupDao.save(userGroup);
	}

	@Override
	public List<UserGroupModel> getUserGroupList(final String userGroupId, final String userGroupName)
	{
		return userGroupDao.findUserGroupByName(userGroupId, userGroupName);
	}

}
