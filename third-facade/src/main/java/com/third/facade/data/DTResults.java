package com.third.facade.data;

import java.util.ArrayList;
import java.util.List;

public class DTResults {
	private int recordsFiltered;
	private int recordsTotal;
	private List<Object[]> data;

	public DTResults()
	{
		data = new ArrayList<Object[]>();
	}

	public int getRecordsFiltered()
	{
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered)
	{
		this.recordsFiltered = recordsFiltered;
	}

	public int getRecordsTotal()
	{
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal)
	{
		this.recordsTotal = recordsTotal;
	}

	public List<Object[]> getData()
	{
		return data;
	}

	public void setData(List<Object[]> data)
	{
		this.data = data;
	}

}
