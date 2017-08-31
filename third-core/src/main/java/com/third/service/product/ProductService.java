package com.third.service.product;

import com.third.dao.util.PaginationSupport;
import com.third.model.ProductModel;


public interface ProductService
{
	ProductModel getProductForCode(final String code);

	void createProduct(final ProductModel product);

	void updateProduct(final ProductModel product);
	
	PaginationSupport getProductList(String productCode, String productTitle, String category,Integer startIndex, Integer pageSize);
}
