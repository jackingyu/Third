package com.third.service.product.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.third.dao.product.ProductGroupDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.ProductGroupModel;
import com.third.service.product.ProductGroupService;


public class DefaultProductGroupService implements ProductGroupService
{
	private ProductGroupDao productGroupDao;

	@Override
	public void createProductGroup(ProductGroupModel productGroup)
	{
		productGroupDao.save(productGroup);
	}

	@Override
	public PaginationSupport getProductGroupList(Integer startIndex, Integer pageSize)
	{
		return getProductGroupList(startIndex, pageSize);
	}

	public void setProductGroupDao(ProductGroupDao productGroupDao)
	{
		this.productGroupDao = productGroupDao;
	}
	
}
