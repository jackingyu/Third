package com.third.dao.product;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.CategoryModel;


public interface CategoryDao extends IGenericDAO<CategoryModel, String>
{

	CategoryModel getCategoryForCode(String code);

}
