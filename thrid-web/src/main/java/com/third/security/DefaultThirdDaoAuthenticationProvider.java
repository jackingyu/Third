/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.third.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;

import com.third.model.UserModel;
import com.third.service.user.UserService;

/**
 * Derived authentication provider supporting additional authentication checks.
 * See
 * {@link de.hybris.platform.spring.security.RejectUserPreAuthenticationChecks}.
 * 
 * <ul>
 * <li>prevent login without password for users created via CSCockpit</li>
 * <li>prevent login as user in group admingroup</li>
 * </ul>
 * 
 * any login as admin disables SearchRestrictions and therefore no page can be
 * viewed correctly
 */
public class DefaultThirdDaoAuthenticationProvider
		implements AuthenticationProvider {
	private UserDetailsService userDetailsService;
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException
	{

		final Object credential = authentication.getCredentials();
		final String username = authentication.getName();

		if (StringUtils.isEmpty(username))
		{
			throw new BadCredentialsException(
					"login.error.account.cannot.be.empty");
		}

		if (credential == null || credential.equals(""))
		{
			throw new BadCredentialsException("profile.password.cannot.blank");
		}

		UserModel user = userService.getUserById(username);

		if (user == null)
		{
			throw new BadCredentialsException("login.error.account.notexist");
		}

		checkPassword(authentication, user);

		UserDetails userDetails = userDetailsService
				.loadUserByUsername(authentication.getName());

		return createSuccessAuthentication(authentication, userDetails);
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return (RememberMeAuthenticationToken.class
				.isAssignableFrom(authentication))
				|| (UsernamePasswordAuthenticationToken.class
						.isAssignableFrom(authentication));
	}

	protected Authentication createSuccessAuthentication(
			final Authentication authentication, final UserDetails user)
	{
		// Ensure we return the original credentials the user supplied,
		// so subsequent attempts are successful even with encoded passwords.
		// Also ensure we return the original getDetails(), so that future
		// authentication events after cache expiry contain the details
		final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
				user.getUsername(), authentication.getCredentials(),
				user.getAuthorities());
		result.setDetails(authentication.getDetails());

		return result;
	}

	private void checkPassword(final Authentication authentication,
			UserModel user)
	{
		if (authentication.getCredentials().toString()
				.equals(user.getPassword()))
			return;

		throw new BadCredentialsException("password not right");
	}

	public void setUserDetailsService(UserDetailsService userDetailsService)
	{
		this.userDetailsService = userDetailsService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
}
