package com.third.service.media;

import java.io.IOException;

import com.third.model.OrderModel;


public interface SMSService {
	boolean sendBindSMS( String to, String param) throws  IOException;
	boolean sendNotifySMS(OrderModel order ) throws IOException;
}
