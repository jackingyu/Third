package com.third.facade.testdata.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;
import com.third.model.PaymentModel;
import com.third.model.ProductModel;
import com.third.service.order.OrderService;
import com.third.service.product.ProductService;
import com.third.service.store.StoreService;


public class ProductDataBuilder implements DataBuilder
{
	@Resource(name = "productService")
	ProductService productService;

	@Override
	public void buildData()
	{
		for (int i = 100; i < 100; i++)
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
