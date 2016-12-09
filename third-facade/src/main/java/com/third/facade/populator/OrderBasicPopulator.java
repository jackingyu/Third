package com.third.facade.populator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.third.facade.data.CustomerData;
import com.third.facade.data.OrderData;
import com.third.facade.data.StoreData;
import com.third.model.OrderModel;


public class OrderBasicPopulator implements Populator<OrderModel, OrderData>
{
	private static final Logger LOG = Logger.getLogger(OrderBasicPopulator.class);

	private SourceDataPopulator sourceDataPopulator;
	private StoreDataPopulator storeDataPopulator;
	private UserDataPopulator userDataPopulator;
	private CustomerDataPopulator customerDataPopulator;

	@Override
	public void populate(OrderModel source, OrderData target)
	{

		target.setPk(source.getPk());
		target.setOrderCode(source.getCode());

		//顾客相关信息
		CustomerData customer = new CustomerData();
		if (source.getCustomer() != null)
		{
			customerDataPopulator.populate(source.getCustomer(), customer);
			LOG.error("order without customer,order no:" + source.getCode());
		}
		target.setCustomer(customer);
		target.setCustomerName(StringUtils.isNotBlank(source.getCustomerName()) ? source.getCustomerName() : customer.getName());

		target.setCellphone(source.getCellphone());


		target.setTryDate(source.getTryDate());
		target.setPhotoDate(source.getPhotoDate());
		target.setDeliveryDate(source.getDeliveryDate());
		target.setWeddingDate(source.getWeddingDate());
      target.setStatus(Integer.valueOf(source.getStatus()));
      
		StoreData store = new StoreData();
		storeDataPopulator.populate(source.getStore(), store);
		target.setStore(store);

		//支付相关信息
		target.setOpenamount(source.getOpenamount() != null ? source.getOpenamount().toString() : "0");
		target.setReceiveable(source.getReceiveable() != null ? source.getReceiveable().toString() : "0");

		//订单相关信息
		target.setOrderDate(source.getOrderDate());
		target.setCoSalesperson(source.getCoSalesperson());
		target.setComment(source.getComment());
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

	public void setCustomerDataPopulator(CustomerDataPopulator customerDataPopulator)
	{
		this.customerDataPopulator = customerDataPopulator;
	}

}
