package com.third.dao.product;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.ProductGroupModel;


public interface ProductGroupDao extends IGenericDAO<ProductGroupModel, String>
{
	public PaginationSupport getProductGroupList(Integer startIndex, Integer pageSize);
}
