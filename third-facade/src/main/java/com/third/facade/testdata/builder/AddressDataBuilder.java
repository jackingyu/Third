package com.third.facade.testdata.builder;

import org.springframework.beans.factory.annotation.Autowired;

import com.third.model.AddressModel;
import com.third.model.CityModel;
import com.third.model.RegionModel;
import com.third.service.location.I18NService;


public class AddressDataBuilder implements DataBuilder
{
	private I18NService i18NService;

	@Override
	public void buildData()
	{
		for (int j = 0; j < 9; j++)
		{
			RegionModel region = buildRegion("CN-0" + j, "测试省" + j);

			for (int i = 0; i < 9; i++)
			{
				CityModel city = buildCity("CN-0" + j + i, "测试城市-" + j + "-" + i, region);
				AddressModel address = buildAddress(region, city);
			}
		}

	}

	public RegionModel buildRegion(final String code, final String name)
	{
		RegionModel region = new RegionModel();
		region.setName(name);
		region.setIsoCode(code);
		i18NService.createRegion(region);
		return region;
	}

	public CityModel buildCity(final String code, final String name, RegionModel region)
	{
		CityModel city = new CityModel();
		city.setIsoCode(code);
		city.setName(name);
		city.setRegion(region);
		i18NService.createCity(city);
		return city;
	}

	public AddressModel buildAddress(final RegionModel region, final CityModel city)
	{
		AddressModel address = new AddressModel();
		address.setCity(city);
		address.setRegion(region);
		i18NService.createAddress(address);
		return address;
	}

	public void setI18NService(I18NService i18nService)
	{
		i18NService = i18nService;
	}

}
