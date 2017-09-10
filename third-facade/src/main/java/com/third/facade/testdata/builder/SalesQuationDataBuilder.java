package com.third.facade.testdata.builder;

import java.math.BigDecimal;
import java.util.Date;

import com.third.model.CoreConstants;
import com.third.model.SalesQuationModel;
import com.third.service.order.SalesQuationService;

public class SalesQuationDataBuilder implements DataBuilder {

	private SalesQuationService salesQuationService;

	public void buildData()
	{
		for (int i = 0; i < 30; i++)
		{
			SalesQuationModel sq = new SalesQuationModel();
			sq.setCellphone("1380013800" + i);
			sq.setCustomerName("测试用户" + i);
			sq.setPaidamount(new BigDecimal(1000));
			sq.setPaymentMethod(CoreConstants.PaymentMethod.Cash);
			sq.setWeddingDate(new Date());
			sq.setTryDate(new Date());
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

}
