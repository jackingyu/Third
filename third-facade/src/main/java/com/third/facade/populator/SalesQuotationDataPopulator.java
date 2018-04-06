package com.third.facade.populator;

import com.third.facade.data.SalesQuotationData;
import com.third.facade.data.SourceData;
import com.third.facade.utils.TextMapperUtils;
import com.third.model.SalesQuotationModel;
import com.third.model.SourceModel;

public class SalesQuotationDataPopulator implements Populator<SalesQuotationModel, SalesQuotationData> {

	private SourceDataPopulator sourceDataPopulator;
	
	@Override
	public void populate(SalesQuotationModel source, SalesQuotationData target)
	{
		SourceData sourceData = new SourceData();
		
		sourceDataPopulator.populate(source.getSource(), sourceData);
		target.setPk(source.getPk());
		target.setSource(sourceData);
		target.setCellphone(source.getCellphone());
		target.setCoSalesperson(source.getCoSalesperson());
		target.setCustomerName(source.getCustomerName());
		target.setDeliveryDate(source.getDeliveryDate());
		target.setTotalamount(source.getTotalamount().toString());
		target.setPaidamount(source.getPaidamount().toString());
		target.setPaymentMethod(source.getPaymentMethod());
		target.setPhotoDate(source.getPhotoDate());
		target.setWeddingDate(source.getWeddingDate());
		target.setTryDate(source.getTryDate());
		target.setCreateDate(source.getCreateDate());
		
		if(source.getOrderCode()!=null)
		target.setOrderCode(source.getOrderCode());
	}

	public void setSourceDataPopulator(SourceDataPopulator sourceDataPopulator)
	{
		this.sourceDataPopulator = sourceDataPopulator;
	}
	
}
