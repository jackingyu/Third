package com.third.facade.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.facade.data.ListData;
import com.third.facade.data.OrderData;
import com.third.facade.data.OrderEntryData;
import com.third.facade.data.SizeAttributeGroupData;


public interface OrderFacade
{
	void createOrder(OrderData orderData);

	ListData getOrders(Date startDate, Date endDate, Integer startIndex, Integer pageSize, Map<String, String> sp);

	OrderData getOrder(final String orderCode);

	void updateOrder(final OrderData order);

	void createOrderEntry(final OrderEntryData orderEntryData);

	void updateOrderEntry(final OrderEntryData orderEntryData);

	void removeOrderEntry(final String orderEntryPK);

	/**
	 * @param itemCategory
	 * @return in the map key: 10-L 量,20-剪,30-裁
	 */
	Map<String, SizeAttributeGroupData> getSizeAttributes(final Integer itemCategory);

	OrderEntryData getSizeDatas(String orderEntryPK);

}
