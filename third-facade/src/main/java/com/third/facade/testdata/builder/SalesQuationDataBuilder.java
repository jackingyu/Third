package com.third.facade.testdata.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.third.model.CoreConstants;
import com.third.model.SalesQuationModel;
import com.third.model.SourceModel;
import com.third.model.StoreModel;
import com.third.service.customer.SourceService;
import com.third.service.order.SalesQuationService;
import com.third.service.store.StoreService;

public class SalesQuationDataBuilder implements DataBuilder {

	private SalesQuationService salesQuationService;
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
			SalesQuationModel sq = new SalesQuationModel();
			sq.setCellphone("1380013800" + i);
			sq.setCustomerName("测试用户" + i);
			sq.setPaidamount(new BigDecimal(1000));
			sq.setPaymentMethod(CoreConstants.PaymentMethod.Cash);
			sq.setWeddingDate(new Date());
			sq.setTryDate(new Date());
			sq.setCreateDate(new Date());
			sq.setSource(exhibitions.get(RandomUtils.nextInt(0,4)));
			salesQuationService.saveSalesQuation(sq);
		}
	}

	public SalesQuationService getSalesQuationService()
	{
		return salesQuationService;
	}

	public void setSalesQuationService(SalesQuationService salesQuationService)
	{
		this.salesQuationService = salesQuationService;
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
