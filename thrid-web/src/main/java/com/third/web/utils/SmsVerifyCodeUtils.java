package com.third.web.utils;

import javax.annotation.Resource;

import com.third.service.user.SessionService;

public class SmsVerifyCodeUtils
{
	@Resource(name="sessionService")
	private SessionService sessionService;
	
	private final static String VCODE = "vcode";

	public boolean verifyVcode(final String inputVCode){
		String vcode = (String) sessionService.get(VCODE);
		return inputVCode.equals(vcode);
	}
	
	public void generateVCode(){
		sessionService.save(VCODE, "111111");
	}
}
