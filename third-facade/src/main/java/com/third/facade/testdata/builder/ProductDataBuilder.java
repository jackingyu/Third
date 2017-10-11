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
	private String filename;
	
	@Resource(name = "productService")
	ProductService productService;

	@Resource(name = "categoryService")
	CategoryService categoryService;

	@Resource(name = "productGroupService")
	ProductGroupService productGroupService;

	@Override
	public void buildData()
	{
		List<String[]> results = ExcelFileReader.readFile(filename, 4);
		
		buildCategories();
		List<CategoryModel> categories = categoryService.getCategories();

		results.forEach(r->{
	    	   String[] item = r;
	    	   ProductGroupModel pgm = productGroupService.getProductGroupByName(r[2]);
	    	   
	    	   if(pgm == null)
	    	   {
	    		   pgm = buildProductGroup(r[2]);
	    	   }
	    	   
	    	   ProductModel product = new ProductModel();
	    	   product.setProductGroup(pgm);
	    	   product.setProducttitle(r[1]);
	    	   product.setCode(r[0]);
	    	   product.setCategory(categoryService.getCategoryForCode(r[3]));
	    	   productService.saveProduct(product);
	    });

	}

	private void buildCategories()
	{
	    buildCategory("A", "西服");
		buildCategory("B", "西裤");
		buildCategory("C", "衬衫");
		buildCategory("D", "马甲");
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

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

}
