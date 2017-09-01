package com.third.controller.pages.lte;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.core.util.DataTableCriterias;
import com.third.exceptions.NoQualifiedTargetStatusException;
import com.third.exceptions.NotFoundException;
import com.third.facade.order.OrderProcessFacade;


@Controller
public class OrderProcessController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(com.third.controller.pages.lte.OrderProcessController.class);

	@Resource(name = "orderProcessFacade")
	private OrderProcessFacade orderProcessFacade;

	@RequestMapping(value = "/order/updatestatus")
	@ResponseBody
	public void processOrder(@RequestParam(value = "orderCode", required = true) final String orderCode,
			@RequestParam(value = "toStatus", required = true) final Integer toStatus,
			final HttpServletResponse response) throws IOException
	{
		try
		{
			orderProcessFacade.processOrder(orderCode, toStatus);
		}
		catch (NoQualifiedTargetStatusException e)
		{
			response.sendError(500);
		}
		catch (NotFoundException e)
		{
			// TODO Auto-generated catch block
			response.sendError(500);
		}
	}

}
