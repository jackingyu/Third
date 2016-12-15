package com.third.controller.weixin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.facade.customer.WeixinFacade;
import com.third.facade.data.WXMessage;



@Controller
@RequestMapping("/wx")
public class WeixinCallbackController
{
	private String TOKEN = "blaimar";
	private static final Logger LOG = Logger.getLogger(WeixinCallbackController.class);
	@Resource(name = "weixinFacade")
	private WeixinFacade weixinFacade;

	@RequestMapping(value = "/callback")
	public @ResponseBody Object callback(@RequestParam(value = "signature", defaultValue = StringUtils.EMPTY) final String signature,
			@RequestParam(value = "timestamp", defaultValue = StringUtils.EMPTY) final String timestamp,
			@RequestParam(value = "nonce", defaultValue = StringUtils.EMPTY) final String nonce, 
			@RequestParam(value = "echostr", defaultValue = StringUtils.EMPTY,required=false) final String echostr,
			@RequestParam(value = "openid",required=false) final String openid,
			final HttpServletRequest request,
			final HttpServletResponse response) throws IOException
	{
		LOG.debug("signature=" + signature);
		LOG.debug("echostr=" + echostr);
		LOG.debug("timestamp=" + timestamp);
		LOG.debug("nonce=" + nonce);

		// 确认请求来至微信
		if (checkSignature(signature, timestamp, nonce))
		{
			//首次接入模式
			if (StringUtils.isNotEmpty(echostr))
			{
				response.reset();
				response.getWriter().write(echostr);
				return null;
			}
			else
			{
				//解析xml
				ServletInputStream in = request.getInputStream();
				WXMessage wxMsg = new WXMessage();

				Document document = readXML(in, wxMsg);

				String msgType = wxMsg.getMsgType();
				LOG.debug(msgType);

				if (null != msgType)
				{
					if (msgType.equals(WXConstant.msgType_event))
					{
						LOG.debug("微信：处理事件");
						LOG.debug(wxMsg.getEvent());
						LOG.debug(wxMsg.getFromUserName());

						//关注事件
						if (wxMsg.getEvent() != null && wxMsg.getEvent().equals(WXConstant.event_subscribe))
						{
							this.processSubscribe(wxMsg);
							//回复图文消息
							String xml = weixinFacade.getSubscribeMsg(wxMsg);
							LOG.debug("回复给用户的XML是：" + xml);
							return xml;
						}
					}
					else
					{
						LOG.debug("将该消息转发至多客服系统");
						String xml = weixinFacade.getTransferMsg(wxMsg);
						LOG.debug("回复给用户的XML是：" + xml);
						return xml;
					}
				}
				return "";
			}
		}
		else
		{
			return "";
		}

	}

	private boolean checkSignature(String signature, String timestamp, String nonce)
	{

		ArrayList<String> stringList = new ArrayList<String>();
		stringList.add(TOKEN);
		stringList.add(timestamp);
		stringList.add(nonce);

		Collections.sort(stringList);

		String[] tmpStrArray = new String[3];
		stringList.toArray(tmpStrArray);

		String tmpStr = StringUtils.join(tmpStrArray);
		tmpStr = DigestUtils.shaHex(tmpStr);

		if (signature.equals(tmpStr))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private void processSubscribe( WXMessage wxMsg ){
		weixinFacade.subscribe(wxMsg.getFromUserName());
	}
	
	private Document readXML(ServletInputStream in, WXMessage wxMsg)
	{

		SAXReader saxReader = new SAXReader();
		Document document = null;

		try
		{
			document = saxReader.read(new InputStreamReader(in, "UTF-8"));

			LOG.debug(document.asXML());

			Element root = document.getRootElement();

			List<Element> elementList = root.elements();

			for (Element e : elementList)
			{

				String name = e.getName();
				String text = e.getText();

				if (name.equals("ToUserName"))
					wxMsg.setToUserName(text);
				if (name.equals("FromUserName"))
					wxMsg.setFromUserName(text);
				if (name.equals("CreateTime"))
					wxMsg.setCreateTime(text);
				if (name.equals("MsgType"))
					wxMsg.setMsgType(text);
				if (name.equals("Event"))
					wxMsg.setEvent(text);
			}

		}
		catch (UnsupportedEncodingException e)
		{
			LOG.error(e);
		}
		catch (DocumentException e)
		{
			LOG.error(e);
		}
		return document;
	}
}
