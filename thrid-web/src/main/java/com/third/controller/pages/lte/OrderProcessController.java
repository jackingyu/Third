package com.third.controller.pages.lte;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.core.util.DataTableCriterias;
import com.third.exceptions.NoQualifiedTargetStatusException;
import com.third.exceptions.NotFoundException;
import com.third.facade.data.OrderData;
import com.third.facade.data.StoreData;
import com.third.facade.order.OrderFacade;
import com.third.facade.order.OrderProcessFacade;
import com.third.facade.populator.option.OrderOption;
import com.third.facade.user.UserFacade;
import com.third.facade.utils.TextMapperUtils;

@Controller
public class OrderProcessController extends AbstractPageController {
	private static final Logger LOG = Logger.getLogger(
			com.third.controller.pages.lte.OrderProcessController.class);

	@Resource(name = "orderProcessFacade")
	private OrderProcessFacade orderProcessFacade;

	@Resource(name = "textMapperUtils")
	private TextMapperUtils textMapperUtils;
	
	@Resource(name = "userFacade")
	private UserFacade userFacade;
	
	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@RequestMapping(value = "/order/updatestatus")
	@ResponseBody
	public String processOrder(
			@RequestParam(value = "orderCode", required = true) final String orderCode,
			@RequestParam(value = "toStatus", required = true) final Integer toStatus,
			final HttpServletResponse response) throws IOException
	{
		if(isSales()||isManager())
		{
			List<StoreData> stores = userFacade.getCurrentUser().getStores();
			OrderData orderData = orderFacade.getOrderForOptions(orderCode,Arrays.asList(OrderOption.BASIC));
			StoreData store = orderData.getStore();
			int i = 0;
			for(StoreData storeData:stores)
			{
				if(storeData.getCode().equals(store.getCode()))
				{	
					i = 1;
					break;
				}
			}
			
			if(i!=1)
			{
				
				response.getWriter().write("该订单属于"+store.getName()+",无权限处理");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				return null;
			}
		}
		try
		{
			orderProcessFacade.processOrder(orderCode, toStatus);
			return TextMapperUtils.getOrderStatusText(toStatus);
		} catch (NoQualifiedTargetStatusException e)
		{
			response.getWriter().write("订单状态不正确");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return null;
		} catch (NotFoundException e)
		{
			response.getWriter().write("未找到订单!");
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return null;
		}

	}

	private boolean isManager()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@RequestMapping(value = "/orderentry/updatestatus")
	@ResponseBody
	public String processOrderEntry(
			@RequestParam(value = "entryPK", required = true) final String entryPK,
			@RequestParam(value = "toStatus", required = true) final Integer toStatus,
			final HttpServletResponse response) throws IOException
	{
		try
		{
			orderProcessFacade.processOrderEntry(entryPK, toStatus);
		} catch (NoQualifiedTargetStatusException e)
		{
			response.sendError(500);
		} catch (NotFoundException e)
		{
			// TODO Auto-generated catch block
			response.sendError(501);
		}

		return TextMapperUtils.getOrderStatusText(toStatus);
	}

}
