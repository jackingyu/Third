package com.third.facade.local;

import java.util.List;

import com.third.facade.data.CityData;
import com.third.facade.data.DistrictData;
import com.third.facade.data.RegionData;

public interface I18NFacade {
	List<RegionData> getRegions();

	List<CityData> getCityForRegion(final String regionISOCode);
	
	List<DistrictData> getDistrictForCity(final String cityISOCode);
}
