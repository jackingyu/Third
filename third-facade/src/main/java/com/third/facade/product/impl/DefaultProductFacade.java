package com.third.facade.product.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.third.dao.util.PaginationSupport;
import com.third.facade.data.CategoryData;
import com.third.facade.data.DTResults;
import com.third.facade.data.ProductData;
import com.third.facade.data.ProductGroupData;
import com.third.facade.populator.CategoryDataPopulator;
import com.third.facade.populator.ProductDataPopulator;
import com.third.facade.populator.ProductGroupDataPopulator;
import com.third.facade.product.ProductFacade;
import com.third.facade.utils.DTResultConvertor;
import com.third.model.CategoryModel;
import com.third.model.ProductGroupModel;
import com.third.model.ProductModel;
import com.third.service.product.CategoryService;
import com.third.service.product.ProductGroupService;
import com.third.service.product.ProductService;


public class DefaultProductFacade implements ProductFacade
{
	private ProductService productService;
	private CategoryService categoryService;
	private CategoryDataPopulator categoryDataPopulator;
	private ProductDataPopulator productDataPopulator;
	private ProductGroupService productGroupService;
	private ProductGroupDataPopulator productGroupDataPopulator;
	
	@Override
	public ProductData getProductForCode(final String productCode)
	{
		ProductModel productModel = productService.getProductForCode(productCode);
		ProductData productData = new ProductData();
		productDataPopulator.populate(productModel, productData);
		
		return productData;
	}
	@Override
	public DTResults getProductList(Map<String, String> sp,final Integer startIndex, final Integer pageSize)
	{
		PaginationSupport ps = productService.getProductList(sp,startIndex, pageSize);
	   
		return DTResultConvertor.convertPS2DT(ps);
	}
	
	@Override
	public DTResults getProductList1(Map<String, String[]> sp,final Integer startIndex, final Integer pageSize)
	{
		PaginationSupport ps = productService.getProductList1(sp,startIndex, pageSize);
		
		return DTResultConvertor.convertPS2DT(ps);
	}

	@Override
	public List<ProductGroupData> getProductGroups()
	{
		List<ProductGroupModel> productGroupModels = productGroupService.getProductGroupList();
		List<ProductGroupData> productGroups = new ArrayList<ProductGroupData>();
		
		productGroupModels.forEach( p ->{
			ProductGroupData pgd = new ProductGroupData();
			productGroupDataPopulator.populate(p, pgd);
			productGroups.add(pgd);
		});
		
		return productGroups;
	}
	
	@Override
	public List<CategoryData> getCategories(){
		List<CategoryModel> categoryModels = categoryService.getCategories();
		List<CategoryData> categories = new ArrayList<CategoryData>();
		
		categoryModels.forEach( c->{
			CategoryData cd = new CategoryData();
			categoryDataPopulator.populate(c, cd);
			categories.add(cd);
		});
		
		return categories;
	}
	
	public void setProductService(ProductService productService)
	{
		this.productService = productService;
	}

	public void setProductGroupService(ProductGroupService productGroupService)
	{
		this.productGroupService = productGroupService;
	}

	public void setProductGroupDataPopulator(ProductGroupDataPopulator productGroupDataPopulator)
	{
		this.productGroupDataPopulator = productGroupDataPopulator;
	}

	public void setCategoryService(CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	public void setCategoryDataPopulator(CategoryDataPopulator categoryDataPopulator)
	{
		this.categoryDataPopulator = categoryDataPopulator;
	}
	public void setProductDataPopulator(ProductDataPopulator productDataPopulator)
	{
		this.productDataPopulator = productDataPopulator;
	}
	@Override
	public void saveProduct(ProductData productData)
	{
		ProductModel productModel = null;
		if(StringUtils.isNotEmpty(productData.getCode()))
			productModel = productService.getProductForCode(productData.getCode());
		
		if(productModel == null)
			productModel = new ProductModel();
		
		ProductGroupModel productGroupModel = productGroupService.getProductGroupByPK(productData.getProductGroup().getPk());
		CategoryModel categoryModel = categoryService.getCategoryForCode(productData.getCategory().getCode());
		
		productModel.setCategory(categoryModel);
		productModel.setProductGroup(productGroupModel);
		productModel.setProducttitle(productData.getProducttitle());
		productModel.setCode(productData.getCode());
		
		productService.saveProduct(productModel);
	}
	
}
