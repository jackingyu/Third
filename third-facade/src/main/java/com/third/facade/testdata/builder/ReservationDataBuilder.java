package com.third.facade.testdata.builder;

import java.util.Date;

import javax.annotation.Resource;

import com.third.core.constants.CoreConstants;
import com.third.dao.location.CityDao;
import com.third.model.CityModel;
import com.third.model.CustomerModel;
import com.third.model.ReservationModel;
import com.third.model.StoreModel;
import com.third.service.customer.CustomerService;
import com.third.service.customer.ReservationService;
import com.third.service.store.StoreService;

public class ReservationDataBuilder implements DataBuilder {
	@Resource(name = "reservationService")
	private ReservationService reservationService;

	@Resource(name = "cityDao")
	private CityDao cityDao;

	@Resource(name = "storeService")
	private StoreService storeService;

	@Resource(name = "customerService")
	private CustomerService customerService;

	@Override
	public void buildData()
	{
		CustomerModel customer = customerService
				.getCustomerByCellphone("13800138000");
		StoreModel store = storeService.getStoreForCode("s-2");
		CityModel city = cityDao.get("cn12");

		for (int i = 0; i < 20; i++)
		{
			ReservationModel reservation = new ReservationModel();
			reservation.setChannel(CoreConstants.ReservationChannel.Web);
			reservation.setCity(city);
			reservation.setCustomer(customer);
			reservation.setName("tt");
			reservation.setCellphone(customer.getCellphone());
			reservation.setReservationDate(new Date());
			reservation.setStatus(0);
			reservation.setStore(store);
			reservationService.createrReservation(reservation);
		}

		store = storeService.getStoreForCode("s-1");
		for (int i = 0; i < 20; i++)
		{
			ReservationModel reservation = new ReservationModel();
			reservation.setChannel(CoreConstants.ReservationChannel.Weixin);
			reservation.setCity(city);
			reservation.setCustomer(customer);
			reservation.setName("tt");
			reservation.setCellphone(customer.getCellphone());
			reservation.setReservationDate(new Date());
			reservation.setStatus(0);
			reservation.setStore(store);
			reservationService.createrReservation(reservation);
		}

	}

}
