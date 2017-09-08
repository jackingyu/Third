package com.third.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTableCriterias {

	private int draw;

	private int start;

	private int length;

	private Map<SearchCriterias, String> search;

	private List<Map<OrderCriterias, String>> order;

	private Map<String, Object> queryParam = new HashMap<String, Object>();

	private String[] groupCondition;

	public enum SearchCriterias {
		value, regex
	}

	public enum OrderCriterias {
		column, dir
	}

	public int getDraw()
	{
		return draw;
	}

	public void setDraw(int draw)
	{
		this.draw = draw;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public Map<SearchCriterias, String> getSearch()
	{
		return search;
	}

	public void setSearch(Map<SearchCriterias, String> search)
	{
		this.search = search;
	}

	public List<Map<OrderCriterias, String>> getOrder()
	{
		return order;
	}

	public void setOrder(List<Map<OrderCriterias, String>> order)
	{
		this.order = order;
	}

	public Map<String, Object> getQueryParam()
	{
		return queryParam;
	}

	public void setQueryParam(Map<String, Object> queryParam)
	{
		this.queryParam = queryParam;
	}

	public String[] getGroupCondition()
	{
		return groupCondition;
	}

	public void setGroupCondition(String... groupCondition)
	{
		this.groupCondition = groupCondition;
	}

}