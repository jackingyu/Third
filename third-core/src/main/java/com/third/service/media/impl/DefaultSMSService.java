package com.third.service.media.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.third.core.util.DateUtil;
import com.third.core.util.EncryptUtil;
import com.third.core.util.SmsConfig;
import com.third.dao.util.HttpClientUtil;
import com.third.model.OrderModel;
import com.third.service.media.SMSService;
import org.springframework.stereotype.Service;

@Service
public class DefaultSMSService implements SMSService {

	private Log log = LogFactory.getLog(getClass());

	public String server  = SmsConfig.getInstance().getProperty("rest_server");
	public String version = SmsConfig.getInstance().getProperty("version");
	public String bindTemplateID = SmsConfig.getInstance().getProperty("bindtemplateid");
	public String accountSid = SmsConfig.getInstance().getProperty("accountsid");
	public String authToken	= SmsConfig.getInstance().getProperty("authtoken");
	public String appId	= SmsConfig.getInstance().getProperty("appid");
	public String notifyTemplateID = SmsConfig.getInstance().getProperty("notifytemplateid");
	
	@Override
	public boolean sendBindSMS( String to, String param ) throws IOException  {
		
		String result = "";
		CloseableHttpClient httpClient = HttpClientUtil.createSSLClientDefault();
		try {
			//MD5加密
			EncryptUtil encryptUtil = new EncryptUtil();
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String signature =getSignature(accountSid,authToken,timestamp,encryptUtil);
			String url = getStringBuffer().append("/").append(version)
					.append("/Accounts/").append(accountSid)
					.append("/Messages/templateSMS")
					.append("?sig=").append(signature).toString();
			TemplateSMS templateSMS=new TemplateSMS();
			templateSMS.setAppId(appId);
			templateSMS.setTemplateId(bindTemplateID);
			templateSMS.setTo(to);
			templateSMS.setParam(param);
			
			ObjectMapper mapper = new ObjectMapper();

			String body = mapper.writeValueAsString(templateSMS);
			body="{\"templateSMS\":"+body+"}";
			log.debug(body);
			HttpResponse response=post("application/json",accountSid, authToken, timestamp, url, httpClient, encryptUtil, body);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
				log.info(result);
				//判断发送是否成功
				mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(result);
				if( rootNode != null ){
					return true;
				} else {
					log.error("短信发送失败");
				}
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
			httpClient.close();
		}
		return true;
	}
	
	public String getSignature(String accountSid, String authToken,String timestamp,EncryptUtil encryptUtil) throws Exception{
		String sig = accountSid + authToken + timestamp;
		String signature = encryptUtil.md5Digest(sig);
		return signature;
	}
	
	public StringBuffer getStringBuffer() {
		StringBuffer sb = new StringBuffer("https://");
		sb.append(server);
		return sb;
	}
	
	public HttpResponse post(String cType,String accountSid,String authToken,String timestamp,String url,CloseableHttpClient httpclient,EncryptUtil encryptUtil,String body) throws Exception{
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Accept", cType);
		httppost.setHeader("Content-Type", cType+";charset=utf-8");
		String src = accountSid + ":" + timestamp;
		String auth = encryptUtil.base64Encoder(src);
		httppost.setHeader("Authorization", auth);
		BasicHttpEntity requestBody = new BasicHttpEntity();
        requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
        requestBody.setContentLength(body.getBytes("UTF-8").length);
        httppost.setEntity(requestBody);
        // 执行客户端请求
		HttpResponse response = httpclient.execute(httppost);
		return response;
	}

	@Override
	public boolean sendNotifySMS(OrderModel order) throws  IOException {
		
//		if( order == null ){
//			return false;
//		}
//		
//		log.debug("订单到店通知:" + order.getId());
//		String to = order.getCustomer().getTel1();
//		if( to == null || to.length() != 11 ){
//			log.error("手机号不正确:" + to);
//			return false;
//		}
//		String param = "";
//		//姓名+订单号+门店名称
//		param = order.getCustomer().getName() + "," +  order.getOrderNo() + "," + order.getStore().getStoreName();
//		
//		String result = "";
//		CloseableHttpClient httpClient = HttpClientUtil.createSSLClientDefault();
//		try {
//			//MD5加密
//			EncryptUtil encryptUtil = new EncryptUtil();
//			// 构造请求URL内容
//			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);// 时间戳
//			String signature =getSignature(accountSid,authToken,timestamp,encryptUtil);
//			String url = getStringBuffer().append("/").append(version)
//					.append("/Accounts/").append(accountSid)
//					.append("/Messages/templateSMS")
//					.append("?sig=").append(signature).toString();
//			TemplateSMS templateSMS = new TemplateSMS();
//			templateSMS.setAppId(appId);
//			templateSMS.setTemplateId(notifyTemplateID);
//			templateSMS.setTo(to);
//			templateSMS.setParam(param);
//			
//			ObjectMapper mapper = new ObjectMapper();
//
//			String body = mapper.writeValueAsString(templateSMS);
//			body="{\"templateSMS\":"+body+"}";
//			log.debug(body);
//			HttpResponse response=post("application/json",accountSid, authToken, timestamp, url, httpClient, encryptUtil, body);
//			HttpEntity entity = response.getEntity();
//			if (entity != null) {
//				result = EntityUtils.toString(entity, "UTF-8");
//				log.info(result);
//				//判断发送是否成功
//				mapper = new ObjectMapper();
//				JsonNode rootNode = mapper.readTree(result);
//				if( rootNode != null ){
//					return true;
//				} else {
//					log.error("短信发送失败");
//				}
//			}
//			EntityUtils.consume(entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{
//			// 关闭连接
//			httpClient.close();
//		}
		return true;
	}

}
