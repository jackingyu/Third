package com.third.dao.order.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.third.dao.generic.GenericDAO;
import com.third.dao.order.PaymentDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.PaymentModel;

public class DefaultPaymentDao extends GenericDAO<PaymentModel, String>
		implements PaymentDao {
	private Log logger = LogFactory.getLog(getClass());

	@Override
	public PaginationSupport findPayments(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String[]> sp)
	{
		StringBuilder sb = new StringBuilder("select p.store.name ,"
				+ "p.order.customer.name ,p.order.code,"
				+ "p.order.salesperson.name,p.paymentMethod,p.amount,p.paidTime,"
				+ "p.order.receiveable,p.order.paidamount,p.order.openamount,p.order.orderDate,"
				+ "p.order.customer.source.name " + "from PaymentModel p ");

		List<String> condition = new ArrayList<String>();

		String c1 = getArrayCondtion(sp, "storeCodes", "p.store.id");
		if (StringUtils.isNotEmpty(c1))
			condition.add(c1);

		String c2 = getArrayCondtion(sp, "orderStatus", "p.order.status");
		if (StringUtils.isNotEmpty(c2))
			condition.add(c2);

		String c3 = getArrayCondtion(sp, "sourcePKs",
				"p.order.customer.source.pk");
		if (StringUtils.isNotEmpty(c3))
			condition.add(c3);

		String c4 = getArrayCondtion(sp, "paymentMethods", "p.paymentMethod");
		if (StringUtils.isNotEmpty(c4))
			condition.add(c4);

		String c5 = getArrayCondtion(sp, "salesPersons",
				"p.order.salesperson.userId");
		if (StringUtils.isNotEmpty(c5))
			condition.add(c5);

		condition.add(new StringBuilder("p.paidTime between '")
				.append(fmt.format(startDate)).append("' and '")
				.append(fmt.format(endDate)).append("'").toString());
		sb.append("where ")
				.append(StringUtils.join(condition.toArray(), " and "));

		logger.info(sb.toString());
		
		String hsql = sb.toString();

		return this.findPageByQuery(hsql, pageSize, startIndex);
	}

	@Override
	public PaginationSupport findPaymentsByOrderDate(Date startDate,
			Date endDate, Integer startIndex, Integer pageSize,
			Map<String, String[]> sp)
	{
		StringBuilder sb = new StringBuilder("select p.order.customerName,p.paymentMethod,p.amount,p.paidTime,p.order.code " 
	              + "from PaymentModel p ");

		List<String> condition = new ArrayList<String>();

		String c1 = getArrayCondtion(sp, "storeCodes", "p.store.id");
		if (StringUtils.isNotEmpty(c1))
			condition.add(c1);

		String c3 = getArrayCondtion(sp, "customerSources",
				"p.order.source.pk");
		
		if (StringUtils.isNotEmpty(c3))
			condition.add(c3);
//
//		String c4 = getArrayCondtion(sp, "paymentMethods", "p.paymentMethod");
//		if (StringUtils.isNotEmpty(c4))
//			condition.add(c4);

		condition.add(new StringBuilder("p.order.orderDate between '")
				.append(fmt.format(startDate)).append("' and '")
				.append(fmt.format(endDate)).append("'").toString());
		sb.append("where ")
				.append(StringUtils.join(condition.toArray(), " and "));
		
		String hsql = sb.toString();

		return this.findPageByQuery(hsql, pageSize, startIndex);
	}
	
	@Override
	public List<Object[]> getTotalPaymentsByMethod(Date startDate, Date endDate,
			Map<String, String[]> sp)
	{
		StringBuilder sb = new StringBuilder("select p.paymentMethod,sum(p.amount) " 
	              + "from PaymentModel p ");

		List<String> condition = new ArrayList<String>();

		String c1 = getArrayCondtion(sp, "storeCodes", "p.store.id");
		if (StringUtils.isNotEmpty(c1))
			condition.add(c1);

		String c3 = getArrayCondtion(sp, "customerSources",
				"p.order.source.pk");
		
		if (StringUtils.isNotEmpty(c3))
			condition.add(c3);

		condition.add(new StringBuilder("p.order.orderDate between '")
				.append(fmt.format(startDate)).append("' and '")
				.append(fmt.format(endDate)).append("'").toString());
		sb.append("where ")
				.append(StringUtils.join(condition.toArray(), " and "));
		
		sb.append(" group by p.paymentMethod ");
		
		String hsql = sb.toString();

		return this.searchByQuery(hsql);
	}

}
