package com.third.facade.testdata.builder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.third.dao.user.UserDao;
import com.third.model.AddressModel;
import com.third.model.CityModel;
import com.third.model.CustomerModel;
import com.third.model.RegionModel;
import com.third.model.SourceModel;
import com.third.model.StoreModel;
import com.third.model.UserModel;
import com.third.service.customer.CustomerService;
import com.third.service.customer.SourceService;
import com.third.service.location.I18NService;
import com.third.service.store.StoreService;
import com.third.service.user.UserService;


public class StoreDataBuilder implements DataBuilder
{
	private I18NService i18NService;
	private CustomerService customerService;
	private StoreService storeService;
	@Resource(name = "sourceService")
	private SourceService sourceService;
	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	public void buildData()
	{
		RegionModel region = buildRegion("cn11", "customer region");
		CityModel city = buildCity("cn12", "customer city", region);
		AddressModel address = buildAddress(region, city, "shanghai street");
		StoreModel store = buildStore("s-1", "南方一店", address);

		List<UserModel> userList = userDao.list();

		for (int i = 0; i < userList.size(); i++)
		{
			UserModel user = userList.get(i);

			user.setStores(Arrays.asList(store));

			userDao.update(user);
		}

		for (int i = 0; i < 100; i++)
		{
			AddressModel address1 = buildAddress(region, city, "shanghai street" + i);
			buildCustomer("13800138900" + i, "fly" + i, address1);
		}

		for (int i = 0; i < 100; i++)
		{
			buildSource("客户来源" + i);
		}

	}

	public SourceModel buildSource(final String name)
	{
		SourceModel source = new SourceModel();
		source.setName(name);
		source.setType("undefined");
		sourceService.createSource(source);
		return source;
	}

	public StoreModel buildStore(final String id, final String name, final AddressModel address)
	{
		StoreModel store = new StoreModel();
		store.setId(id);
		store.setAddress(address);
		store.setName(name);
		storeService.createStore(store);

		return store;
	}


	public CustomerModel buildCustomer(final String cellphone, final String name, final AddressModel address)
	{
		CustomerModel customer = new CustomerModel();
		customer.setCellphone(cellphone);
		customer.setName(name);
		customer.setAddress(address);
		customer.setBirthday(new Date());
		customer.setWeddingDay(new Date());
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

	public CityModel buildCity(final String code, final String name, RegionModel region)
	{
		CityModel city = new CityModel();
		city.setIsoCode(code);
		city.setName(name);
		city.setRegion(region);
		i18NService.createCity(city);
		return city;
	}

	public AddressModel buildAddress(final RegionModel region, final CityModel city, final String adr1)
	{
		AddressModel address = new AddressModel();
		address.setCity(city);
		address.setRegion(region);
		address.setAdr1(adr1);
		i18NService.createAddress(address);
		return address;
	}

	public void setI18NService(I18NService i18nService)
	{
		i18NService = i18nService;
	}

	public void setCustomerService(CustomerService customerService)
	{
		this.customerService = customerService;
	}

	public void setStoreService(StoreService storeService)
	{
		this.storeService = storeService;
	}

}
