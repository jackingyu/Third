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
import com.third.dao.order.SalesQuotationDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.SalesQuotationModel;

public class DefaultSalesQuotationDao extends
		GenericDAO<SalesQuotationModel, String> implements SalesQuotationDao {

	@Override
	public PaginationSupport findSalesQuotations(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String> sp)
	{
		final StringBuilder sb = new StringBuilder(
				"select o.source.name, o.customerName,o.cellphone,o.paidamount,o.paymentMethod,o.pk from SalesQuotationModel o ");

		List<String> condition = new ArrayList<String>();

		if (StringUtils.isNotBlank(getParameterValue(sp, "cellphone")))
			condition.add(new StringBuilder("o.cellphone = '")
					.append(getParameterValue(sp, "cellphone")).append("'")
					.toString());

		String exhibitions = sp.get("exhibitions");
		
		if(StringUtils.isNotEmpty(exhibitions))
		{
		    
			String[] exhibitionArray = exhibitions.split(",");
			if(exhibitionArray.length >1 )
			{
			    String csql = this.convertArray(exhibitionArray, "o.source.pk");
			    condition.add(csql);
			}
			else
				condition.add(new StringBuilder("o.source.pk = '")
						.append(exhibitionArray[0]).append("'")
						.toString());
		}
	

		if (StringUtils.isNotBlank(getParameterValue(sp, "customerName")))
			condition.add(new StringBuilder("o.customerName like '")
					.append(generateLikeParameter("customerName")).append("'")
					.toString());

		condition.add(new StringBuilder("o.createDate between '")
				.append(fmt.format(startDate)).append("' and '")
				.append(fmt.format(endDate)).append("'").toString());

		if (CollectionUtils.isNotEmpty(condition))
		{
			sb.append("where ")
					.append(StringUtils.join(condition.toArray(), " and "));
		}

		return findPageByQuery(sb.toString(), pageSize, startIndex);
	}

}
