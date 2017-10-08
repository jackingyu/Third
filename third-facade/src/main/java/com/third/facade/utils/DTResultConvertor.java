package com.third.facade.utils;

import java.util.List;

import com.third.dao.util.PaginationSupport;
import com.third.facade.data.DTResults;

public class DTResultConvertor {
	public static DTResults convertPS2DT(PaginationSupport ps)
	{
		DTResults r = new DTResults();
		r.setRecordsFiltered(ps.getTotalCount());
		r.setRecordsTotal(ps.getTotalCount());
		r.setData(ps.getItems());
		return r;
	}
	
	public static DTResults convertPS2DT(List<Object[]> result)
	{
		DTResults r = new DTResults();
		r.setRecordsFiltered(result.size());
		r.setRecordsTotal(result.size());
		r.setData(result);
		return r;
	}

}
