package com.third.service.location;

import java.util.List;

import com.third.model.AddressModel;
import com.third.model.CityModel;
import com.third.model.DistrictModel;
import com.third.model.RegionModel;

public interface I18NService {
	void createRegion(RegionModel regionModel);

	void createCity(CityModel cityModel);
	
	void createDistrict(DistrictModel districtModel);

	void createAddress(AddressModel addressModel);

	void updateAddress(AddressModel addressModel);

	CityModel getCity(final String code);

	RegionModel getRegion(final String code);

	List<RegionModel> getRegions();

	DistrictModel getDistrict(String isoCode);

}
