package com.third.facade.testdata.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;
import com.third.model.PaymentModel;
import com.third.service.customer.CustomerService;
import com.third.service.order.OrderService;
import com.third.service.product.ProductService;
import com.third.service.store.StoreService;


public class OrderDataBuilder implements DataBuilder
{
	@Resource(name = "orderService")
	private OrderService orderService;

	@Resource(name = "storeService")
	private StoreService storeService;

	@Resource(name = "customerService")
	private CustomerService customerService;

	@Resource(name = "productService")
	private ProductService productService;

	@Override
	public void buildData()
	{
		for (int i = 0; i < 20; i++)
		{
			buildOrder("o-" + i);
		}
	}

	public OrderModel buildOrder(final String orderCode)
	{
		OrderModel orderModel = new OrderModel();

		orderModel.setCode(orderCode);
		orderModel.setCellphone("13800138000");
		orderModel.setTryDate(new Date());
		orderModel.setDeliveryDate(new Date());
		orderModel.setOrderDate(new Date());
		orderModel.setStore(storeService.getStoreForCode("s-1"));

		PaymentModel paymentModel = new PaymentModel();
		paymentModel.setPaymentEntryNo(10);
		paymentModel.setAmount(BigDecimal.valueOf(100.00));


		List<PaymentModel> payments = new ArrayList<PaymentModel>();
		payments.add(paymentModel);

		OrderEntryModel entry = new OrderEntryModel();
		entry.setQuantity(11);
		entry.setEntryNo(10);
		//entry.setProduct(productService.getProductForCode("p-1"));
		List<OrderEntryModel> entries = new ArrayList<OrderEntryModel>();
		entries.add(entry);

		orderModel.setOrderEntries(entries);
		orderModel.setPayments(payments);

		orderService.createOrder(orderModel);
		return orderModel;
	}
}
