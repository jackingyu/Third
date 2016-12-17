package com.third.controller.weixin;


import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.third.service.customer.WeixinService;
import com.third.service.user.SessionService;
import com.third.web.utils.SmsVerifyCodeUtils;


@Controller
@RequestMapping("/wx")
public class WeixinMemberController extends AbstractWeixinController
{
	private static final Logger LOG = Logger.getLogger(WeixinMemberController.class);
	
	@Resource(name="customerFacade")
	private CustomerFacade customerFacade;
	
	@Resource(name="sessionService")
	private SessionService sessionService;
	
	@Resource(name="weixinFacade")
	private WeixinFacade weixinFacade;
	
	@Resource(name="smsVerifyCodeUtils")
	SmsVerifyCodeUtils smsVerifyCodeUtils;
	@RequestMapping(value = "/getBindCustomerPage")
	public String getBindCustomerPage(
			final HttpServletRequest request,
			final Model model,
			@RequestParam(value="code",required=false)final String code,
			@RequestParam(value="state",required=false)final String state)
	{
		String openId = "";
		
	   openId = weixinFacade.getOpenId(code);
	
		return ControllerConstants.WeiXin.BIND;
		
	}
	
	@RequestMapping(value = "/bindCustomer")
	public void bindCustomer(
			@RequestParam(value="vcode",required=false) final String vcode,@RequestParam(value="cellphone",required=false) final String cellphone,final Model model)
	{
		LOG.debug("vcode  is" +vcode);
		LOG.debug("cellphone is"+cellphone);
		if(smsVerifyCodeUtils.verifyVcode(vcode))
		{
				try
				{
					customerFacade.bindCustomer((String)sessionService.get(WXConstant.WX_OPENID), cellphone);
				}
				catch (NotFoundException e)
				{
					e.printStackTrace();
				}
				catch (SubscribeException e)
				{
					e.printStackTrace();
				}
			
		}
	}
	
	//@RequestMapping(value = "/getStoreDetail")
	public String register(@RequestParam(value ="code",required=true)final String storeCode,final Model model)
	{
		return ControllerConstants.WeiXin.STOREDETAILPAGE;
	}

}
