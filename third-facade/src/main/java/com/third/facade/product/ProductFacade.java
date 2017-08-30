package com.third.facade.product;

import com.third.facade.data.DTResults;

public interface ProductFacade
{
	DTResults getProductList(String productCode, String productTitle, Integer startIndex, Integer pageSize);
}
