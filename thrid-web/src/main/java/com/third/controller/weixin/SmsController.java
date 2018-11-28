package com.third.controller.weixin;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.web.utils.Message;
import com.third.web.utils.SmsVerifyCodeUtils;

@Controller
public class SmsController {
	@Autowired
	SmsVerifyCodeUtils smsVerifyCodeUtils;

	@RequestMapping(value = "/weixin/generatevcode")
	@ResponseBody
	public Object sendVCode(
			@RequestParam(value = "cellphone") final String cellphone)
	{
		smsVerifyCodeUtils.generateVCode(cellphone);
		return new Message(true);
	}

}
