package com.third.service.product.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.third.dao.product.ProductGroupDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.ProductGroupModel;
import com.third.service.product.ProductGroupService;
import org.springframework.stereotype.Service;

@Service
public class DefaultProductGroupService implements ProductGroupService {
	private ProductGroupDao productGroupDao;

	@Override
	public void createProductGroup(ProductGroupModel productGroup)
	{
		productGroupDao.save(productGroup);
	}

	@Override
	public List<ProductGroupModel> getProductGroupList()
	{
		return productGroupDao.getProductGroupList();
	}

	public void setProductGroupDao(ProductGroupDao productGroupDao)
	{
		this.productGroupDao = productGroupDao;
	}

	@Override
	public ProductGroupModel getProductGroupByPK(String pk)
	{
		return productGroupDao.get(pk);
	}

	@Override
	public ProductGroupModel getProductGroupByName(String name)
	{
		List<ProductGroupModel> productGroups = productGroupDao
				.findProductGroupByName(name);
		
		if (CollectionUtils.isEmpty(productGroups))
		{
			return null;
		}
		else
			return productGroups.get(0);
	}

}
