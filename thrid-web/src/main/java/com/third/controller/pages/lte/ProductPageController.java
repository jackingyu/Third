package com.third.controller.pages.lte;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.core.util.DataTableCriterias;
import com.third.facade.product.ProductFacade;


@Controller
public class ProductPageController
{
	private static final Logger LOG = Logger.getLogger(ProductPageController.class);

	@Resource(name="productFacade")
	private ProductFacade productFacade;
	
	@RequestMapping(value = "/product/productlist", method = RequestMethod.GET)
	@ResponseBody
	public Object getProductList(@RequestParam(value = "productCode", required = false) final String productCode,
			@RequestParam(value = "productTitle", required = false) final String productTitle,
			@RequestParam(value = "category", required = false) final String category,
			final DataTableCriterias criterias)
	{
		return productFacade.getProductList(productCode, productTitle,category,criterias.getStart(),criterias.getLength());
	}

	

}
