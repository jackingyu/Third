package com.third.facade.data;

/**
 * Father of all data,handle the attribute of PK
 */
public abstract class AbstractData {
	protected String pk;

	public String getPk()
	{
		return pk;
	}

	public void setPk(String pk)
	{
		this.pk = pk;
	}

}
