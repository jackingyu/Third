package com.third.controller.weixin;


import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.third.controller.pages.ControllerConstants;
import com.third.core.util.WXConstant;
import com.third.exceptions.BussinessException;
import com.third.exceptions.NotFoundException;
import com.third.exceptions.SubscribeException;
import com.third.facade.customer.CustomerFacade;
import com.third.facade.customer.WeixinFacade;
import com.third.facade.data.CustomerData;
import com.third.model.CoreConstants;
import com.third.service.customer.WeixinService;
import com.third.service.user.SessionService;
import com.third.web.utils.SmsVerifyCodeUtils;


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
