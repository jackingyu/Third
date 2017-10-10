package com.third.facade.testdata.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;

import com.third.core.constants.CoreConstants;
import com.third.dao.user.UserDao;
import com.third.model.AddressModel;
import com.third.model.CityModel;
import com.third.model.CustomerModel;
import com.third.model.DistrictModel;
import com.third.model.RegionModel;
import com.third.model.SourceModel;
import com.third.model.StoreModel;
import com.third.model.UserModel;
import com.third.service.customer.CustomerService;
import com.third.service.customer.SourceService;
import com.third.service.location.I18NService;
import com.third.service.store.StoreService;
import com.third.service.user.UserService;

public class StoreDataBuilder implements DataBuilder {
	private String filename;
	@Resource(name = "i18NService")
	private I18NService i18NService;
	@Resource(name = "customerService")
	private CustomerService customerService;
	@Resource(name = "storeService")
	private StoreService storeService;
	@Resource(name = "sourceService")
	private SourceService sourceService;
	@Resource(name = "userDao")
	private UserDao userDao;

	private List<SourceModel> sourceModels = new ArrayList<SourceModel>();;

	@Override
	public void buildData()
	{
		List<String[]> results = ExcelFileReader.readFile(filename, 9);
		
		results.forEach(r->{
			StoreModel store = new StoreModel();
			store.setId(r[0]);
			store.setName(r[1]);
			
			AddressModel addressModel = new AddressModel();
			RegionModel region = i18NService.getRegion(r[2]);
			CityModel city = i18NService.getCity(r[3]);
			DistrictModel district = i18NService.getDistrict(r[4]);
			addressModel.setCity(city);
			addressModel.setRegion(region);
			addressModel.setDistrict(district);
			addressModel.setAdr1(r[5]);;
			addressModel.setAdr2(r[6]);;
			addressModel.setTel1(r[7]);;
			addressModel.setTel2(r[8]);;
			
			i18NService.createAddress(addressModel);
			
			store.setAddress(addressModel);
			
			storeService.createStore(store);
		});

	}

	public SourceModel buildSource(final String name)
	{
		SourceModel source = new SourceModel();
		source.setName(name);
		source.setType(CoreConstants.SourceType.NORMAL);
		sourceService.createSource(source);
		return source;
	}
	
	public SourceModel buildExhibition(final String name)
	{
		SourceModel source = new SourceModel();
		source.setName(name);
		source.setType(CoreConstants.SourceType.EXHIBITION);
		sourceService.createSource(source);
		return source;
	}

	public StoreModel buildStore(final String id, final String name,
			final AddressModel address)
	{
		StoreModel store = new StoreModel();
		store.setId(id);
		store.setAddress(address);
		store.setName(name);
		List<SourceModel> sources = new ArrayList<SourceModel>();

		int j = RandomUtils.nextInt(10, 15);

		for (int i = 0; i < j; i++)
		{
			sources.add(sourceModels.get(i));
		}

		//store.setSources(sources);
		storeService.createStore(store);

		return store;
	}

	public CustomerModel buildCustomer(final String cellphone,
			final String name, final AddressModel address)
	{
		CustomerModel customer = new CustomerModel();
		customer.setCellphone(cellphone);
		customer.setName(name);
		customer.setAddress(address);
		customer.setBirthday(new Date());
		customer.setWeddingDate(new Date());
		customer.setEmail("dd@tt.com");
		customer.setComment("yekongzhongzuiliangdexing");
		customer.setQQ("33445566");
		customerService.createCustomer(customer);
		return customer;
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

	public AddressModel buildAddress(final RegionModel region,
			final CityModel city, final String adr1)
	{
		AddressModel address = new AddressModel();
		address.setCity(city);
		address.setRegion(region);
		address.setAdr1(adr1);
		address.setTel1("02160306796");
		i18NService.createAddress(address);
		return address;
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
