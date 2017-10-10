package com.third.facade.testdata.builder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.third.model.AddressModel;
import com.third.model.CityModel;
import com.third.model.DistrictModel;
import com.third.model.RegionModel;
import com.third.service.location.I18NService;

public class AddressDataBuilder implements DataBuilder {
	private I18NService i18NService;
	private String filename;

	@Override
	public void buildData()
	{
		List<String[]> results = ExcelFileReader.readFile(filename, 8);
		results.stream().filter( r -> r[0].equals("1")).forEach( r->{
			buildRegion(r[1],r[2]);
		});
		
		results.stream().filter( r -> r[0].equals("2")).forEach( r->{
			RegionModel region = i18NService.getRegion(r[3]);
			buildCity(r[1],r[2],region);
		});
		
		results.stream().filter( r -> r[0].equals("3")).forEach( r->{
			CityModel city = i18NService.getCity(r[3]);
			buildDistrict(r[1],r[2],city);
		});

	}

	public RegionModel buildRegion(final String code, final String name)
	{
		RegionModel region = new RegionModel();
		region.setName(name);
		region.setIsoCode(code);
		i18NService.createRegion(region);
		return region;
	}

	public CityModel buildCity(final String code, final String name,
			RegionModel region)
	{
		CityModel city = new CityModel();
		city.setIsoCode(code);
		city.setName(name);
		city.setRegion(region);
		i18NService.createCity(city);
		return city;
	}
	
	public DistrictModel buildDistrict(final String code, final String name,
			CityModel city)
	{
		DistrictModel district = new DistrictModel();
		district.setIsoCode(code);
		district.setName(name);
		district.setCity(city);
		i18NService.createDistrict(district);
		return district;
	}

	public AddressModel buildAddress(final RegionModel region,
			final CityModel city)
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

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}
	
}
