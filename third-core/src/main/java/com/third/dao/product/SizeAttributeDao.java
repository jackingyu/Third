package com.third.dao.product;

import java.util.List;

import com.third.dao.generic.IGenericDAO;
import com.third.model.SizeAttributeModel;


public interface SizeAttributeDao extends IGenericDAO<SizeAttributeModel, String>
{
	List<SizeAttributeModel> findSizeAttributeForItemCategory(final Integer itemCategory);

}
