package com.third.facade.populator;

import com.third.facade.data.AddressData;
import com.third.facade.data.CityData;
import com.third.facade.data.DistrictData;
import com.third.facade.data.RegionData;
import com.third.model.AddressModel;

public class AddressDataPopulator
		implements Populator<AddressModel, AddressData> {
	private CityDataPopulator cityDataPopulator;
	private DistrictDataPopulator districtDataPopulator;
	private RegionDataPopulator regionDataPopulator;

	@Override
	public void populate(AddressModel source, AddressData target)
	{
		CityData city = new CityData();
		cityDataPopulator.populate(source.getCity(), city);

		RegionData region = new RegionData();
		regionDataPopulator.populate(source.getRegion(), region);

		DistrictData district = new DistrictData();
		districtDataPopulator.populate(source.getDistrict(), district);
		target.setCity(city);
		target.setRegion(region);
		target.setDistrict(district);
		target.setAdr1(source.getAdr1());
		target.setAdr2(source.getAdr2());
		target.setTel1(source.getTel1());
		target.setTel2(source.getTel2());
		target.setPk(source.getPk());

	}

	public void setCityDataPopulator(CityDataPopulator cityDataPopulator)
	{
		this.cityDataPopulator = cityDataPopulator;
	}

	public void setRegionDataPopulator(RegionDataPopulator regionDataPopulator)
	{
		this.regionDataPopulator = regionDataPopulator;
	}

	public void setDistrictDataPopulator(
			DistrictDataPopulator districtDataPopulator)
	{
		this.districtDataPopulator = districtDataPopulator;
	}

}
