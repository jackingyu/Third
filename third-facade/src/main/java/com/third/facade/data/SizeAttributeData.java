package com.third.facade.data;

public class SizeAttributeData extends AbstractData
{
	private String name;
	private String code;
	private String value;
	private String group;
	private String itemCategory;
	

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getGroup()
	{
		return group;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

	public String getCode()
	{
		return code;
	}

	public String getItemCategory()
	{
		return itemCategory;
	}

	public void setItemCategory(String itemCategory)
	{
		this.itemCategory = itemCategory;
	}

}
