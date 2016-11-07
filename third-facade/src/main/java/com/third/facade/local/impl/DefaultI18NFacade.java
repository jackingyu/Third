package com.third.facade.local.impl;

import java.util.ArrayList;
import java.util.List;

import com.third.facade.data.CityData;
import com.third.facade.data.RegionData;
import com.third.facade.local.I18NFacade;
import com.third.facade.populator.CityDataPopulator;
import com.third.facade.populator.RegionDataPopulator;
import com.third.model.CityModel;
import com.third.model.RegionModel;
import com.third.service.location.I18NService;


public class DefaultI18NFacade implements I18NFacade
{
	private I18NService i18NService;
	private RegionDataPopulator regionDataPopulator;
	private CityDataPopulator cityDataPopulator;

	@Override
	public List<RegionData> getRegions()
	{
		List<RegionModel> regions = i18NService.getRegions();
		List<RegionData> regionDatas = new ArrayList<RegionData>();

		regions.forEach(r -> {
			RegionData regionData = new RegionData();
			regionDataPopulator.populate(r, regionData);
			regionDatas.add(regionData);

		});

		return regionDatas;
	}

	@Override
	public List<CityData> getCityForRegion(String regionISOCode)
	{
		RegionModel region = i18NService.getRegion(regionISOCode);
		List<CityModel> citys = (List<CityModel>) region.getCitys();
		List<CityData> cityDatas = new ArrayList<CityData>();
		citys.forEach(c -> {
			CityData cityData = new CityData();
			cityDataPopulator.populate(c, cityData);
			cityDatas.add(cityData);
		});

		return cityDatas;
	}

	public void setI18NService(I18NService i18nService)
	{
		i18NService = i18nService;
	}

	public void setRegionDataPopulator(RegionDataPopulator regionDataPopulator)
	{
		this.regionDataPopulator = regionDataPopulator;
	}

	public void setCityDataPopulator(CityDataPopulator cityDataPopulator)
	{
		this.cityDataPopulator = cityDataPopulator;
	}

}
