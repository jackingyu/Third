package com.third.service.product;

import java.util.Map;

import com.third.dao.util.PaginationSupport;
import com.third.model.ProductModel;


public interface ProductService
{
	ProductModel getProductForCode(final String code);

	void createProduct(final ProductModel product);

	void updateProduct(final ProductModel product);
	
	PaginationSupport getProductList(Map<String, String> sp, Integer startIndex, Integer pageSize);
}
