package com.third.core.util;

import java.util.List;

import javax.annotation.Resource;

import com.third.dao.generic.TextMessageDao;
import com.third.model.TextMessageModel;

public class MessageUtils {
	
	@Resource(name="textMessageDao")
	private TextMessageDao textMessageDao;
	
	public void createMessage(final String cellphone,final String message)
	{
		TextMessageModel textMessage = new TextMessageModel();
        textMessage.setReceiver(cellphone);
		textMessage.setMessage(message);
		textMessage.setSent(Boolean.FALSE);
		textMessage.setType("text");
		textMessageDao.save(textMessage);
	}
	
	public void sentMessage()
	{
		List<TextMessageModel> messages = textMessageDao.find("from TextMessageModel where sent = false");
		for(int i = 0; i < messages.size();i++)
		{
			TextMessageModel message = messages.get(i);
			message.setSent(true);
		}
		
		textMessageDao.saveOrUpdateList(messages);
	}
	
}
