package com.third.dao.product;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.ProductModel;


public interface ProductDao extends IGenericDAO<ProductModel, String>
{
	ProductModel getProductForCode(final String code);

	PaginationSupport getProductList(String productCode, String productTitle, String category,Integer startIndex, Integer pageSize);

}
