package com.third.facade.testdata.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import com.alibaba.fastjson.JSON;
import com.third.core.constants.CoreConstants;
import com.third.model.AddressModel;
import com.third.model.CustomerModel;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;
import com.third.model.OrderProcessRecordModel;
import com.third.model.PaymentModel;
import com.third.model.SizeAttributeModel;
import com.third.model.SourceModel;
import com.third.model.StoreModel;
import com.third.model.UserModel;
import com.third.service.customer.CustomerService;
import com.third.service.customer.SourceService;
import com.third.service.order.OrderProcessService;
import com.third.service.order.OrderService;
import com.third.service.product.ProductService;
import com.third.service.product.SizeAttributeService;
import com.third.service.store.StoreService;
import com.third.service.user.UserService;
import com.third.core.constants.CoreConstants.PaymentMethod;
import com.third.core.constants.CoreConstants.PaymentType;
import com.third.dao.product.ProductDao;
import com.third.facade.data.SizeAttributeData;

public class OrderDataBuilder implements DataBuilder {
	@Resource(name = "orderService")
	private OrderService orderService;

	@Resource(name = "storeService")
	private StoreService storeService;

	@Resource(name = "sourceService")
	private SourceService sourceService;

	@Resource(name = "customerService")
	private CustomerService customerService;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "sizeAttributeService")
	private SizeAttributeService sizeAttributeService;

	@Resource(name = "orderProcessService")
	private OrderProcessService orderProcessService;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "productDao")
	private ProductDao productDao;

	private HashMap<String, List<SizeAttributeModel>> sizeAttributeModels = new HashMap<String, List<SizeAttributeModel>>();;

	private List<SourceModel> sources;

	public void buildData()
	{
		sizeAttributeModels.put("10",
				sizeAttributeService.getSizeAttributeForItemCategory(10));
		sizeAttributeModels.put("20",
				sizeAttributeService.getSizeAttributeForItemCategory(20));
		sizeAttributeModels.put("30",
				sizeAttributeService.getSizeAttributeForItemCategory(30));
		sizeAttributeModels.put("40",
				sizeAttributeService.getSizeAttributeForItemCategory(40));

		for (int i = 0; i < 50; i++)
		{
			buildOrder("T-" + i);
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
		sources = sourceService.getSources();
		OrderModel orderModel = new OrderModel();

		orderModel.setCode(orderCode);

		Calendar calendar = Calendar.getInstance();
		Date today = new Date();
		orderModel.setDeliveryDate(
				getNextDay(today, RandomUtils.nextInt(0, 365)));
		orderModel.setOrderDate(getNextDay(today, RandomUtils.nextInt(0, 365)));
		orderModel.setPhotoDate(getNextDay(today, RandomUtils.nextInt(0, 365)));
		orderModel
				.setWeddingDate(getNextDay(today, RandomUtils.nextInt(0, 365)));
		orderModel.setStatus(0);
		CustomerModel customer = buildCustomer("13800138000" + orderCode,
				"name" + orderCode);
		orderModel.setCustomer(customer);

		StoreModel store = storeService.getStoreForCode("1");
		orderModel.setStore(store);
		UserModel salesperson = userService.getSalesPerson(store.getId())
				.get(RandomUtils.nextInt(0, 3));
		orderModel.setSalesperson(salesperson);

		PaymentModel paymentModel = new PaymentModel();
		paymentModel.setPaymentMethod(PaymentMethod.Cash);
		paymentModel.setPaymentType(PaymentType.DownPayment);
		paymentModel.setPaymentEntryNo(10);
		paymentModel.setAmount(BigDecimal.valueOf(100.00));
		paymentModel.setPaidTime(Calendar.getInstance().getTime());
		paymentModel.setStore(store);

		List<PaymentModel> payments = new ArrayList<PaymentModel>();
		payments.add(paymentModel);

		BigDecimal paidamount = new BigDecimal(100);

		for (int i = 0; i < 5; i++)
		{
			PaymentModel paymentModel1 = new PaymentModel();
			paymentModel1.setPaymentMethod(PaymentMethod.CreditCard);
			paymentModel1.setPaymentType(PaymentType.NormalPayment);
			paymentModel1.setPaymentEntryNo(10 + i);
			paymentModel1.setStore(store);
			paymentModel1
					.setAmount(BigDecimal.valueOf(RandomUtils.nextInt(1, 199)));
			paymentModel1.setPaidTime(Calendar.getInstance().getTime());
			payments.add(paymentModel1);
			paidamount = paidamount.add(paymentModel1.getAmount());
		}

		orderModel.setReceiveable(
				BigDecimal.valueOf(RandomUtils.nextInt(1000, 9999)));
		orderModel.setPaidamount(paidamount);
		orderModel.setOpenamount(orderModel.getReceiveable()
				.subtract(orderModel.getPaidamount()));

		List<OrderEntryModel> entries = new ArrayList<OrderEntryModel>();

		List<UserModel> designers = userService
				.getDesignerForStore(orderModel.getStore().getId());

		UserModel designer = null;
		if (CollectionUtils.isNotEmpty(designers))
			designer = designers.get(0);

		for (int j = 0; j < 5; j++)
		{
			OrderEntryModel entry = new OrderEntryModel();
			entry.setQuantity(1);
			entry.setEntryNo(j + 1);
			entry.setComment("test order entry" + j);
			entry.setDeliveryDate(orderModel.getDeliveryDate());
			entry.setSizeDate(new Date());
			Integer itemCategory = RandomUtils.nextInt(1, 4) * 10;
			entry.setItemCategory(itemCategory.toString());
			List<SizeAttributeModel> sizeAttributes = sizeAttributeService
					.getSizeAttributeForItemCategory(
							Integer.valueOf(itemCategory));
			List<SizeAttributeData> sizeAttributeDatas = new ArrayList<SizeAttributeData>();

			sizeAttributes.forEach(s -> {
					SizeAttributeData sizeAttributeData = new SizeAttributeData();
					sizeAttributeData.setName(s.getName());
					sizeAttributeData.setValue(
							new Integer(RandomUtils.nextInt(1, 50)).toString());
					sizeAttributeData.setGroup(s.getGroup().toString());
					sizeAttributeDatas.add(sizeAttributeData);
			});

			entry.setSizeDetails(JSON.toJSONString(sizeAttributeDatas));

			entry.setStyle("测试规格");
			entry.setProductTitle("成品西装");
			entry.setSizeDate(new Date());

			if (designer != null)
				entry.setDesigner(designer);

			entry.setTryDate(com.third.facade.utils.DateUtils.getToday());
			entry.setComment("我是一个备注备注备注");
			entry.setExternalId(Integer.toString(RandomUtils.nextInt()));
			entry.setStore(store);
			entry.setStatus(0);
			entry.setProduct(productDao.list().get(RandomUtils.nextInt(0, 10)));
			entries.add(entry);
		}

		orderModel.setOrderEntries(entries);
		orderModel.setPayments(payments);

		orderService.createOrder(orderModel);
		return orderModel;
	}

	public static Date getNextDay(Date date, int after)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +after);// +1今天的时间加一天
		date = calendar.getTime();
		return date;
	}

	public CustomerModel buildCustomer(final String cellphone,
			final String name)
	{
		CustomerModel customer = new CustomerModel();
		customer.setCellphone(cellphone);
		customer.setName(name);
		customer.setBirthday(new Date());
		customer.setWeddingDate(new Date());
		customer.setEmail("dd@tt.com");
		customer.setComment("yekongzhongzuiliangdexing");
		customer.setQQ("33445566");
		customer.setSource(sources.get(RandomUtils.nextInt(0, 5)));
		customerService.createCustomer(customer);
		return customer;
	}

}
