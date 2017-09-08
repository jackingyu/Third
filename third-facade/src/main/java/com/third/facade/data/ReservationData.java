package com.third.facade.data;

import java.util.Date;

public class ReservationData extends AbstractData {
	private String name;
	private String cellphone;
	private Date reservationDate;
	private String channel;
	private String channelText;
	private StoreData store;
	private CustomerData customer;
	private String comment;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getReservationDate()
	{
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate)
	{
		this.reservationDate = reservationDate;
	}

	public String getChannel()
	{
		return channel;
	}

	public void setChannel(String channel)
	{
		this.channel = channel;
	}

	public String getChannelText()
	{
		return channelText;
	}

	public void setChannelText(String channelText)
	{
		this.channelText = channelText;
	}

	public StoreData getStore()
	{
		return store;
	}

	public void setStore(StoreData store)
	{
		this.store = store;
	}

	public CustomerData getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerData customer)
	{
		this.customer = customer;
	}

	public String getCellphone()
	{
		return cellphone;
	}

	public void setCellphone(String cellphone)
	{
		this.cellphone = cellphone;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

}
