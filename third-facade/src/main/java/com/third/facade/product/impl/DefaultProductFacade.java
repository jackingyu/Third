package com.third.facade.product.impl;

import java.util.List;

import com.third.dao.util.PaginationSupport;
import com.third.facade.data.DTResults;
import com.third.facade.product.ProductFacade;
import com.third.facade.utils.DTResultConvertor;
import com.third.service.product.ProductService;


public class DefaultProductFacade implements ProductFacade
{
	private ProductService productService;
	
	@Override
	public DTResults getProductList(final String productCode, final String productTitle,final Integer startIndex, final Integer pageSize)
	{
		PaginationSupport ps = productService.getProductList(productCode, productTitle,startIndex, pageSize);
	   
		return DTResultConvertor.convertPS2DT(ps);
	}

	public void setProductService(ProductService productService)
	{
		this.productService = productService;
	}
	
	
}
