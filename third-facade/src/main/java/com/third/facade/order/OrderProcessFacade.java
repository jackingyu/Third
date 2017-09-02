package com.third.facade.order;

import com.third.exceptions.NoQualifiedTargetStatusException;
import com.third.exceptions.NotFoundException;
import com.third.facade.data.ListData;
import com.third.facade.data.OrderData;

public interface OrderProcessFacade
{
   void processOrder(String orderCode,Integer toStatus) throws NoQualifiedTargetStatusException, NotFoundException;
   
   void processOrderEntry(String entryPK,Integer toStatus) throws NoQualifiedTargetStatusException, NotFoundException;
   
	ListData getProcessRecordForOrder(String orderCode);
}
