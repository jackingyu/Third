package com.third.facade.utils;

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

}
