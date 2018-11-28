package com.third.dao.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.third.dao.generic.GenericDAO;
import com.third.dao.order.OrderProcessRecordDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.OrderProcessRecordModel;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultOrderProcessRecordDao
		extends GenericDAO<OrderProcessRecordModel, String>
		implements OrderProcessRecordDao {
	private final static String FIND_BY_ORDERCODE_SQL = "from com.third.model.OrderProcessRecordModel op where op.orderCode=?";

	@Override
	public PaginationSupport findOrderProcessRecord(Date startDate,
			Date endDate, Integer startIndex, Integer pageSize,
			Map<String, String[]> sp)
	{
	    StringBuilder sb = new StringBuilder("select r.storeName,r.name,r.productCode,r.quantity,r.sizeOrderPk,r.sizeOrderExternalId,"
	            + "r.processTime from OrderProcessRecordModel r ");

      List<String> condition = new ArrayList<String>();

      String c1 = getArrayCondtion(sp, "storeCodes", "r.storeCode");
      if (StringUtils.isNotEmpty(c1))
          condition.add(c1);

      String c2 = getArrayCondtion(sp, "externalIds", "r.sizeOrderExternalId");
      
      if (StringUtils.isNotEmpty(c2))
          condition.add(c2);
      
      String c3 = getArrayCondtion(sp, "orderStatus", "r.toStatus");
      
      if (StringUtils.isNotEmpty(c3))
          condition.add(c3);

      condition.add(new StringBuilder("r.processTime between '")
              .append(fmt.format(startDate)).append("' and '")
              .append(fmt.format(endDate)).append("'").toString());
      sb.append("where ")
              .append(StringUtils.join(condition.toArray(), " and "));
      
      String hsql = sb.toString();

      return this.findPageByQuery(hsql, pageSize, startIndex);
	}

	@Override
	public List<OrderProcessRecordModel> findOrderProcessForOrder(
			String orderCode)
	{
		List<OrderProcessRecordModel> or = find(FIND_BY_ORDERCODE_SQL,
				orderCode);
		return or;
	}

}
