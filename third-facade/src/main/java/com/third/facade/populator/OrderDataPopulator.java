package com.third.facade.populator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.third.facade.data.CustomerData;
import com.third.facade.data.OrderData;
import com.third.facade.data.OrderEntryData;
import com.third.facade.data.PaymentData;
import com.third.facade.data.StoreData;
import com.third.facade.utils.TextMapperUtils;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;
import com.third.model.PaymentModel;
import com.third.model.StoreModel;

public class OrderDataPopulator implements Populator<OrderModel, OrderData> {
	private static final Logger LOG = Logger
			.getLogger(OrderDataPopulator.class);

	private OrderEntryDataPopulator orderEntryDataPopulator;
	private PaymentDataPopulator paymentDataPopulator;
	private SourceDataPopulator sourceDataPopulator;
	private StoreDataPopulator storeDataPopulator;
	private UserDataPopulator userDataPopulator;
	private CustomerDataPopulator customerDataPopulator;

	@Override
	public void populate(OrderModel source, OrderData target)
	{

		target.setPk(source.getPk());
		target.setOrderCode(source.getCode());
		target.setStatus(source.getStatus());
		target.setStatusText(
				TextMapperUtils.getOrderStatusText(source.getStatus()));
		// 顾客相关信息
		CustomerData customer = new CustomerData();
		if (source.getCustomer() != null)
		{
			customerDataPopulator.populate(source.getCustomer(), customer);
			LOG.error("order without customer,order no:" + source.getCode());
		}
		target.setCustomer(customer);
		target.setCustomerName(StringUtils.isNotBlank(source.getCustomerName())
				? source.getCustomerName()
				: customer.getName());
		target.setWeddingDate(customer.getWeddingdate());
		target.setCellphone(source.getCellphone());

		target.setTryDate(source.getTryDate());
		target.setPhotoDate(source.getPhotoDate());
		target.setDeliveryDate(source.getDeliveryDate());

		StoreData store = new StoreData();
		storeDataPopulator.populate(source.getStore(), store);
		target.setStore(store);

		// 支付相关信息
		target.setOpenamount(source.getOpenamount() != null
				? source.getOpenamount().toString()
				: "0");
		target.setReceiveable(source.getReceiveable() != null
				? source.getReceiveable().toString()
				: "0");
		List<PaymentData> payments = new ArrayList<PaymentData>();
		List<PaymentModel> paymentModels = (List<PaymentModel>) source
				.getPayments();

		if (!CollectionUtils.isEmpty(paymentModels))
			paymentModels.stream().filter((p) -> p != null).forEach(p -> {
				PaymentData paymentData = new PaymentData();
				paymentDataPopulator.populate(p, paymentData);
				payments.add(paymentData);
			});

		target.setPayments(payments);

		// 订单相关信息
		target.setOrderDate(source.getOrderDate());
		target.setCoSalesperson(source.getCoSalesperson());
		target.setComment(source.getComment());

		// 订单行项目
		List<OrderEntryData> orderEntryDatas = new ArrayList<OrderEntryData>();
		List<OrderEntryModel> orderEntryModels = (List<OrderEntryModel>) source
				.getOrderEntries();

		if (!CollectionUtils.isEmpty(orderEntryModels))
			orderEntryModels.stream().filter((o) -> o != null).forEach(o -> {
				if (o == null)
					return;
				OrderEntryData orderEntryData = new OrderEntryData();
				orderEntryDataPopulator.populate(o, orderEntryData);
				orderEntryDatas.add(orderEntryData);
			});

		target.setEntries(orderEntryDatas);

	}

	public void setOrderEntryDataPopulator(
			OrderEntryDataPopulator orderEntryDataPopulator)
	{
		this.orderEntryDataPopulator = orderEntryDataPopulator;
	}

	public void setPaymentDataPopulator(
			PaymentDataPopulator paymentDataPopulator)
	{
		this.paymentDataPopulator = paymentDataPopulator;
	}

	public void setSourceDataPopulator(SourceDataPopulator sourceDataPopulator)
	{
		this.sourceDataPopulator = sourceDataPopulator;
	}

	public void setStoreDataPopulator(StoreDataPopulator storeDataPopulator)
	{
		this.storeDataPopulator = storeDataPopulator;
	}

	public void setUserDataPopulator(UserDataPopulator userDataPopulator)
	{
		this.userDataPopulator = userDataPopulator;
	}

	public void setCustomerDataPopulator(
			CustomerDataPopulator customerDataPopulator)
	{
		this.customerDataPopulator = customerDataPopulator;
	}

}
