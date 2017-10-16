package com.third.facade.customer.impl;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.third.core.util.Config;
import com.third.core.util.WXConstant;
import com.third.dao.util.SubscribeData;
import com.third.facade.customer.WeixinFacade;
import com.third.facade.data.WXMessage;
import com.third.model.SubscribeModel;
import com.third.service.customer.SubscribeService;
import com.third.service.customer.WeixinService;

public class DefaultWeixinFacade implements WeixinFacade {
	private static final Logger LOG = Logger
			.getLogger(DefaultWeixinFacade.class);
	private SubscribeService subscribeService;
	private WeixinService weixinService;
	private final static String wx_title = "\u6B22\u8FCE\u5173\u6CE8\u94C2\u739B\u7537\u58EB\u793C\u670D";
	private final static String wx_desc = "\u4E13\u4E1A\u91CF\u8EAB\u5B9A\u5236\u7ED3\u5A5A\u793C\u670D\u3001\u5546\u52A1\u897F\u670D\uFF01";
	private final static String wx_picurl = "http://blaimar.xnorth.cn/BLAIMAR/wx/img/subscribe1.jpg";
	private final static String wx_url = "http://blaimar.xnorth.cn/BLAIMAR/wx/welcome";
	private final static String wx_tempid = "XjNukY8nzjEzWgxok3eVKl-XUSaD9HCMWNwAqm14q70";

	@Override
	public String getTransferMsg(WXMessage wxMsg)
	{
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xml");

		// Build head information
		int time = (int) System.currentTimeMillis();
		root.addElement("ToUserName").addCDATA(wxMsg.getFromUserName());
		root.addElement("FromUserName").addCDATA(wxMsg.getToUserName());
		root.addElement("CreateTime").addCDATA(String.valueOf(time));
		root.addElement("MsgType").addCDATA(WXConstant.msgType_transfer);

		return document.asXML();
	}

	@Override
	public boolean subscribe(String openid)
	{

		SubscribeData subscribeData = null;

		try
		{
			subscribeData = weixinService.getSubscribe(openid);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SubscribeModel subscribeModel = new SubscribeModel();

		subscribeModel.setOpenId(subscribeData.getOpenId());
		subscribeModel.setNickName(subscribeData.getNickName());
		subscribeModel.setSex(subscribeData.getSex());
		subscribeModel.setCity(subscribeData.getCity());
		subscribeModel.setCountry(subscribeData.getCountry());
		subscribeModel.setProvince(subscribeData.getProvince());
		subscribeModel.setHeadimgurl(subscribeData.getHeadimgurl());
		subscribeModel.setTime(subscribeData.getTime());
		subscribeModel.setUnionId(subscribeData.getUnionId());

		subscribeService.save(subscribeModel);
		LOG.debug("保存关注列表成功");

		return true;
	}

	@Override
	public String getSubscribeMsg(WXMessage wxMsg)
	{

		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xml");

		// Build head information
		int time = (int) System.currentTimeMillis();
		root.addElement("ToUserName").addCDATA(wxMsg.getFromUserName());
		root.addElement("FromUserName").addCDATA(wxMsg.getToUserName());
		root.addElement("CreateTime").addCDATA(String.valueOf(time));
		root.addElement("MsgType").addCDATA(WXConstant.msgType_news);
		root.addElement("ArticleCount").addText("1");

		// 以后修改成从数据库中读取
		Element item = root.addElement("Articles").addElement("item");
		item.addElement("Title").addCDATA(wx_title);
		item.addElement("Description").addCDATA(wx_desc);
		item.addElement("PicUrl").addCDATA(wx_picurl);
		item.addElement("Url").addCDATA(wx_url);

		return document.asXML();
	}

	public void setSubscribeService(SubscribeService subscribeService)
	{
		this.subscribeService = subscribeService;
	}

	public void setWeixinService(WeixinService weixinService)
	{
		this.weixinService = weixinService;
	}

	@Override
	public String getOpenId(String code)
	{
		try
		{
			return weixinService.getOpenID(code);
		} catch (IOException e)
		{
			e.printStackTrace();

			if (Config.getParameter("weixin.enabledev") != null
					&& (boolean) Config.getParameter("weixin.enabledev"))
			{
				return "mockupopenid";
			}
		}

		return StringUtils.EMPTY;
	}

}
