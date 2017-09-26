package com.third.facade.cronjob;

import javax.annotation.Resource;

import com.third.core.util.MessageUtils;

public class SMSSender {
	
	@Resource(name="messageUtils")
	private MessageUtils messageUtils;

	public void send()
	{
		messageUtils.sentMessage();
	}
}
