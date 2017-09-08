package com.third.web.messageconvertors;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.third.facade.data.OrderData;

public class OrderInputMessageConvertor
		extends AbstractHttpMessageConverter<OrderData> {

	@Override
	protected boolean supports(Class<?> clazz)
	{
		return false;
	}

	@Override
	protected OrderData readInternal(Class<? extends OrderData> clazz,
			HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException
	{
		String temp = StreamUtils.copyToString(inputMessage.getBody(),
				Charset.forName("UTF-8"));
		System.out.println(temp);
		return null;
	}

	@Override
	protected void writeInternal(OrderData t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException
	{
		// TODO Auto-generated method stub

	}

}
