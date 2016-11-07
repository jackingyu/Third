package com.third.facade.populator;

import com.third.facade.data.AddressData;
import com.third.facade.data.CustomerData;
import com.third.facade.data.SourceData;
import com.third.model.CustomerModel;


public class CustomerDataPopulator implements Populator<CustomerModel, CustomerData>
{
	private AddressDataPopulator addressDataPopulator;
	private SourceDataPopulator sourceDataPopulator;

	@Override
	public void populate(CustomerModel source, CustomerData target)
	{
		target.setPk(source.getPk());
		target.setBirthday(source.getBirthday());
		target.setWeddingday(source.getWeddingDay());
		target.setCellphone(source.getCellphone());
		target.setComment(source.getComment());
		target.setEmail(source.getEmail());
		target.setQQ(source.getQQ());
		target.setName(source.getName());

		AddressData address = new AddressData();
		addressDataPopulator.populate(source.getAddress(), address);

		target.setAddress(address);

		SourceData sourceData = new SourceData();
		if (source.getSource() != null)
			sourceDataPopulator.populate(source.getSource(), sourceData);

		target.setSource(sourceData);
	}

	public void setAddressDataPopulator(AddressDataPopulator addressDataPopulator)
	{
		this.addressDataPopulator = addressDataPopulator;
	}

	public void setSourceDataPopulator(SourceDataPopulator sourceDataPopulator)
	{
		this.sourceDataPopulator = sourceDataPopulator;
	}

}
