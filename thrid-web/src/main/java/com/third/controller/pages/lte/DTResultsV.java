package com.third.controller.pages.lte;

import java.util.List;

public class DTResultsV
{
	private int recordsFiltered;
	private int recordsTotal;
	private List<String[]> data;
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
	public List<String[]> getData()
	{
		return data;
	}
	public void setData(List<String[]> data)
	{
		this.data = data;
	}

   
}
