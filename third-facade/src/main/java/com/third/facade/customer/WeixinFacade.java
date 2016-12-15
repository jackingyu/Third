package com.third.facade.customer;

import com.third.facade.data.WXMessage;


public interface WeixinFacade
{
	//微信关注相关
	boolean subscribe(String openid);

	String getSubscribeMsg(WXMessage wxMsg);

	String getTransferMsg(WXMessage wxMsg);


}
