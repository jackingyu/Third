package com.third.facade.testdata.builder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;

import com.third.model.CategoryModel;
import com.third.model.ProductGroupModel;
import com.third.model.ProductModel;
import com.third.service.product.CategoryService;
import com.third.service.product.ProductGroupService;
import com.third.service.product.ProductService;

public class ProductDataBuilder implements DataBuilder {
	@Resource(name = "productService")
	ProductService productService;

	@Resource(name = "categoryService")
	CategoryService categoryService;

	@Resource(name = "productGroupService")
	ProductGroupService productGroupService;

	@Override
	public void buildData()
	{
		List<ProductGroupModel> pgm = new ArrayList<ProductGroupModel>();

		pgm.add(buildProductGroup("1000-2000"));
		pgm.add(buildProductGroup("2000-3000"));
		pgm.add(buildProductGroup("3000-4000"));
		pgm.add(buildProductGroup("4000-5000"));

		List<CategoryModel> categorys = new ArrayList<CategoryModel>();

		categorys.add(buildCategory("A", "西服"));
		categorys.add(buildCategory("B", "西裤"));
		categorys.add(buildCategory("C", "衬衫"));
		categorys.add(buildCategory("D", "马甲"));

		for (int i = 0; i < 100; i++)
			buildProduct("p-" + i, pgm, categorys);

	}

	public ProductModel buildProduct(final String code,
			final List<ProductGroupModel> pgms,
			final List<CategoryModel> categorys)
	{
		ProductModel product = new ProductModel();
		product.setCode(code);
		product.setProducttitle("商品" + code);

		product.setProductGroup(pgms.get(RandomUtils.nextInt(0, 4)));
		product.setCategory(categorys.get(RandomUtils.nextInt(0, 4)));
		productService.createProduct(product);

		return product;

	}

	public CategoryModel buildCategory(final String code, final String name)
	{
		CategoryModel category = new CategoryModel();
		category.setCode(code);
		category.setName(name);
		categoryService.createCategory(category);
		return category;
	}

	public ProductGroupModel buildProductGroup(final String name)
	{
		ProductGroupModel productGroup = new ProductGroupModel();
		productGroup.setName(name);
		productGroupService.createProductGroup(productGroup);
		return productGroup;
	}

}
