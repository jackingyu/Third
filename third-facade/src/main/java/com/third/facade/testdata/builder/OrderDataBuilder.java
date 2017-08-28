package com.third.facade.testdata.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.third.model.CoreConstants.PaymentMethod;
import com.third.model.CoreConstants.PaymentType;
import com.third.model.AddressModel;
import com.third.model.CustomerModel;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;
import com.third.model.OrderProcessRecordModel;
import com.third.model.PaymentModel;
import com.third.model.StoreModel;
import com.third.service.customer.CustomerService;
import com.third.service.order.OrderProcessService;
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
	
	@Resource(name="orderProcessService")
	private OrderProcessService orderProcessService;

	@Override
	public void buildData()
	{
		for (int i = 0; i < 20; i++)
		{
			OrderModel order = buildOrder("o-" + i);
			buildOrderProcess(order);
		}
	}

	public void buildOrderProcess(final OrderModel orderModel)
	{
		OrderProcessRecordModel op = new OrderProcessRecordModel();
		op.setMessage("dadfds");
		op.setOrderCode(orderModel.getCode());
		op.setFromStatus("0");
		op.setToStatus("1");
		orderProcessService.createOrderProcess(op);
		
	}
	
	public OrderModel buildOrder(final String orderCode)
	{
		OrderModel orderModel = new OrderModel();

		orderModel.setCode(orderCode);

		Calendar calendar = Calendar.getInstance();
		Date today = new Date();
		orderModel.setDeliveryDate(getNextDay(today, 5));
		orderModel.setOrderDate(today);
		orderModel.setStore(storeService.getStoreForCode("s-1"));
		orderModel.setPhotoDate(getNextDay(today, 3));
		orderModel.setWeddingDate(getNextDay(today, 20));
		orderModel.setReceiveable(BigDecimal.valueOf(1000.00));
		orderModel.setOpenamount(BigDecimal.valueOf(500.00));
      orderModel.setStatus(0);
		CustomerModel customer = buildCustomer("13800138000"+orderCode, "name"+orderCode);
		orderModel.setCustomer(customer);

		StoreModel store = storeService.getStoreForCode("s-1");
		orderModel.setStore(store);

		PaymentModel paymentModel = new PaymentModel();
		paymentModel.setPaymentMethod(PaymentMethod.Cash);
		paymentModel.setPaymentType(PaymentType.DownPayment);
		paymentModel.setPaymentEntryNo(10);
		paymentModel.setAmount(BigDecimal.valueOf(100.00));
		paymentModel.setPaidTime(Calendar.getInstance().getTime());


		List<PaymentModel> payments = new ArrayList<PaymentModel>();
		payments.add(paymentModel);

		for (int i = 0; i < 5; i++)
		{
			PaymentModel paymentModel1 = new PaymentModel();
			paymentModel1.setPaymentMethod(PaymentMethod.CreditCard);
			paymentModel1.setPaymentType(PaymentType.NormalPayment);
			paymentModel1.setPaymentEntryNo(10 + i);
			paymentModel1.setAmount(BigDecimal.valueOf(100.00 + i));
			paymentModel1.setPaidTime(Calendar.getInstance().getTime());
			payments.add(paymentModel1);
		}


		OrderEntryModel entry = new OrderEntryModel();
		entry.setQuantity(11);
		entry.setEntryNo(1);
		entry.setComment("test order entry");
		entry.setDeliveryDate(new Date());
		entry.setSizeDate(new Date());
		entry.setItemCategory("10");
		entry.setStyle("测试规格");
		entry.setProductTitle("成品西装");
		entry.setSizeDate(new Date());
		entry.setSizeDetails("aaa");
		entry.setDesigner("设计师");
		entry.setTryDate(new Date());
		entry.setComment("我是一个备注备注备注");
		entry.setStore(store);
		//entry.setProduct(productService.getProductForCode("p-1"));
		List<OrderEntryModel> entries = new ArrayList<OrderEntryModel>();
		entries.add(entry);

		orderModel.setOrderEntries(entries);
		orderModel.setPayments(payments);

		orderService.createOrder(orderModel);
		return orderModel;
	}

	public static Date getNextDay(Date date, int after)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +after);//+1今天的时间加一天
		date = calendar.getTime();
		return date;
	}
	
	public CustomerModel buildCustomer(final String cellphone, final String name)
	{
		CustomerModel customer = new CustomerModel();
		customer.setCellphone(cellphone);
		customer.setName(name);
		customer.setBirthday(new Date());
		customer.setWeddingDate(new Date());
		customer.setEmail("dd@tt.com");
		customer.setComment("yekongzhongzuiliangdexing");
		customer.setQQ("33445566");
		customerService.createCustomer(customer);
		return customer;
	}

}
