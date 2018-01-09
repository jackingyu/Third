package com.third.facade.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.exceptions.NoQualifiedTargetStatusException;
import com.third.exceptions.NotFoundException;
import com.third.facade.data.DTResults;
import com.third.facade.data.ListData;
import com.third.facade.data.OrderData;

public interface OrderProcessFacade {
	void processOrder(String orderCode, Integer toStatus)
			throws NoQualifiedTargetStatusException, NotFoundException;

	void processOrderEntry(String entryPK, Integer toStatus)
			throws NoQualifiedTargetStatusException, NotFoundException;

	ListData getProcessRecordForOrder(String orderCode);

    DTResults getOrderProcessRecords(Date startProcessTime, Date endProcessTime,
            Integer startIndex, Integer pageSize, Map<String, String[]> sp);

    List<Object[]> exportOrderProcessRecords(Date startDate, Date endDate,
            int startIndex, int pageSize, Map<String, String[]> sp);
}
