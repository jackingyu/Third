package com.third.service.user.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.third.model.RoleModel;
import com.third.model.UserModel;
import com.third.service.user.UserService;

public class DefaultUserDetailsService implements UserDetailsService {

	private UserService userService;
	private final String rolePrefix = "ROLE_";

	public UserDetails loadUserByUsername(String username)
	{
		UserModel user = userService.getUserById(username);

		if (user == null)
			throw new UsernameNotFoundException(username + " not found!");

		// TODO dummy values, will be delivered by the platform
		final boolean accountNonExpired = true;
		final boolean credentialsNonExpired = true;
		final boolean accountNonLocked = true;

		String password = user.getPassword();
		// a null value has to be transformed to empty string, otherwise the
		// constructor
		// of org.springframework.security.userdetails.User will fail
		if (password == null)
		{
			password = "";
		}

		final org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
				username, password, getAuthorities(user));

		return userDetails;
	}

	private Collection<GrantedAuthority> getAuthorities(final UserModel user)
	{
		Collection<RoleModel> roleList = user.getUserGroup().getRoles();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(
				roleList.size());
		final Iterator<RoleModel> itr = roleList.iterator();
		while (itr.hasNext())
		{
			final RoleModel role = itr.next();
			authorities.add(new SimpleGrantedAuthority(
					rolePrefix + role.getRoleId().toUpperCase()));
		}

		return authorities;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

}
