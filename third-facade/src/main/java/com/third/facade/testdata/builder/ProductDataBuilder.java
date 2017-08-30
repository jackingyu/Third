package com.third.facade.testdata.builder;

import javax.annotation.Resource;

import com.third.model.ProductModel;
import com.third.service.product.ProductService;


public class ProductDataBuilder implements DataBuilder
{
	@Resource(name = "productService")
	ProductService productService;

	@Override
	public void buildData()
	{
		for (int i = 0; i < 100; i++)
			buildProduct("p-" + i);

	}

	public ProductModel buildProduct(final String code)
	{
		ProductModel product = new ProductModel();
		product.setCode(code);
		product.setProducttitle("商品" + code);
		productService.createProduct(product);

		return product;

	}

}
