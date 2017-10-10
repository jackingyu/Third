package com.third.dao.product;

import java.util.List;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.ProductGroupModel;

public interface ProductGroupDao
		extends IGenericDAO<ProductGroupModel, String> {
	public List<ProductGroupModel> getProductGroupList();

	List<ProductGroupModel> findProductGroupByName(String name);
}
