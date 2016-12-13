package com.third.facade.data;

public class StoreData extends AbstractData
{
	private String name;
	private String code;
	private AddressData address;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public AddressData getAddress()
	{
		return address;
	}

	public void setAddress(AddressData address)
	{
		this.address = address;
	}
	
}
