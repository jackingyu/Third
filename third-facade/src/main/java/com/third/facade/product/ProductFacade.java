package com.third.facade.product;

import java.util.List;
import java.util.Map;

import com.third.facade.data.CategoryData;
import com.third.facade.data.DTResults;
import com.third.facade.data.ProductData;
import com.third.facade.data.ProductGroupData;

public interface ProductFacade {
	DTResults getProductList(Map<String, String> sp, Integer startIndex,
			Integer pageSize);

	List<ProductGroupData> getProductGroups();

	DTResults getProductList1(Map<String, String[]> sp, Integer startIndex,
			Integer pageSize);

	List<CategoryData> getCategories();

	ProductData getProductForCode(final String productCode);

	void saveProduct(ProductData productData);
}
