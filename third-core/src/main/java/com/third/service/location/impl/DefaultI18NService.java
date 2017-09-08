package com.third.service.location.impl;

import java.util.List;

import com.third.dao.location.AddressDao;
import com.third.dao.location.CityDao;
import com.third.dao.location.RegionDao;
import com.third.model.AddressModel;
import com.third.model.CityModel;
import com.third.model.RegionModel;
import com.third.service.location.I18NService;

public class DefaultI18NService implements I18NService {
	private RegionDao regionDao;
	private CityDao cityDao;
	private AddressDao addressDao;

	@Override
	public void createRegion(RegionModel regionModel)
	{
		regionDao.save(regionModel);
	}

	@Override
	public void createCity(CityModel cityModel)
	{
		cityDao.save(cityModel);
	}

	@Override
	public void createAddress(AddressModel addressModel)
	{
		addressDao.save(addressModel);
	}

	@Override
	public CityModel getCity(String code)
	{
		return cityDao.get(code);
	}

	@Override
	public RegionModel getRegion(String code)
	{
		return regionDao.get(code);
	}

	public void setRegionDao(RegionDao regionDao)
	{
		this.regionDao = regionDao;
	}

	public void setCityDao(CityDao cityDao)
	{
		this.cityDao = cityDao;
	}

	public void setAddressDao(AddressDao addressDao)
	{
		this.addressDao = addressDao;
	}

	@Override
	public List<RegionModel> getRegions()
	{
		return regionDao.list();
	}

	@Override
	public void updateAddress(AddressModel addressModel)
	{
		addressDao.update(addressModel);
	}

}
