package com.third.facade.populator;

import com.third.facade.data.OrderProcessRecordData;
import com.third.facade.data.TextMapper;
import com.third.model.OrderProcessRecordModel;


public class OrderProcessRecordDataPopulator implements Populator<OrderProcessRecordModel, OrderProcessRecordData>
{

	@Override
	public void populate(OrderProcessRecordModel source, OrderProcessRecordData target)
	{
		target.setProcessTime(source.getProcessTime());
		target.setFromStatusText(TextMapper.OrderStatus.get(source.getFromStatus()));
		target.setToStatusText(TextMapper.OrderStatus.get(source.getToStatus()));
		target.setMessage(source.getMessage());
		target.setPk(source.getPk());
		target.setOrderCode(source.getOrderCode());
		if (null != source.getCreatedBy())
		{
			target.setUserId(source.getCreatedBy().getUserId());
			target.setUserName(source.getCreatedBy().getName());
		}
	}

}
