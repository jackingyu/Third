package com.third.controller.weixin;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.third.controller.pages.ControllerConstants;
import com.third.facade.data.StoreData;
import com.third.facade.store.StoreFacade;

@Controller
@RequestMapping("/wx")
public class WeixinStoreController extends AbstractWeixinController {
	private static final Logger LOG = Logger
			.getLogger(WeixinStoreController.class);

	@Resource(name = "storeFacade")
	private StoreFacade storeFacade;

	@RequestMapping(value = "/getStoreList")
	public String getStoreList(final Model model)
	{
		fillInStoreList1(model);
		return ControllerConstants.WeiXin.STORELISTPAGE;
	}

	@RequestMapping(value = "/getStoreDetail")
	public String getOrderDetail(
			@RequestParam(value = "code", required = true) final String storeCode,
			final Model model)
	{
		StoreData store = storeFacade.getStoreForCode(storeCode);
		model.addAttribute("store", store);
		return ControllerConstants.WeiXin.STOREDETAILPAGE;
	}

}
