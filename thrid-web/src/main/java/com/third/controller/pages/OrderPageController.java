package com.third.controller.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.third.facade.data.ListData;
import com.third.facade.data.OrderData;
import com.third.facade.data.OrderEntryData;
import com.third.facade.data.PaymentData;
import com.third.facade.data.TextMapper;
import com.third.facade.order.OrderFacade;
import com.third.facade.utils.TextMapperUtils;


@Controller
public class OrderPageController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(OrderPageController.class);

	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@Resource(name = "textMapperUtils")
	private TextMapperUtils textMapperUtils;

	@RequestMapping(value = "/getOrderListPage", method = RequestMethod.GET)
	public String getOrderListPage(Model model)
	{
		return ControllerConstants.Fragements.ORDERLIST;
	}

	@RequestMapping(value = "/getOrderList")
	@ResponseBody
	public Object getOrderList(@RequestParam(value = "orderCode", required = false) final String orderCode,
			@RequestParam(value = "cellphone", required = false) final String cellphone,
			@RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date startDate,
			@RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date endDate,
			@RequestParam(value = "page", required = false) final Integer page,
			@RequestParam(value = "rows", required = false) final Integer rows)
	{
		Map<String, String> sp = new HashMap<String, String>();
		sp.put("cellphone", cellphone);
		sp.put("orderCode", orderCode);
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		ListData results = orderFacade.getOrders(startDate, endDate, (page - 1) * rows, rows, sp);
		return results;
	}

	@RequestMapping(value = "/getOrder")
	@ResponseBody
	public Object getOrder(@RequestParam(value = "orderCode") final String orderCode)
	{
		OrderData order = orderFacade.getOrder(orderCode);

		return order;
	}

	@RequestMapping(value = "/saveOrder")
	@ResponseBody
	public void saveOrder(@RequestParam(value = "orderPK", required = false) final String orderPK,
			@RequestParam(value = "orderCode", required = false) final String orderCode,
			@RequestParam(value = "cellphone", required = false) final String cellphone,
			@RequestParam(value = "tryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date tryDate,
			@RequestParam(value = "photoDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date photoDate,
			@RequestParam(value = "deliveryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date deliveryDate,
			@RequestParam(value = "weddingDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date weddingDate,
			@RequestParam(value = "customerName", required = false) final String customerName,
			@RequestParam(value = "comment", required = false) final String comment,
			@RequestParam(value = "coSalesperson", required = false) final String coSalesperson,
			@RequestParam(value = "payments") final String payments)
	{
		OrderData order = new OrderData();
		order.setPk(orderPK);
		order.setOrderCode(orderCode);
		order.setCellphone(cellphone);
		order.setComment(comment);
		order.setPhotoDate(photoDate);
		order.setDeliveryDate(deliveryDate);
		order.setCoSalesperson(coSalesperson);
		order.setTryDate(tryDate);
		order.setOrderDate(new Date());
		order.setWeddingDate(weddingDate);
		order.setCustomerName(customerName);
		List<PaymentData> paymentList = new ArrayList<PaymentData>(JSONArray.parseArray(payments, PaymentData.class));

		order.setPayments(paymentList);

		if (StringUtils.isBlank(order.getPk()))
			orderFacade.createOrder(order);
		else
			orderFacade.updateOrder(order);

	}

	@RequestMapping(value = "/getOrderEntries")
	@ResponseBody
	public Object getOrderEntries(@RequestParam(value = "orderCode") final String orderCode)
	{
		OrderData order = orderFacade.getOrder(orderCode);
		ListData list = new ListData();
		List<Object> rows = new ArrayList<Object>();

		for (int i = 0; i < order.getEntries().size(); i++)
		{
			OrderEntryData e = order.getEntries().get(i);
			rows.add(e);
		}
		list.setRows(rows);
		list.setTotal(rows.size());
		return list;
	}

	@RequestMapping(value = "/removeOrderEntry")
	@ResponseBody
	public void removeOrderEntry(@RequestParam(value = "entryPK", required = false) final String entryPK)
	{
		orderFacade.removeOrderEntry(entryPK);
	}

	@RequestMapping(value = "/saveOrderEntry")
	@ResponseBody
	public void saveOrderEntry(@RequestParam(value = "orderCode", required = false) final String orderCode,
			@RequestParam(value = "entryPK", required = false) final String entryPK,
			@RequestParam(value = "itemCategory", required = false) final String itemCategory,
			@RequestParam(value = "style", required = false) final String style,
			@RequestParam(value = "productTitle", required = false) final String productTitle,
			@RequestParam(value = "quantity", required = false) final Integer quantity,
			@RequestParam(value = "designer", required = false) final String designer,
			@RequestParam(value = "customerName", required = false) final String customerName,
			@RequestParam(value = "deliveryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date deliveryDate,
			@RequestParam(value = "sizeDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date sizeDate,
			@RequestParam(value = "tryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date tryDate,
			@RequestParam(value = "sizeDetails", required = false) final String sizeDetails,
			@RequestParam(value = "comment", required = false) final String comment)
	{
		OrderEntryData orderEntryData = new OrderEntryData();
		orderEntryData.setOrderCode(orderCode);
		orderEntryData.setPk(entryPK);
		orderEntryData.setItemCategory(itemCategory);
		orderEntryData.setStyle(style);
		orderEntryData.setProductTitle(productTitle);
		orderEntryData.setQuantity(quantity);
		orderEntryData.setDesigner(designer);
		orderEntryData.setDeliveryDate(deliveryDate);
		orderEntryData.setSizeDate(sizeDate);
		orderEntryData.setTryDate(tryDate);
		orderEntryData.setSizeDetails(sizeDetails);
		orderEntryData.setComment(comment);
		orderEntryData.setCustomerName(customerName);

		if (StringUtils.isBlank(entryPK))
			orderFacade.createOrderEntry(orderEntryData);
		else
			orderFacade.updateOrderEntry(orderEntryData);
	}

}
