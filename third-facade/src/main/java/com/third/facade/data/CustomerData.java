package com.third.facade.data;

import java.util.Date;


public class CustomerData extends AbstractData
{
	private String name;
	private String cellphone;
	private Date birthday;
	private Date weddingday;
	private SourceData source;
	private String comment;
	private String email;
	private String QQ;
	private AddressData address;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCellphone()
	{
		return cellphone;
	}

	public void setCellphone(String cellphone)
	{
		this.cellphone = cellphone;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}



	public AddressData getAddress()
	{
		return address;
	}

	public void setAddress(AddressData address)
	{
		this.address = address;
	}

	public void setQQ(String qQ)
	{
		QQ = qQ;
	}

	public String getQQ()
	{
		return QQ;
	}

	public void setWeddingday(Date weddingday)
	{
		this.weddingday = weddingday;
	}

	public void setSource(SourceData source)
	{
		this.source = source;
	}

	public Date getWeddingday()
	{
		return weddingday;
	}

	public SourceData getSource()
	{
		return source;
	}

}
