package com.third.facade.data;

import java.util.Date;

public class OrderProcessRecordData extends AbstractData
{
	private String orderCode;
	private String fromStatusText;
	private String toStatusText;
	private String message;
	private Date processTime;
	private String userId;
	private String userName;

	public String getOrderCode()
	{
		return orderCode;
	}

	public void setOrderCode(String orderCode)
	{
		this.orderCode = orderCode;
	}

	public String getFromStatusText()
	{
		return fromStatusText;
	}

	public void setFromStatusText(String fromStatusText)
	{
		this.fromStatusText = fromStatusText;
	}

	public String getToStatusText()
	{
		return toStatusText;
	}

	public void setToStatusText(String toStatusText)
	{
		this.toStatusText = toStatusText;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	
	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public Date getProcessTime()
	{
		return processTime;
	}

	public void setProcessTime(Date processTime)
	{
		this.processTime = processTime;
	}

}
