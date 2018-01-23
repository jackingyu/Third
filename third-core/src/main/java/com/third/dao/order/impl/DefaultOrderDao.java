package com.third.dao.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.third.dao.generic.GenericDAO;
import com.third.dao.order.OrderDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.OrderModel;

public class DefaultOrderDao extends GenericDAO<OrderModel, String>
		implements OrderDao {
	private Log logger = LogFactory.getLog(getClass());
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
	public PaginationSupport findOrders(Date startDate, Date endDate,
			final Integer startIndex, final Integer pageSize,
			Map<String, String> sp)
	{

		final StringBuilder sb = new StringBuilder(
				"select s.code,s.customer.name,s.customer.cellphone,s.store.name,s.deliveryDate,s.status,s.receiveable,s.paidamount,s.openamount,s.salesperson.name,s.coSalesperson,s.source.name from OrderModel  s "
						+ " where ");

		if (StringUtils.isNotBlank(getParameterValue(sp, "orderCode")))
			sb.append("s.code = '").append(getParameterValue(sp, "orderCode"))
					.append("' and ");

		if (StringUtils.isNotBlank(getParameterValue(sp, "cellphone")))
			sb.append("s.customer.cellphone = '")
					.append(getParameterValue(sp, "cellphone"))
					.append("' and ");

		if (StringUtils.isNotBlank(getParameterValue(sp, "orderStatus")))
			sb.append("s.status = ")
					.append(getParameterValue(sp, "orderStatus"))
					.append(" and ");

		if (StringUtils.isNotBlank(getParameterValue(sp, "orderDate")))
			sb.append("s.orderDate = '")
					.append(getParameterValue(sp, "orderDate"))
					.append("' and ");
		
		if (StringUtils.isNotBlank(getParameterValue(sp, "salesperson")))
		    sb.append("s.salesperson = '")
		    .append(getParameterValue(sp, "salesperson"))
		    .append("' and ");

		if (StringUtils.isNotBlank(getParameterValue(sp, "storeCodes")))
		{
			String[] storeArray = getParameterValue(sp, "storeCodes")
					.split(",");

			if (storeArray.length == 1)
				sb.append("s.store.id = '").append(storeArray[0])
						.append("' and ");
			else if (storeArray.length > 1)
			{
				sb.append("s.store.id in (");
				for (int i = 0; i < storeArray.length; i++)
				{
					if (i == storeArray.length - 1)
						sb.append("'").append(storeArray[i]).append("') and ");
					else
						sb.append("'").append(storeArray[i]).append("',");
				}
			}

		}

		if (StringUtils.isNotBlank(getParameterValue(sp, "name")))
			sb.append("s.customer.name like '%")
					.append(getParameterValue(sp, "name")).append("%' and ");

		sb.append("s.deliveryDate between '").append(fmt.format(startDate))
				.append("' and '").append(fmt.format(endDate)).append("'");

		sb.append(" order by s.deliveryDate asc");
		final String hsql = sb.toString();
		logger.info(hsql);

		return findPageByQuery(hsql, pageSize, startIndex);

		// if(StringUtils.isNotBlank(getParameterValue(sp,"orderCode")))
		// dcOrder.add(Restrictions.eq("code", sp.get("orderCode")));
		//
		// if( StringUtils.isNotBlank(getParameterValue(sp,"cellphone")))
		// dcOrder.add(Restrictions.eq("customer.cellphone",
		// sp.get("cellphone")));
		//
		//
		// if( StringUtils.isNotBlank(getParameterValue(sp,"orderDate")))
		// dcOrder.add(Restrictions.eq("orderDate", sp.get("orderDate")));
		//
		//
		//
		//// if( StringUtils.isNotBlank(getParameterValue(sp,"storeCodes")))
		//// dcOrder.add(Restrictions.in(("orderDate", sp.get("orderDate")));
		//
		//
		// if( StringUtils.isNotBlank(getParameterValue(sp,"orderStatus")))
		// dcOrder.add(Restrictions.eq("orderStatus", sp.get("orderStatus")));
		//
		// dcOrder.add(Restrictions.between("deliveryDate", startDate,
		// endDate));
		//
		// if( StringUtils.isNotBlank(getParameterValue(sp,"name")))
		// dcOrder.add(Restrictions.like("customer.name", sp.get("name")));
		//

		// return findPageByCriteria(dcOrder, pageSize, startIndex);
	}

	@Override
	public PaginationSupport findOrdersByOrderDate(Date startDate, Date endDate,
			final Integer startIndex, final Integer pageSize,
			Map<String, String[]> sp) 
	{
		final StringBuilder sb = new StringBuilder(
				"select s.store.id,s.receiveable,s.openamount,s.paidamount from OrderModel  s ");
		
		List<String> condition = new ArrayList<String>();

		
		String c1 = getArrayCondtion(sp, "storeCodes", "s.store.id");
		
		if (StringUtils.isNotEmpty(c1))
			condition.add(c1);

		String c2 = getArrayCondtion(sp, "customerSources", "s.source");
		if (StringUtils.isNotEmpty(c2))
			condition.add(c2);

		condition.add(new StringBuilder("s.orderDate between '")
				.append(fmt.format(startDate)).append("' and '")
				.append(fmt.format(endDate)).append("'").toString());
		
		sb.append("where ")
				.append(StringUtils.join(condition.toArray(), " and "));
		System.out.println("details-----------------------------------");
		System.out.println(sb.toString());
		
		List result = this.searchByQuery(sb.toString());
		result.forEach(r -> {
			Object[] X = (Object[]) r;
			System.out.println(X[0]+"--"+X[1]+"--"+X[2]+"--"+X[3]);
		});
		
		return null;
	}
	
	@Override
	public List<OrderModel> findOrdersForCustomer(String customerPK)
	{
		List<OrderModel> orders = find(FIND_BY_CUSTOMERPK_SQL, customerPK);
		return orders;
	}

	@Override
	public Integer countOrderForCustomer(String cellphone)
	{
		Long numberOfOrder = (Long) this.currentSession()
				.createQuery(COUNT_ORDER_BY_CUSTOMER)
				.setParameter("cellphone", cellphone).uniqueResult();
		return numberOfOrder.intValue();
	}

	@Override
	public List<OrderModel> findOrders(Map<String, String> sp)
	{
		final StringBuilder sb = new StringBuilder(
				"from OrderModel s"
						+ " where ");
		
		if(StringUtils.isNotBlank(getParameterValue(sp, "orderStatus")))
			sb.append("s.status = ").append(getParameterValue(sp, "orderStatus"));
		
		if(StringUtils.isNotBlank(getParameterValue(sp, "salesperson")))
			sb.append(" and ").append(" s.salesperson.userId = '").append(getParameterValue(sp, "salesperson")).append("'");
		
		return find(sb.toString());
	}
	
	@Override
	public List<Object[]> anlysisOrder(Date startDate, Date endDate,
			Map<String, String[]> sp)
	{
		final StringBuilder sb = new StringBuilder(
				"select s.store.id,count(s.pk),sum(s.receiveable), sum(s.openamount),sum(s.paidamount) from OrderModel  s ");
		
		List<String> condition = new ArrayList<String>();

		
		String c1 = getArrayCondtion(sp, "storeCodes", "s.store.id");
		
		if (StringUtils.isNotEmpty(c1))
			condition.add(c1);

		String c2 = getArrayCondtion(sp, "customerSources", "s.source");
		if (StringUtils.isNotEmpty(c2))
			condition.add(c2);

		condition.add(new StringBuilder("s.orderDate between '")
				.append(fmt.format(startDate)).append("' and '")
				.append(fmt.format(endDate)).append("'").toString());
		
		sb.append("where ")
				.append(StringUtils.join(condition.toArray(), " and "));
		sb.append(" group by s.store.id");
		List<Object[]> result = this.searchByQuery(sb.toString());
	    return result;
	}

}
