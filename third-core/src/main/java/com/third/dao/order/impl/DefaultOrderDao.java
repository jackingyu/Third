package com.third.dao.order.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.CollectionUtils;

import com.third.dao.generic.GenericDAO;
import com.third.dao.order.OrderDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.OrderModel;
import com.third.model.StoreModel;


public class DefaultOrderDao extends GenericDAO<OrderModel, String> implements OrderDao
{
	private final static String FIND_BY_ORDERCODE_SQL = "from com.third.model.OrderModel o where o.code=?";
	private final static String FIND_BY_CUSTOMERPK_SQL = "from com.third.model.OrderModel o where o.customer.pk=?";
	private final static String COUNT_ORDER_BY_CUSTOMER = "select count(*) from com.third.model.OrderModel o where o.customer.cellphone=:cellphone";


	@Override
	public OrderModel findOrder(String orderCode)
	{
		List<OrderModel> orders = find(FIND_BY_ORDERCODE_SQL, orderCode);
		return CollectionUtils.isEmpty(orders) ? null : orders.get(0);
	}

	@Override
	public PaginationSupport findOrders(Date startDate, Date endDate, final Integer startIndex, final Integer pageSize,
			Map<String, String> sp)
	{
		DetachedCriteria dcOrder = DetachedCriteria.forClass(OrderModel.class);

		if (sp != null && sp.containsKey("orderCode") && StringUtils.isNotBlank(sp.get("orderCode")))
			dcOrder.add(Restrictions.eq("code", sp.get("orderCode")));

		if (sp != null && sp.containsKey("cellphone") && StringUtils.isNotBlank(sp.get("cellphone")))
			dcOrder.add(Restrictions.eq("cellphone", sp.get("cellphone")));

		dcOrder.add(Restrictions.between("orderDate", startDate, endDate));
		
		return findPageByCriteria(dcOrder, pageSize, startIndex);
	}

	@Override
	public List<OrderModel> findOrdersForCustomer(String customerPK)
	{
		List<OrderModel> orders = find(FIND_BY_CUSTOMERPK_SQL,customerPK);
		return orders;
	}

	@Override
	public Integer countOrderForCustomer(String cellphone)
	{
		Long numberOfOrder= (Long) this.currentSession().createQuery(COUNT_ORDER_BY_CUSTOMER).setParameter("cellphone", cellphone)
				.uniqueResult();
		return numberOfOrder.intValue();
	}


}
