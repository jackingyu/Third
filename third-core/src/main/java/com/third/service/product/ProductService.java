package com.third.service.product;

import com.third.model.ProductModel;


public interface ProductService
{
	ProductModel getProductForCode(final String code);

	void createProduct(final ProductModel product);

	void updateProduct(final ProductModel product);

}
