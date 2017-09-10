package com.third.controller.pages;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.facade.data.ComboboxData;
import com.third.facade.data.StoreData;
import com.third.facade.data.UserData;
import com.third.facade.user.UserFacade;
import com.third.facade.utils.TextMapperUtils;

@Controller
public class ComboboxData1Controller extends AbstractPageController {
	private static final Logger LOG = Logger
			.getLogger(ComboboxData1Controller.class);

	@Resource(name = "textMapperUtils")
	private TextMapperUtils textMapperUtils;

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@RequestMapping(value = "/getPaymentTypes")
	@ResponseBody
	public Object getPaymentTypes()
	{
		return textMapperUtils.getPaymentTypes();
	}

	@RequestMapping(value = "/getPaymentMethods")
	@ResponseBody
	public Object getPaymentMethods1()
	{
		return textMapperUtils.getPaymentMethods();
	}

	@RequestMapping(value = "/getItemCategories")
	@ResponseBody
	public Object getItemCategories()
	{
		return textMapperUtils.getItemCategories();
	}

	@RequestMapping(value = "/getStoresForUser")
	@ResponseBody
	public Object getStores()
	{
		UserData user = userFacade.getCurrentUser();
		List<ComboboxData> results = new ArrayList<ComboboxData>();
		for (int i = 0; i < user.getStores().size(); i++)
		{
			StoreData storeData = user.getStores().get(i);
			ComboboxData e = new ComboboxData();
			e.setCode(storeData.getCode());
			e.setText(storeData.getName());

			if (i == 0)
				e.setSelected(true);

			results.add(e);
		}

		return results;
	}

}
