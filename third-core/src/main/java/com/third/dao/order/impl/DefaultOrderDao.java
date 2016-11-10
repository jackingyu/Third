package com.third.dao.order.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.third.dao.generic.GenericDAO;
import com.third.dao.order.OrderDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.OrderModel;


public class DefaultOrderDao extends GenericDAO<OrderModel, String> implements OrderDao
{
	private final static String FIND_BY_ORDERNO_SQL = "from com.third.model.OrderModel o where o.orderNo=?";

	@Override
	public OrderModel getOrder(String orderNo)
	{
		List<OrderModel> orders = find(FIND_BY_ORDERNO_SQL, orderNo);
		return CollectionUtils.isEmpty(orders) ? null : orders.get(0);
	}


}
