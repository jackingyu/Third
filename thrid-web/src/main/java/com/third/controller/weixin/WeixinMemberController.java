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
import com.third.facade.customer.CustomerFacade;
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
	
	@Resource(name="weixinService")
	private WeixinService weixinService;
	
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
		
		try
		{
			openId = weixinService.getOpenID(code);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("IN GET BIND1-"+openId);
		LOG.info("IN GET BIND2-"+sessionService.get(WXConstant.WX_OPENID));
		return ControllerConstants.WeiXin.BIND;
		
	}
	
	@RequestMapping(value = "/bindCustomer")
	public void bindCustomer(
			@RequestParam(value="vcode") final String vcode,@RequestParam(value="cellphone") final String cellphone,final Model model)
	{
		if(smsVerifyCodeUtils.verifyVcode(vcode))
		{
			CustomerData customer = customerFacade.getCustomerByCellphone(cellphone);
		}
	}
	
	//@RequestMapping(value = "/getStoreDetail")
	public String register(@RequestParam(value ="code",required=true)final String storeCode,final Model model)
	{
		return ControllerConstants.WeiXin.STOREDETAILPAGE;
	}

}
