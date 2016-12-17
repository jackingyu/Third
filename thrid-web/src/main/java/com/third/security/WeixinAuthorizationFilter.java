package com.third.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.filter.GenericFilterBean;

import com.third.core.util.WXConstant;
import com.third.facade.customer.CustomerFacade;
import com.third.facade.customer.WeixinFacade;
import com.third.facade.data.CustomerData;
import com.third.security.exceptions.WeixinAuthenticationException;
import com.third.service.customer.CustomerService;
import com.third.service.customer.WeixinService;
import com.third.service.user.SessionService;


public class WeixinAuthorizationFilter extends GenericFilterBean
{

	protected static final Logger LOG = Logger.getLogger(WeixinAuthorizationFilter.class);
	@Resource(name="sessionService")
	private SessionService sessionService;
	
	@Resource(name="customerFacade")
	private CustomerFacade customerFacade;

	@Resource(name="weixinFacade")
	private WeixinFacade weixinFacade;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		LOG.info("in custom filter");
		
		// session 中有open id ,证明当前页面是通过微信进入
		if(sessionService.contains(WXConstant.WX_OPENID))
		{
			//该微信已经绑定了有效账户,且已经存在了
			LOG.info("there is openid");
			if(sessionService.contains(WXConstant.WX_CUSTOMER))
				chain.doFilter(request, response);
			else
				LOG.info("not exist suitation");
		}
		else
		{
			String code = request.getParameter("code");
			if (StringUtils.isEmpty(code))
				throw new WeixinAuthenticationException(WXConstant.WX_ERR_MUST_FROM_WX);
			else
			{
				final String openId = weixinFacade.getOpenId(code);
				LOG.info("get open id "+openId);
				sessionService.save(WXConstant.WX_OPENID,openId );
				CustomerData customer = customerFacade.loginCustomer(openId);

				if(customer == null)
					throw new WeixinAuthenticationException(WXConstant.WX_ERR_NOT_BIND_CUST);
			}
		}
		
		
      
			

	}

}
