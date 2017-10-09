package com.third.web.utils;

public class Message {

	private boolean success;

	public Message(boolean b)
	{
		this.success = b;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}
}
