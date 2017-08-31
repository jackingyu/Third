package com.third.service.product;

import com.third.dao.util.PaginationSupport;
import com.third.model.ProductGroupModel;


public interface ProductGroupService
{
	void createProductGroup(final ProductGroupModel productGroup);

	PaginationSupport getProductGroupList(Integer startIndex, Integer pageSize);
}
