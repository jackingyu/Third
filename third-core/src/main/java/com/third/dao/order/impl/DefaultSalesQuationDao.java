package com.third.dao.order.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import com.third.dao.generic.GenericDAO;
import com.third.dao.order.SalesQuationDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.SalesQuationModel;


public class DefaultSalesQuationDao extends GenericDAO<SalesQuationModel, String> implements SalesQuationDao
{

	@Override
	public PaginationSupport findSalesQuations(Date startDate, Date endDate, Integer startIndex, Integer pageSize,
			Map<String, String> sp)
	{
		final StringBuilder sb = new StringBuilder("select o.source.name, o.customerName,o.cellphone,o.paidamount,o.paymentMethod from SalesQuation o ");

		List<String> condition = new ArrayList<String>();

		if (StringUtils.isNotBlank(getParameterValue(sp, "cellphone")))
			condition.add(sb.append("o.cellphone = '").
					append(getParameterValue(sp, "cellphone")).append("'").toString());
		
		if (StringUtils.isNotBlank(getParameterValue(sp, "source")))
			condition.add(sb.append("o.source.pk = '").
					append(getParameterValue(sp, "source")).append("'").toString());
		
		if (StringUtils.isNotBlank(getParameterValue(sp, "customerName")))
			condition.add(sb.append("o.source.pk like '").
					append(generateLikeParameter("customerName")).append("'").toString());

		condition.add(
				new StringBuilder("o.createDate between '").
				append(fmt.format(startDate)).
				append("' and '").append(fmt.format(endDate))
		      .append("'").toString());
		
		if(CollectionUtils.isNotEmpty(condition))
		{
			sb.append("where ").append(StringUtils.join(condition.toArray(), " and "));
		}
		
		return findPageByQuery(sb.toString(), pageSize, startIndex);
	}


}
