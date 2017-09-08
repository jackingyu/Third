package com.third.controller.weixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.third.facade.data.StoreData;
import com.third.facade.store.StoreFacade;

public abstract class AbstractWeixinController {
	@Resource(name = "storeFacade")
	private StoreFacade storeFacade;

	protected Integer getStartIndex(Integer page, Integer rows)
	{
		return (page - 1) * rows;
	}

	protected void fillInStoreList(final Model model)
	{
		List<StoreData> stores = storeFacade.getAllStores();
		HashMap<String, List<StoreData>> allStores = new HashMap<String, List<StoreData>>();

		for (StoreData store : stores)
		{
			String cityName = store.getAddress().getCity().getName();
			List<StoreData> cityStores = allStores.get(cityName);
			if (CollectionUtils.isEmpty(cityStores))
			{
				cityStores = new ArrayList<StoreData>();
				allStores.put(cityName, cityStores);
			}

			cityStores.add(store);
		}
		model.addAttribute("stores", JSON.toJSON(allStores));
	}

	protected void fillInStoreList1(final Model model)
	{
		List<StoreData> stores = storeFacade.getAllStores();
		HashMap<String, List<StoreData>> allStores = new HashMap<String, List<StoreData>>();

		for (StoreData store : stores)
		{
			String cityName = store.getAddress().getCity().getName();
			List<StoreData> cityStores = allStores.get(cityName);
			if (CollectionUtils.isEmpty(cityStores))
			{
				cityStores = new ArrayList<StoreData>();
				allStores.put(cityName, cityStores);
			}

			cityStores.add(store);
		}
		model.addAttribute("stores", allStores);
	}

}
