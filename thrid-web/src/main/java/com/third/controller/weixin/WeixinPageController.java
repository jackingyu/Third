package com.third.controller.weixin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.facade.data.ComboboxData;
import com.third.facade.data.StoreData;
import com.third.facade.data.UserData;
import com.third.facade.user.UserFacade;
import com.third.facade.utils.TextMapperUtils;


@Controller
public class WeixinPageController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(WeixinPageController.class);

	@RequestMapping(value = "/getPaymentTypes")
	@ResponseBody
	public Object getPaymentTypes()
	{
		return null;
	}

}
