package com.third.controller.pages.lte;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.util.DataTableCriterias;
import com.third.facade.data.ComboboxData;
import com.third.facade.data.DTResults;
import com.third.facade.data.ListData;
import com.third.facade.data.StoreData;
import com.third.facade.data.UserData;
import com.third.facade.data.UserGroupData;
import com.third.facade.store.StoreFacade;
import com.third.facade.user.UserFacade;

@Controller
public class StoreController extends AbstractPageController {
	private static final Logger LOG = Logger
			.getLogger(StoreController.class);
	private static final String STORECODE_PATH_VARIABLE_PATTERN = "/{storeCode:.*}";

	@Autowired
	private StoreFacade storeFacade;

	@RequestMapping(value = "/store/storelistpage", method = RequestMethod.GET)
	public String getStoreListPage()
	{
		return ControllerConstants.LTE.STORELISTPAGE;
	}

	

	@RequestMapping(value = "/store/storelist")
	@ResponseBody
	public Object getUserList(
			@RequestParam(value = "name", required = false) final String name,
			final DataTableCriterias criterias)
	{
		List<StoreData> stores = StringUtils.isEmpty(name)?storeFacade.getAllStores():storeFacade.getStores(name);
		DTResults dtResult =new DTResults();
		dtResult.setRecordsTotal(stores.size());
		List<Object[]> datas = new ArrayList<Object[]>();
		for(int i = 0;i < stores.size();i++)
		{
			String[] r = {stores.get(i).getCode(),stores.get(i).getName()};
			datas.add(r);
		}
		
		dtResult.setData(datas);
		
		return dtResult;
	}

	@RequestMapping(value = "/store/createstorepage", method = RequestMethod.GET)
	public String getCreatePage(final Model model)
	{
		return ControllerConstants.LTE.STOREDETAILSPAGE;
	}

	@RequestMapping(value = "/store/modifystorepage"
			+ STORECODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String getModifyStorePage(
			@PathVariable(value = "storeCode") final String storeCode,
			final Model model)
	{
		StoreData store = storeFacade.getStoreForCode(storeCode);
		
		model.addAttribute("store", store);
		
		return ControllerConstants.LTE.STOREDETAILSPAGE;
	}
	
	@RequestMapping(value = "/store/save", method = RequestMethod.POST)
	public String saveUser(@RequestParam(value = "storeCode") final String storeCode,
			@RequestParam(value = "storePK") final String storePK,
			@RequestParam(value = "name") final String name,
			final Model model)
	{
	    StoreData store = new StoreData();
	    store.setCode(storeCode);
	    store.setName(name);
	    

		if (StringUtils.isEmpty(storePK))
			storeFacade.createStore(store);
		else 
		{
			store.setPk(storePK);
			storeFacade.updateStore(store);
		}

		model.addAttribute("message", "保存成功!");

		return REDIRECT_PREFIX + "/store/modifystorepage/" + storeCode;
	}

}
