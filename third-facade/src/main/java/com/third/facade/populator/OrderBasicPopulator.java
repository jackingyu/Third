package com.third.facade.populator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.third.facade.data.CustomerData;
import com.third.facade.data.OrderData;
import com.third.facade.data.SourceData;
import com.third.facade.data.StoreData;
import com.third.facade.data.TextMapper;
import com.third.facade.data.UserData;
import com.third.facade.utils.TextMapperUtils;
import com.third.model.OrderModel;
import com.third.model.SourceModel;

public class OrderBasicPopulator implements Populator<OrderModel, OrderData> {
	private static final Logger LOG = Logger
			.getLogger(OrderBasicPopulator.class);

	private SourceDataPopulator sourceDataPopulator;
	private StoreDataPopulator storeDataPopulator;
	private UserDataPopulator userDataPopulator;
	private CustomerDataPopulator customerDataPopulator;

	@Override
	public void populate(OrderModel source, OrderData target)
	{

		target.setPk(source.getPk());
		target.setOrderCode(source.getCode());

		// 顾客相关信息
		CustomerData customer = new CustomerData();
		if (source.getCustomer() != null)
		{
			customerDataPopulator.populate(source.getCustomer(), customer);
		} else
		{
			LOG.error("order without customer,order no:" + source.getCode());
		}

		target.setCustomer(customer);
		target.setCustomerName(StringUtils.isNotBlank(source.getCustomerName())
				? source.getCustomerName()
				: customer.getName());
		target.setContactinfo(source.getContactinfo());
		target.setWeddingDate(customer.getWeddingdate());
		target.setCellphone(customer.getCellphone());

		target.setTryDate(source.getTryDate());
		target.setPhotoDate(source.getPhotoDate());
		target.setDeliveryDate(source.getDeliveryDate());
		target.setStatus(Integer.valueOf(source.getStatus()));
		target.setStatusText(
				TextMapperUtils.getOrderStatusText(source.getStatus()));
		
		target.setStatusText4Cust(
				TextMapperUtils.getOrderStatusText4Cust(source.getStatus()));

		StoreData store = new StoreData();
		storeDataPopulator.populate(source.getStore(), store);
		target.setStore(store);

		// 支付相关信息 paid + open = receiveable
		target.setOpenamount(source.getOpenamount() != null
				? source.getOpenamount().toString()
				: "0");
		target.setReceiveable(source.getReceiveable() != null
				? source.getReceiveable().toString()
				: "0");
		target.setPaidamount(source.getPaidamount() != null
				? source.getPaidamount().toString()
				: "0");
		// 订单相关信息
		UserData salesPerson = new UserData();
		userDataPopulator.populate(source.getSalesperson(), salesPerson);
		target.setSalesPerson(salesPerson);
		target.setOrderDate(source.getOrderDate());
		target.setCoSalesperson(source.getCoSalesperson());
		target.setComment(source.getComment());
		

        SourceModel customerSource = source.getSource();
        SourceData sourceData = new SourceData();
        sourceDataPopulator.populate(customerSource, sourceData);
        target.setSource(sourceData);
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
