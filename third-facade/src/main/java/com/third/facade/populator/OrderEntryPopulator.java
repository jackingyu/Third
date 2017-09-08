package com.third.facade.populator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.third.facade.data.OrderData;
import com.third.facade.data.OrderEntryData;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;

public class OrderEntryPopulator implements Populator<OrderModel, OrderData> {
	private static final Logger LOG = Logger
			.getLogger(OrderEntryPopulator.class);

	private OrderEntryDataPopulator orderEntryDataPopulator;

	@Override
	public void populate(OrderModel source, OrderData target)
	{
		// 订单行项目
		List<OrderEntryData> orderEntryDatas = new ArrayList<OrderEntryData>();
		List<OrderEntryModel> orderEntryModels = (List<OrderEntryModel>) source
				.getOrderEntries();

		if (!CollectionUtils.isEmpty(orderEntryModels))
			orderEntryModels.forEach(o -> {
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

}
