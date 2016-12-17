package com.third.service.customer.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.third.core.util.WXConstant;
import com.third.dao.customer.WeixinInfoDao;
import com.third.dao.util.HttpClientUtil;
import com.third.model.AccessToken;
import com.third.model.SubscribeData;
import com.third.model.WeixinInfoModel;
import com.third.service.customer.WeixinService;



public class DefaultWeixinService implements WeixinService
{
	private static final Logger LOG = Logger.getLogger(DefaultWeixinService.class);
	private WeixinInfoDao weixinInfoDao;
	private WeixinInfoModel weixinInfo;
	private AccessToken accessToken;
	
	//private String templateID = WXConfig.getInstance().getProperty("wx_tempid");

	public AccessToken getWXAccessToken() throws IOException
	{

		CloseableHttpClient httpClient = HttpClientUtil.createSSLClientDefault();
		HttpGet get = new HttpGet();
		AccessToken accessToken = new AccessToken();
		try
		{
			WeixinInfoModel wxInfo = getWeixinInfo(1);
			
			if(wxInfo==null)
				LOG.fatal("请补充weixininfo appID appsecret!");
			
			String uri = new StringBuffer().append(WXConstant.getAccessTokenURL).append("&appid=").append(wxInfo.getAppId())
					.append("&secret=").append(wxInfo.getAppSecret()).toString();
			LOG.debug("请求新的Access Token");
			LOG.debug(uri);
			get.setURI(new URI(uri));
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			LOG.debug(content);
			if (null != content)
			{
				//解析Json格式
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(content);

				//异常处理
				JsonNode node = rootNode.get("errcode");
				if (node != null && "40001".equals(node.textValue()))
				{
					return null;
				}

				node = rootNode.get("access_token");
				if (node != null)
				{
					accessToken.setAccessToken(node.textValue());
				}

				node = rootNode.get("expires_in");
				if (node != null)
				{
					accessToken.setExpires(node.intValue());
				}

				long currentTime = System.currentTimeMillis();
				accessToken.setRequestTime(currentTime);
				accessToken.setRequestDate(new Date(currentTime));
			}
		}
		catch (URISyntaxException e)
		{
			LOG.error(e);
		}
		catch (ClientProtocolException e)
		{
			LOG.error(e);
		}
		finally
		{
			httpClient.close();
		}
		return accessToken;
	}

	public String getOpenID(String code) throws IOException
	{

		CloseableHttpClient httpClient = HttpClientUtil.createSSLClientDefault();
		HttpGet get = new HttpGet();

		String openID = null;

		WeixinInfoModel wxInfo = getWeixinInfo(1);
		String uri = new StringBuffer().append(WXConstant.getOAuthTokenURL).append("&appid=").append(wxInfo.getAppId())
				.append("&secret=").append(wxInfo.getAppSecret()).append("&code=").append(code).toString();
		LOG.debug("请求用户Access Token");
		LOG.debug(uri);

		try
		{
			get.setURI(new URI(uri));
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			if (null != content)
			{
				//解析Json格式
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(content);
				JsonNode node = rootNode.get("openid");

				if (node != null)
				{
					openID = node.textValue();
					LOG.debug("Open ID = " + openID);
				}
				else
				{
					LOG.debug("不能获取用户openID");
				}
			}
		}
		catch (URISyntaxException e)
		{
			LOG.error(e);
		}
		catch (ClientProtocolException e)
		{
			LOG.error(e);
		}
		finally
		{
			httpClient.close();
		}

		return openID;
	}

	@Override
	public SubscribeData getSubscribe(String openid) throws IOException
	{

		SubscribeData subscribe = new SubscribeData();

		CloseableHttpClient httpClient = HttpClientUtil.createSSLClientDefault();
		HttpGet get = new HttpGet();
		String uri = new StringBuffer().append(WXConstant.getUserInfoURL).append("access_token=")
				.append(this.getAccessToken().getAccessToken()).append("&openid=").append(openid).append("&lang=zh_CN").toString();
		LOG.debug("获取用户基本信息");
		LOG.debug(uri);

		try
		{
			get.setURI(new URI(uri));
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "utf-8");
			LOG.debug("内容为：" + content);
			if (null != content)
			{
				//解析Json格式
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(content);

				JsonNode node = rootNode.get("openid");
				if (node != null)
				{
					subscribe.setOpenId(node.textValue());
				}

				node = rootNode.get("city");
				if (node != null)
				{
					subscribe.setCity(node.textValue());
				}

				node = rootNode.get("country");
				if (node != null)
				{
					subscribe.setCountry(node.textValue());
				}

				node = rootNode.get("province");
				if (node != null)
				{
					subscribe.setProvince(node.textValue());
				}
				;

				node = rootNode.get("nickname");
				if (node != null)
				{
					subscribe.setNickName(node.textValue());
				}

				node = rootNode.get("sex");
				if (node != null)
				{
					subscribe.setSex(node.intValue());
				}

				node = rootNode.get("headimgurl");
				if (node != null)
				{
					subscribe.setHeadimgurl(node.textValue());
				}

				node = rootNode.get("subscribe_time");
				if (node != null)
				{
					subscribe.setTime(new Date(node.longValue() * 1000L));
				}
			}
		}
		catch (URISyntaxException e)
		{
			LOG.error(e);
		}
		catch (ClientProtocolException e)
		{
			LOG.error(e);
		}
		finally
		{
			httpClient.close();
		}

		return subscribe;
	}

	@Override
	public WeixinInfoModel getWeixinInfo(Integer id)
	{
		return weixinInfoDao.get(id);
	}

	@Override
	public void saveWeixinInfo(WeixinInfoModel wxInfo)
	{
		weixinInfoDao.save(wxInfo);
	}

	public synchronized AccessToken getAccessToken()  {
		if( null == accessToken || accessToken.isValid() == false ){
			//Read from WX service
			try {
				this.accessToken = this.getWXAccessToken();
				
			} catch (IOException e) {
				LOG.error(e);
			}
		}
		return this.accessToken;
	}
	
	public synchronized AccessToken refreshAccessToken() {
		try {
			this.accessToken = this.getWXAccessToken();
			
		} catch (IOException e) {
			LOG.error(e);
		}
		return this.accessToken;
	}

	public void setWeixinInfoDao(WeixinInfoDao weixinInfoDao)
	{
		this.weixinInfoDao = weixinInfoDao;
	}

}
