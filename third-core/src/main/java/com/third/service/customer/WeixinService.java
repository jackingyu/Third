package com.third.service.customer;

import java.io.IOException;

import com.third.model.AccessToken;
import com.third.model.SubscribeData;
import com.third.model.WeixinInfoModel;

public interface WeixinService {
	public WeixinInfoModel getWeixinInfo(Integer id);

	public void saveWeixinInfo(WeixinInfoModel wxInfo);

	public AccessToken getAccessToken();

	public AccessToken refreshAccessToken();

	public String getOpenID(String code) throws IOException;

	public SubscribeData getSubscribe(String openid) throws IOException;
}
