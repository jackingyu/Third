package com.third.dao.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.third.dao.generic.GenericDAO;
import com.third.dao.order.OrderEntryDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.OrderEntryModel;

public class DefaultOrderEntryDao extends GenericDAO<OrderEntryModel, String>
		implements OrderEntryDao {
	private Log logger = LogFactory.getLog(getClass());
	private final String COUNT_ENTRY_BY_ORDER = "select count(*) from com.third.model.OrderEntryModel e where e.order.pk=:orderPK";

	@Override
	public Integer countOrderEntryByOrder(final String orderPK)
	{
		Long numberOfEntry = (Long) this.currentSession()
				.createQuery(COUNT_ENTRY_BY_ORDER)
				.setParameter("orderPK", orderPK).uniqueResult();
		return numberOfEntry.intValue();
	}

	@Override
	public OrderEntryModel findOrderEntryByExternalId(final String externalId)
	{
		DetachedCriteria dcOrderEntry = DetachedCriteria
				.forClass(OrderEntryModel.class);
		dcOrderEntry.add(Restrictions.eq("externalId", externalId));
		List<OrderEntryModel> entry = findByCriteria(dcOrderEntry);

		return CollectionUtils.isNotEmpty(entry) ? entry.get(0) : null;
	}

	@Override
	public PaginationSupport findOrderEntries(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String> sp)
	{
		final StringBuilder sb = new StringBuilder(
				"select e.itemCategory,e.externalId,e.deliveryDate,e.customerName,e.store.name,e.status,e.pk from OrderEntryModel  e "
						+ " where ");

		List<String> condition = new ArrayList<String>();

		if (StringUtils.isNotBlank(getParameterValue(sp, "externalId")))
		{
			StringBuilder c = new StringBuilder().append("e.externalId = '")
					.append(getParameterValue(sp, "externalId")).append("'");
			condition.add(c.toString());
		}

		if (StringUtils.isNotBlank(getParameterValue(sp, "status")))
		{
			StringBuilder c = new StringBuilder().append("e.status = ")
					.append(getParameterValue(sp, "status"));
			condition.add(c.toString());
		}

		// TODO if there is no store parameter get store from session
		if (StringUtils.isNotBlank(getParameterValue(sp, "storeCodes")))
		{
			String[] storeArray = getParameterValue(sp, "storeCodes")
					.split(",");
			StringBuilder c = new StringBuilder();

			if (storeArray.length == 1)
				c = c.append("e.store.id = '").append(storeArray[0])
						.append("'");
			else if (storeArray.length > 1)
			{
				c.append("e.store.id in (");
				for (int i = 0; i < storeArray.length; i++)
				{
					if (i == storeArray.length - 1)
						c.append("'").append(storeArray[i]).append("') ");
					else
						c.append("'").append(storeArray[i]).append("',");
				}
			}

			condition.add(c.toString());
		}

		if (StringUtils.isNotBlank(getParameterValue(sp, "name")))
		{
			StringBuilder c = new StringBuilder().append("e.customerName like '%")
					.append(getParameterValue(sp, "name")).append("%'");
			condition.add(c.toString());
		}

		if (CollectionUtils.isNotEmpty(condition))
		{

			for (int i = 0; i < condition.size(); i++)
			{
				sb.append(condition.get(i)).append(" and ");
			}

		}

		sb.append("e.order.deliveryDate between '")
				.append(fmt.format(startDate)).append("' and '")
				.append(fmt.format(endDate)).append("'");
		sb.append(" order by e.order.deliveryDate asc");
		logger.info(sb.toString());
		return findPageByQuery(sb.toString(), pageSize, startIndex);
	}
}
