package com.third.service.product;

import java.util.List;

import com.third.dao.util.PaginationSupport;
import com.third.model.ProductGroupModel;


public interface ProductGroupService
{
	void createProductGroup(final ProductGroupModel productGroup);

	List<ProductGroupModel> getProductGroupList();
}
