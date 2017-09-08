package com.third.model;

import java.util.Date;

public class AccessToken {

	private String accessToken;
	private long requestTime;
	private Date requestDate;
	private int expires;

	public int getExpires()
	{
		return expires;
	}

	public void setExpires(int expires)
	{
		this.expires = expires;
	}

	public String getAccessToken()
	{
		return accessToken;
	}

	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}

	public long getRequestTime()
	{
		return requestTime;
	}

	public void setRequestTime(long requestTime)
	{
		this.requestTime = requestTime;
	}

	public Date getRequestDate()
	{
		return requestDate;
	}

	public void setRequestDate(Date requestDate)
	{
		this.requestDate = requestDate;
	}

	public boolean isValid()
	{
		// 取得系统当天时间
		long currentTime = System.currentTimeMillis();
		if ((currentTime - requestTime) > (expires - 2000) * 1000)
		{ // 间隔大于5200秒
			return false;
		} else
		{
			return true;
		}
	}
}
