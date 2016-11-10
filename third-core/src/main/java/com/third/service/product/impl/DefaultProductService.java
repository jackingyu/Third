package com.third.service.product.impl;

import com.third.dao.product.ProductDao;
import com.third.model.ProductModel;
import com.third.service.product.ProductService;


public class DefaultProductService implements ProductService
{
	private ProductDao productDao;

	@Override
	public ProductModel getProductForCode(String code)
	{
		return productDao.getProductForCode(code);
	}

	@Override
	public void createProduct(ProductModel product)
	{
		productDao.save(product);

	}

	@Override
	public void updateProduct(ProductModel product)
	{
		productDao.update(product);
	}

	public void setProductDao(ProductDao productDao)
	{
		this.productDao = productDao;
	}

}
