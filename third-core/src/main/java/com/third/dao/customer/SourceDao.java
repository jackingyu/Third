package com.third.dao.customer;

import java.util.List;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.SourceModel;


public interface SourceDao extends IGenericDAO<SourceModel, String>
{

	public List<SourceModel> findSourceByName(final String name);
	public boolean checkIfExist(final String name);
}
