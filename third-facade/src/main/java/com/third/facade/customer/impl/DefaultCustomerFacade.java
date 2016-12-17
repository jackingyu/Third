package com.third.facade.customer.impl;

import java.util.ArrayList;
import java.util.List;

import com.third.dao.util.PaginationSupport;
import com.third.exceptions.BussinessException;
import com.third.facade.customer.CustomerFacade;
import com.third.facade.data.AddressData;
import com.third.facade.data.CustomerData;
import com.third.facade.data.ListData;
import com.third.facade.data.SourceData;
import com.third.facade.populator.CustomerDataPopulator;
import com.third.facade.populator.SourceDataPopulator;
import com.third.model.AddressModel;
import com.third.model.CoreConstants;
import com.third.model.CustomerModel;
import com.third.model.SourceModel;
import com.third.model.SubscribeModel;
import com.third.service.customer.CustomerService;
import com.third.service.customer.SourceService;
import com.third.service.customer.SubscribeService;
import com.third.service.location.I18NService;
import com.third.service.user.SessionService;


public class DefaultCustomerFacade implements CustomerFacade
{
	private CustomerDataPopulator customerDataPopulator;
	private SourceDataPopulator sourceDataPopulator;
	private I18NService i18NService;
	private CustomerService customerService;
	private SourceService sourceService;
	private SessionService sessionService;
	private SubscribeService subscribeService;

	@Override
	public void createCustomer(CustomerData customer)
	{
		CustomerModel customerModel = new CustomerModel();

		customerModel.setCellphone(customer.getCellphone());
		customerModel.setName(customer.getName());
		customerModel.setBirthday(customer.getBirthday());
		customerModel.setWeddingDate(customer.getWeddingdate());
		customerModel.setComment(customer.getComment());
		customerModel.setEmail(customer.getEmail());
		customerModel.setQQ(customer.getQQ());

		SourceModel sourceModel = sourceService.getSource(customer.getSource().getPk());
		customerModel.setSource(sourceModel);

		AddressModel addressModel = new AddressModel();
		AddressData address = customer.getAddress();

		addressModel.setAdr1(address.getAdr1());
		addressModel.setAdr2(address.getAdr2());
		addressModel.setCity(i18NService.getCity(address.getCity().getIsoCode()));
		addressModel.setRegion(i18NService.getRegion(address.getRegion().getIsoCode()));
		addressModel.setTel1(address.getTel1());
		addressModel.setTel2(address.getTel2());

		//TODO:need to check if necessay
		//i18NService.createAddress(addressModel);

		customerModel.setAddress(addressModel);

		customerService.createCustomer(customerModel);
	}

	@Override
	public CustomerData getCustomerByCellphone(final String cellphone)
	{
		CustomerModel customer = customerService.getCustomerByCellphone(cellphone);
		CustomerData customerData = new CustomerData();

		if (customer != null)
			customerDataPopulator.populate(customer, customerData);

		return customerData;
	}

	@Override
	public ListData getCustomers(final String cellphone, final String name, final Integer startIndex, final Integer pageSize)
	{
		PaginationSupport result = customerService.getCustomerList(cellphone, name, startIndex, pageSize);
		ListData grid = new ListData();
		grid.setTotal(result.getTotalCount());
		List<Object> customers = new ArrayList<Object>();
		result.getItems().forEach(n -> {
			CustomerData customerData = new CustomerData();
			customerDataPopulator.populate((CustomerModel) n, customerData);
			customers.add(customerData);
		});

		grid.setRows(customers);

		return grid;
	}

	@Override
	public List<SourceData> getSources()
	{
		List<SourceModel> sourceModels = sourceService.getSources();
		List<SourceData> sourceDatas = new ArrayList<SourceData>();

		sourceModels.forEach(s -> {
			SourceData sourceData = new SourceData();
			sourceDataPopulator.populate(s, sourceData);
			sourceDatas.add(sourceData);
		});

		return sourceDatas;

	}

	@Override
	public void updateCustomer(CustomerData customer)
	{
		CustomerModel customerModel = customerService.getCustomerByCellphone(customer.getCellphone());

		customerModel.setName(customer.getName());
		customerModel.setBirthday(customer.getBirthday());
		customerModel.setWeddingDate(customer.getWeddingdate());
		customerModel.setComment(customer.getComment());
		customerModel.setEmail(customer.getEmail());
		customerModel.setQQ(customer.getQQ());

		SourceModel sourceModel = sourceService.getSource(customer.getSource().getPk());
		customerModel.setSource(sourceModel);

		//修改地址信息
		AddressModel addressModel = customerModel.getAddress();
		if (addressModel == null)
		{
			addressModel = new AddressModel();
			i18NService.createAddress(addressModel);
		}

		AddressData address = customer.getAddress();

		addressModel.setAdr1(address.getAdr1());
		addressModel.setAdr2(address.getAdr2());
		addressModel.setCity(i18NService.getCity(address.getCity().getIsoCode()));
		addressModel.setRegion(i18NService.getRegion(address.getRegion().getIsoCode()));
		addressModel.setTel1(address.getTel1());
		addressModel.setTel2(address.getTel2());

		customerModel.setAddress(addressModel);

		customerService.updateCustomer(customerModel);
	}

	 @Override
	public CustomerData bindCustomer(String openId, String cellphone) throws BussinessException{
	    	
			SubscribeModel subscribeModel = subscribeService.getSubscribeModel(openId);
			if(subscribeModel == null) {
				throw new BussinessException("请先关注铂玛微信号");
			}
			
			CustomerModel customerModel = customerService.getCustomerByCellphone(cellphone);
			
			if(customerModel == null) {
				throw new BussinessException("您还不是铂玛会员，请使用加入铂玛功能");
			}
			
			customerModel.setSubscribe(subscribeModel);
//			
//			//如果该客户还没有分组或者分组为默认分组时，设置默认微信分组
//			if ( customer.getCustomerGroupModel() == null || customer.getCustomerGroupModel().getId() == 0 ) {
//				CustomerGroupModel custGroup = customerGroupService.getCustomerGroupModel(0);
//				customer.setCustomerGroupModel(custGroup);
//			} else {
//				try {
//					//同步微信分组
//					this.getWxService().updateCustomerGroup(openId, customer.getCustomerGroupModel().getId().toString());
//				} catch (IOException e) {
//					//Do nothing
//				}
//			}
			
			customerService.updateCustomer(customerModel);
			CustomerData customer = new CustomerData();
			customerDataPopulator.populate(customerModel, customer);
			
			return customer;
	}
	 
	@Override
	public CustomerData getCurrentCustomer()
	{
		//TODO: need to get the user from session
	   CustomerModel customer = customerService.getCustomerByCellphone("13800138000");
	   CustomerData customerData = null;
	   
	   if(customer!=null)
	   {
	   	customerData = new CustomerData();
	   	customerDataPopulator.populate(customer, customerData);
	   }
	   
		return customerData;
	}
//
	@Override
	public void loginSuccess(CustomerData customer)
	{
		sessionService.save(CoreConstants.Session.CURRENT_CUSTOMER,customer);
	}
//	
//	@Override
//	public void logout()
//	{
//		sessionService.clear(CoreConstants.Session.CURRENT_CUSTOMER);
//	}

	public void setCustomerDataPopulator(CustomerDataPopulator customerDataPopulator)
	{
		this.customerDataPopulator = customerDataPopulator;
	}

	public void setSourceDataPopulator(SourceDataPopulator sourceDataPopulator)
	{
		this.sourceDataPopulator = sourceDataPopulator;
	}

	public void setI18NService(I18NService i18nService)
	{
		i18NService = i18nService;
	}

	public void setCustomerService(CustomerService customerService)
	{
		this.customerService = customerService;
	}

	public void setSourceService(SourceService sourceService)
	{
		this.sourceService = sourceService;
	}

	public void setSessionService(SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public void setSubscribeService(SubscribeService subscribeService)
	{
		this.subscribeService = subscribeService;
	}

	@Override
	public CustomerData loginCustomer(String openId)
	{
		CustomerModel customerModel = customerService.getCustomerByOpenId(openId);
		if(customerModel == null)
			return null;
		
		CustomerData customer = new CustomerData();
		customerDataPopulator.populate(customerModel, customer);
		return customer;
	}
	
}
