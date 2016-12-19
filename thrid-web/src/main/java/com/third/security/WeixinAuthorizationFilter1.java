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
import com.third.model.CoreConstants;
import com.third.security.exceptions.WeixinAuthenticationException;
import com.third.service.customer.CustomerService;
import com.third.service.customer.WeixinService;
import com.third.service.user.SessionService;


public class WeixinAuthorizationFilter1 extends GenericFilterBean
{

	protected static final Logger LOG = Logger.getLogger(WeixinAuthorizationFilter1.class);
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
			if(sessionService.contains(CoreConstants.Session.CURRENT_CUSTOMER))
			{
				LOG.debug("有已绑定的顾客,正常访问");
				chain.doFilter(request, response);
			}
			else
			{
				//如registerCustomer应该是这种情形
				LOG.info("not exist suitation");
			}
		}
		else
		{
			String code = request.getParameter("code");
			
			//该URL是在security的管控下,必须通过微信的页面来访问的
			if (StringUtils.isEmpty(code))
			{
				LOG.debug("必须通过微信客户端来访问");
				throw new WeixinAuthenticationException(WXConstant.WX_ERR_MUST_FROM_WX);
			}
			else
			//通过微信菜单初次进入此session
			{
				LOG.debug("第一次进入此session,初始化openId,customer数据");
				final String openId = weixinFacade.getOpenId(code);
				LOG.debug("get open id "+openId);
				sessionService.save(WXConstant.WX_OPENID,openId );
				CustomerData customer = customerFacade.loginCustomer(openId);

				if(customer == null)
				{
					LOG.debug(openId+"未找到对应的客户主数据");
					throw new WeixinAuthenticationException(WXConstant.WX_ERR_NOT_BIND_CUST);
				}
				
				chain.doFilter(request, response);
			}
		}
		
		
      
			

	}

}
