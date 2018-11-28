package com.third.dao.product.impl;

import java.util.List;

import com.third.dao.generic.GenericDAO;
import com.third.dao.product.ProductGroupDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.ProductGroupModel;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultProductGroupDao extends
		GenericDAO<ProductGroupModel, String> implements ProductGroupDao {
	@Override
	public List<ProductGroupModel> getProductGroupList()
	{
		return list();
	}
	
	@Override
	public List<ProductGroupModel> findProductGroupByName(final String name)
	{
		return find("from ProductGroupModel pg where pg.name = ?", name);
	}
}
