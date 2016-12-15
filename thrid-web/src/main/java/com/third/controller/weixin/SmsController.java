package com.third.controller.weixin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.third.web.utils.SmsVerifyCodeUtils;

@Controller
public class SmsController
{
	@Resource(name="smsVerifyCodeUtils")
	SmsVerifyCodeUtils smsVerifyCodeUtils;
	
	@RequestMapping(value = "/sendVCode")
	public void sendVCode(@RequestParam(value="cellphone")final String cellphone){
		smsVerifyCodeUtils.generateVCode();
	}

}
