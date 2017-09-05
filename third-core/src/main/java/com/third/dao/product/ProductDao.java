package com.third.dao.product;

import java.util.Map;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.ProductModel;


public interface ProductDao extends IGenericDAO<ProductModel, String>
{
	ProductModel getProductForCode(final String code);

	PaginationSupport getProductList(Map<String, String> sp, Integer startIndex, Integer pageSize);

	PaginationSupport getProductList1(Map<String, String[]> sp, Integer startIndex, Integer pageSize);

}
