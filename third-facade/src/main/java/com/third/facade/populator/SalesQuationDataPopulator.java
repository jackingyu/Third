package com.third.facade.populator;

import com.third.facade.data.SalesQuationData;
import com.third.facade.data.SourceData;
import com.third.facade.utils.TextMapperUtils;
import com.third.model.SalesQuationModel;
import com.third.model.SourceModel;

public class SalesQuationDataPopulator implements Populator<SalesQuationModel, SalesQuationData> {

	private SourceDataPopulator sourceDataPopulator;
	
	@Override
	public void populate(SalesQuationModel source, SalesQuationData target)
	{
		SourceData sourceData = new SourceData();
		
		sourceDataPopulator.populate(source.getSource(), sourceData);
		target.setPk(source.getPk());
		target.setSource(sourceData);
		target.setCellphone(source.getCellphone());
		target.setCoSalesperson(source.getCoSalesperson());
		target.setCustomerName(source.getCustomerName());
		target.setDeliveryDate(source.getDeliveryDate());
		target.setPaidamount(source.getPaidamount().toString());
		target.setPaymentMethod(source.getPaymentMethod());
		target.setPhotoDate(source.getPhotoDate());
		target.setWeddingDate(source.getWeddingDate());
		target.setTryDate(source.getTryDate());
		
	}

	public void setSourceDataPopulator(SourceDataPopulator sourceDataPopulator)
	{
		this.sourceDataPopulator = sourceDataPopulator;
	}
	
}
