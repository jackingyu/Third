package com.third.controller.pages.lte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.util.DataTableCriterias;
import com.third.facade.data.CategoryData;
import com.third.facade.data.ProductData;
import com.third.facade.data.ProductGroupData;
import com.third.facade.product.ProductFacade;

@Controller
public class ProductPageController extends AbstractPageController {
	private static final Logger LOG = Logger
			.getLogger(ProductPageController.class);
	private static final String PRODUCTCODE_PATH_VARIABLE_PATTERN = "/{productCode:.*}";

	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	@RequestMapping(value = "/product/productlistpage", method = RequestMethod.GET)
	public String getProductListPage(final Model model)
	{
		fillProductGroupsInModel(model);
		fillAllCategoryView(model);
		return ControllerConstants.LTE.PRODUCTLISTPAGE;
	}

	@RequestMapping(value = "/product/modifyproductpage"
			+ PRODUCTCODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String getModifyPage(@PathVariable("productCode") String productCode,
			@ModelAttribute("message") final String message, final Model model)
	{
		ProductData product = productFacade.getProductForCode(productCode);
		model.addAttribute("categories",
				getAllCategory(product.getCategory().getCode()));
		model.addAttribute("productGroups",
				getProductGroups(product.getProductGroup().getPk()));
		model.addAttribute("product", product);
		model.addAttribute("readonly", true);
		return ControllerConstants.LTE.MODIFYPRODUCTPAGE;
	}

	@RequestMapping(value = "/product/createproductpage", method = RequestMethod.GET)
	public String getCreatePage(final Model model)
	{
		fillAllCategoryView(model);
		fillProductGroupsInModel(model);
		model.addAttribute("readonly", "false");
		return ControllerConstants.LTE.MODIFYPRODUCTPAGE;
	}

	// TODO 需要考虑productCode重复的问题
	@RequestMapping(value = "/product/save", method = RequestMethod.POST)
	public String saveProduct(
			@RequestParam(value = "productCode", required = true) final String productCode,
			@RequestParam(value = "producttitle", required = false) final String productTitle,
			@RequestParam(value = "productGroup", required = false) final String productGroup,
			@RequestParam(value = "category", required = true) final String category,
			final Model model, final RedirectAttributes attr)
	{
		ProductData productData = new ProductData();
		productData.setCode(productCode);
		productData.setProducttitle(productTitle);

		CategoryData categoryData = new CategoryData();
		categoryData.setCode(category);

		ProductGroupData productGroupData = new ProductGroupData();
		productGroupData.setPk(productGroup);

		productData.setCategory(categoryData);
		productData.setProductGroup(productGroupData);

		productFacade.saveProduct(productData);

		attr.addFlashAttribute("message", "保存成功!");
		return REDIRECT_PREFIX + "/product/modifyproductpage/" + productCode;
	}

	@RequestMapping(value = "/product/productlist", method = RequestMethod.GET)
	@ResponseBody
	public Object getProductList(
			@RequestParam(value = "productCode", required = false) final String productCode,
			@RequestParam(value = "productTitle", required = false) final String productTitle,
			@RequestParam(value = "productGroup", required = false) final String productGroup,
			@RequestParam(value = "category", required = true) final String category,
			final DataTableCriterias criterias)
	{
		Map<String, String> sp = new HashMap<String, String>();
		sp.put("productCode", productCode);
		sp.put("productTitle", productTitle);
		sp.put("category", category);
		sp.put("productGroup", productGroup);

		return productFacade.getProductList(sp, criterias.getStart(),
				criterias.getLength());
	}

	@RequestMapping(value = "/product/productlist1", method = RequestMethod.GET)
	@ResponseBody
	public Object getProductList1(
			@RequestParam(value = "productCode", required = false) final String productCode,
			@RequestParam(value = "productTitle", required = false) final String productTitle,
			@RequestParam(value = "productGroups", required = false) final String[] productGroups,
			@RequestParam(value = "categories", required = false) final String[] categories,
			final DataTableCriterias criterias)
	{
		Map<String, String[]> sp = new HashMap<String, String[]>();
		sp.put("productCode", new String[] { productCode });
		sp.put("productTitle", new String[] { productTitle });
		sp.put("categories", categories);
		sp.put("productGroups", productGroups);

		return productFacade.getProductList1(sp, criterias.getStart(),
				criterias.getLength());
	}

}
