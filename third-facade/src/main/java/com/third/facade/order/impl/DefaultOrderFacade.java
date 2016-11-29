package com.third.facade.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.third.dao.util.PaginationSupport;
import com.third.facade.data.ListData;
import com.third.facade.data.OrderData;
import com.third.facade.data.OrderEntryData;
import com.third.facade.order.OrderFacade;
import com.third.facade.populator.OrderDataPopulator;
import com.third.model.CustomerModel;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;
import com.third.model.PaymentModel;
import com.third.model.StoreModel;
import com.third.service.customer.CustomerService;
import com.third.service.customer.SourceService;
import com.third.service.order.OrderService;
import com.third.service.store.StoreService;
import com.third.service.user.UserService;


public class DefaultOrderFacade implements OrderFacade
{
	private OrderService orderService;
	private CustomerService customerService;
	private OrderDataPopulator orderDataPopulator;
	private UserService userService;
	private StoreService storeService;
	private SourceService sourceService;

	@Override
	public void createOrder(OrderData orderData)
	{
		OrderModel order = new OrderModel();

		//TODO: 需要考虑订单上店铺的拉取策略,可以考虑改完前台手工设置
		Optional<StoreModel> store = userService.getCurrentUser().getStores().stream().findFirst();//(StoreModel) userService.getCurrentUser().getStores().iterator().next();

		order.setCode(orderData.getOrderCode());
		order.setCellphone(orderData.getCellphone());
		order.setComment(orderData.getComment());
		order.setCoSalesperson(orderData.getCoSalesperson());
		CustomerModel customer = customerService.getCustomerByCellphone(orderData.getCellphone());
		order.setCustomer(customer);
		order.setCustomerName(orderData.getCustomerName());

		order.setDeliveryDate(orderData.getDeliveryDate());
		order.setOrderDate(orderData.getOrderDate());
		order.setPhotoDate(orderData.getPhotoDate());
		order.setSalesperson(userService.getCurrentUser());
		order.setSource(customer.getSource());
		order.setTryDate(orderData.getTryDate());
		order.setWeddingDate(orderData.getWeddingDate());

		if (!CollectionUtils.isEmpty(orderData.getPayments()))
		{
			List<PaymentModel> paymentModels = new ArrayList<PaymentModel>();
			orderData.getPayments().forEach(p -> {
				PaymentModel payment = new PaymentModel();
				payment.setAmount(p.getAmount());
				payment.setCreatedBy(userService.getCurrentUser());
				payment.setPaidTime(new Date());
				payment.setPaymentMethod(p.getPaymentMethod());
				payment.setPaymentType(p.getPaymentType());
				payment.setStore(store.get());
				paymentModels.add(payment);
			});
			order.setPayments(paymentModels);
		}
		orderService.createOrder(order);
	}

	@Override
	public ListData getOrders(Date startDate, Date endDate, Integer startIndex, Integer pageSize, Map<String, String> sp)
	{
		PaginationSupport ps = orderService.getOrders(startDate, endDate, startIndex, pageSize, sp);

		List<OrderModel> orders = ps.getItems();
		ListData datas = new ListData();
		List<Object> orderDatas = new ArrayList<Object>();

		if (!CollectionUtils.isEmpty(orders))
		{
			orders.forEach(o -> {
				OrderData orderData = new OrderData();
				orderDataPopulator.populate(o, orderData);
				orderDatas.add(orderData);
			});

			datas.setRows(orderDatas);
			datas.setTotal(ps.getTotalCount());
		}

		return datas;
	}

	@Override
	public OrderData getOrder(String orderCode)
	{
		OrderModel order = orderService.getOrderForCode(orderCode);
		OrderData orderData = new OrderData();
		orderDataPopulator.populate(order, orderData);
		return orderData;
	}

	@Override
	public void updateOrder(OrderData orderData)
	{
		if (StringUtils.isBlank(orderData.getPk()))
			return;

		OrderModel order = orderService.getOrderForCode(orderData.getOrderCode());

		order.setCellphone(orderData.getCellphone());
		order.setComment(orderData.getComment());
		order.setCoSalesperson(orderData.getCoSalesperson());
		CustomerModel customer = customerService.getCustomerByCellphone(orderData.getCellphone());
		order.setCustomer(customer);
		order.setCustomerName(orderData.getCustomerName());

		order.setDeliveryDate(orderData.getDeliveryDate());
		order.setOrderDate(orderData.getOrderDate());
		order.setPhotoDate(orderData.getPhotoDate());

		if (orderData.getSource() != null)
			order.setSource(sourceService.getSource(orderData.getSource().getPk()));

		order.setTryDate(orderData.getTryDate());
		order.setWeddingDate(orderData.getWeddingDate());

		if (!CollectionUtils.isEmpty(orderData.getPayments()))
		{
			List<PaymentModel> paymentModels = new ArrayList<PaymentModel>();
			List<PaymentModel> oldPaymentModels = order.getPayments();
			orderData.getPayments().forEach(p -> {
				if (StringUtils.isBlank(p.getPk()))
				{
					PaymentModel payment = new PaymentModel();
					payment.setAmount(p.getAmount());
					payment.setCreatedBy(userService.getCurrentUser());
					payment.setPaidTime(new Date());
					payment.setPaymentMethod(p.getPaymentMethod());
					payment.setPaymentType(p.getPaymentType());
					paymentModels.add(payment);
				}
				else
				{
					Optional<PaymentModel> payment = oldPaymentModels.stream().filter(p1 -> p1.getPk().equals(p.getPk())).findFirst();
					payment.get().setAmount(p.getAmount());
					payment.get().setPaymentType(p.getPaymentType());
					payment.get().setPaymentMethod(p.getPaymentMethod());
					paymentModels.add(payment.get());
				}
			});
			order.setPayments(paymentModels);
		}

		orderService.upateOrder(order);


	}

	@Override
	public void createOrderEntry(final OrderEntryData orderEntryData)
	{
		OrderEntryModel orderEntry = new OrderEntryModel();
		orderEntry.setComment(orderEntryData.getComment());
		orderEntry.setDeliveryDate(orderEntryData.getDeliveryDate());
		orderEntry.setDesigner(orderEntryData.getDesigner());
		orderEntry.setItemCategory(orderEntryData.getItemCategory());
		orderEntry.setProductTitle(orderEntryData.getProductTitle());
		orderEntry.setQuantity(orderEntryData.getQuantity());
		orderEntry.setSizeDate(orderEntryData.getSizeDate());
		orderEntry.setSizeDetails(orderEntryData.getSizeDetails());
		orderEntry.setTryDate(orderEntryData.getTryDate());
		orderEntry.setStyle(orderEntryData.getStyle());
		orderEntry.setCustomerName(orderEntryData.getCustomerName());

		OrderModel order = orderService.getOrderForCode(orderEntryData.getOrderCode());
		orderEntry.setOrder(order);
		orderEntry.setCreatedBy(userService.getCurrentUser());
		orderService.createOrderEntry(orderEntry);
	}

	@Override
	public void updateOrderEntry(final OrderEntryData orderEntryData)
	{
		OrderEntryModel orderEntry = orderService.getOrderEntry(orderEntryData.getPk());
		orderEntry.setComment(orderEntryData.getComment());
		orderEntry.setDeliveryDate(orderEntryData.getDeliveryDate());
		orderEntry.setDesigner(orderEntryData.getDesigner());
		orderEntry.setItemCategory(orderEntryData.getItemCategory());
		orderEntry.setProductTitle(orderEntryData.getProductTitle());
		orderEntry.setQuantity(orderEntryData.getQuantity());
		orderEntry.setSizeDate(orderEntryData.getSizeDate());
		orderEntry.setSizeDetails(orderEntryData.getSizeDetails());
		orderEntry.setTryDate(orderEntryData.getTryDate());
		orderEntry.setStyle(orderEntryData.getStyle());
		orderEntry.setCustomerName(orderEntryData.getCustomerName());

		orderService.updateOrderEntry(orderEntry);
	}

	public void setOrderService(OrderService orderService)
	{
		this.orderService = orderService;
	}

	public void setCustomerService(CustomerService customerService)
	{
		this.customerService = customerService;
	}

	public void setOrderDataPopulator(OrderDataPopulator orderDataPopulator)
	{
		this.orderDataPopulator = orderDataPopulator;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public void setStoreService(StoreService storeService)
	{
		this.storeService = storeService;
	}

	public void setSourceService(SourceService sourceService)
	{
		this.sourceService = sourceService;
	}

	@Override
	public void removeOrderEntry(String orderEntryPK)
	{
		orderService.removeOrderEntry(orderEntryPK);
	}
}
