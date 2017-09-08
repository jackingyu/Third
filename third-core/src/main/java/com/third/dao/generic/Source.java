package com.third.dao.generic;

public enum Source {
	Exhibition("展会", 1), CoCompany("合作伙伴", 2);

	private String name;
	private int index;

	private Source(String name, int index)
	{
		this.name = name;
		this.index = index;
	}

	public static String getName(int index)
	{
		for (Source c : Source.values())
		{
			if (c.getIndex() == index)
			{
				return c.name;
			}
		}
		return null;
	}

	// get set 方法
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

}
