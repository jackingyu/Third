package com.third.service.product.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.third.dao.product.ProductDao;
import com.third.dao.util.PaginationSupport;
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
	public void saveProduct(ProductModel product)
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

	@Override
	public PaginationSupport getProductList(Map<String, String> sp, final Integer startIndex, final Integer pageSize)
	{
		return productDao.getProductList(sp, startIndex, pageSize);
	}
	
	@Override
	public PaginationSupport getProductList1(Map<String, String[]> sp, final Integer startIndex, final Integer pageSize)
	{
		return productDao.getProductList1(sp, startIndex, pageSize);
	}

}
