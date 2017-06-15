package com.third.controller.weixin;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.third.controller.pages.ControllerConstants;


@Controller
public class WeixinErrorController extends AbstractWeixinController
{
	private static final Logger LOG = Logger.getLogger(WeixinErrorController.class);
	
	@RequestMapping("/wxerror")
	public String getErrorPage(
			final HttpServletRequest request,
			final Model model,
			@RequestParam(value="errorCode",required=false)final String errorCode)
	{
	   return ControllerConstants.WeiXin.ERRORPAGE;
	}
	
}
