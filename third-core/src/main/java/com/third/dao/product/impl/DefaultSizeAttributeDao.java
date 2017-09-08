package com.third.dao.product.impl;

import java.util.List;

import com.third.dao.generic.GenericDAO;
import com.third.dao.product.SizeAttributeDao;
import com.third.model.SizeAttributeModel;

public class DefaultSizeAttributeDao extends
		GenericDAO<SizeAttributeModel, String> implements SizeAttributeDao {
	private final static String FIND_BY_ITEMCATEGORY_SQL = "from com.third.model.SizeAttributeModel s where s.itemCategory=?";

	@Override
	public List<SizeAttributeModel> findSizeAttributeForItemCategory(
			Integer itemCategory)
	{
		return find(FIND_BY_ITEMCATEGORY_SQL, itemCategory);
	}

}
