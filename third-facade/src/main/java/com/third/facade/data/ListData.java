package com.third.facade.data;

import java.util.ArrayList;
import java.util.List;


/**
 * for list style data,current is suitable for easyui grid
 */
public class ListData
{
	private Integer total = 0;

	private List<Object> rows = new ArrayList<Object>();

	public Integer getTotal()
	{
		return total;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}

	public List<Object> getRows()
	{
		return rows;
	}

	public void setRows(List<Object> rows)
	{
		this.rows = rows;
	}

}
