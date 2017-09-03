package com.third.facade.product.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.third.dao.util.PaginationSupport;
import com.third.facade.data.DTResults;
import com.third.facade.data.ProductGroupData;
import com.third.facade.populator.ProductGroupDataPopulator;
import com.third.facade.product.ProductFacade;
import com.third.facade.utils.DTResultConvertor;
import com.third.model.ProductGroupModel;
import com.third.service.product.ProductGroupService;
import com.third.service.product.ProductService;


public class DefaultProductFacade implements ProductFacade
{
	private ProductService productService;
	private ProductGroupService productGroupService;
	private ProductGroupDataPopulator productGroupDataPopulator;
	
	@Override
	public DTResults getProductList(Map<String, String> sp,final Integer startIndex, final Integer pageSize)
	{
		PaginationSupport ps = productService.getProductList(sp,startIndex, pageSize);
	   
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
	
}
