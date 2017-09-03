package com.third.controller.pages.lte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.core.util.DataTableCriterias;
import com.third.facade.product.ProductFacade;


@Controller
public class ProductPageController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(ProductPageController.class);

	@Resource(name="productFacade")
	private ProductFacade productFacade;
	
	@RequestMapping(value = "/product/productlist", method = RequestMethod.GET)
	@ResponseBody
	public Object getProductList(@RequestParam(value = "productCode", required = false) final String productCode,
			@RequestParam(value = "productTitle", required = false) final String productTitle,
			@RequestParam(value = "productGroup", required = false) final String productGroup,
			@RequestParam(value = "category", required = true) final String category,
			final DataTableCriterias criterias)
	{
		Map<String,String> sp = new HashMap<String,String>();
		sp.put("productCode", productCode);
		sp.put("productTitle", productTitle);
		sp.put("category", category);
		sp.put("productGroup", productGroup);
		
		return productFacade.getProductList(sp,criterias.getStart(),criterias.getLength());
	}

}
