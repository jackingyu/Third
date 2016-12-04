package com.third.service.product.impl;

import java.util.List;

import com.third.dao.product.SizeAttributeDao;
import com.third.model.SizeAttributeModel;
import com.third.service.product.SizeAttributeService;


public class DefaultSizeAttributeService implements SizeAttributeService
{
	private SizeAttributeDao sizeAttributeDao;

	@Override
	public void createSizeAttribute(SizeAttributeModel sizeAttributeModel)
	{
		sizeAttributeDao.save(sizeAttributeModel);
	}

	@Override
	public List<SizeAttributeModel> getSizeAttributeForItemCategory(Integer itemCategory)
	{
		return sizeAttributeDao.findSizeAttributeForItemCategory(itemCategory);
	}

	public void setSizeAttributeDao(SizeAttributeDao sizeAttributeDao)
	{
		this.sizeAttributeDao = sizeAttributeDao;
	}
}
