package com.third.facade.testdata.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.third.core.constants.CoreConstants;
import com.third.model.SalesQuotationModel;
import com.third.model.SourceModel;
import com.third.model.StoreModel;
import com.third.service.customer.SourceService;
import com.third.service.order.SalesQuotationService;
import com.third.service.store.StoreService;

public class SalesQuotationDataBuilder implements DataBuilder {

	private SalesQuotationService salesQuotationService;
	private StoreService storeService;
	private SourceService sourceService;

	public void buildData()
	{
		StoreModel store = storeService.getStoreForCode("s-1");
	
		List<SourceModel> exhibitions = new ArrayList<SourceModel>();
		
		for(int i = 0;i < 5;i++)
		{
			SourceModel source = new SourceModel();
			source.setName("展会S-1-"+i);
			source.setType(CoreConstants.SourceType.EXHIBITION);
			sourceService.saveSource(source);
			exhibitions.add(source);
		}
		store.getSources().addAll(exhibitions);
		storeService.saveStore(store);
		
		for (int i = 0; i < 30; i++)
		{
			SalesQuotationModel sq = new SalesQuotationModel();
			sq.setCellphone("1380013800" + i);
			sq.setCustomerName("测试用户" + i);
			sq.setPaidamount(new BigDecimal(1000));
			sq.setPaymentMethod(CoreConstants.PaymentMethod.Cash);
			sq.setWeddingDate(new Date());
			sq.setTryDate(new Date());
			sq.setCreateDate(new Date());
			sq.setSource(exhibitions.get(RandomUtils.nextInt(0,4)));
			salesQuotationService.saveSalesQuotation(sq);
		}
	}

	public SalesQuotationService getSalesQuotationService()
	{
		return salesQuotationService;
	}

	public void setSalesQuotationService(SalesQuotationService salesQuotationService)
	{
		this.salesQuotationService = salesQuotationService;
	}

	public void setStoreService(StoreService storeService)
	{
		this.storeService = storeService;
	}

	public void setSourceService(SourceService sourceService)
	{
		this.sourceService = sourceService;
	}
	
}
