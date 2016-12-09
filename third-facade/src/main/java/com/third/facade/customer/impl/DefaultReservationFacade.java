package com.third.facade.customer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.third.dao.util.PaginationSupport;
import com.third.facade.customer.ReservationFacade;
import com.third.facade.data.ListData;
import com.third.facade.data.ReservationData;
import com.third.facade.populator.ReservationDataPopulator;
import com.third.model.CustomerModel;
import com.third.model.ReservationModel;
import com.third.service.customer.CustomerService;
import com.third.service.customer.ReservationService;
import com.third.service.store.StoreService;

public class DefaultReservationFacade implements ReservationFacade
{
	private ReservationService reservationService;
	private StoreService storeService;
	private CustomerService customerService;
	private ReservationDataPopulator reservationDataPopulator;

	@Override
	public String createReservation(ReservationData reservationData)
	{
		ReservationModel reservation = new ReservationModel();
		reservation.setCellphone(reservationData.getCellphone());
		reservation.setChannel(reservationData.getChannel());
		reservation.setName(reservationData.getName());
		reservation.setReservationDate(reservationData.getReservationDate());
		
		reservation.setStore(storeService.getStoreForCode(reservationData.getStore().getCode()));
		reservation.setComment(reservationData.getComment());
		CustomerModel customer = customerService.getCustomerByCellphone(reservationData.getCellphone());
		if(customer != null)
		{
			reservation.setCustomer(customer);
		}
		
		reservationService.createrReservation(reservation);
		
		return reservation.getPk();
	}

	@Override
	public void updateReservation(ReservationData reservationData)
	{
		ReservationModel reservation = reservationService.getReservation(reservationData.getPk());
		reservation.setCellphone(reservationData.getCellphone());
		reservation.setChannel(reservationData.getChannel());
		reservation.setName(reservationData.getName());
		reservation.setReservationDate(reservationData.getReservationDate());
		
		reservation.setStore(storeService.getStoreForCode(reservationData.getStore().getCode()));
		reservation.setComment(reservationData.getComment());
		CustomerModel customer = customerService.getCustomerByCellphone(reservationData.getCellphone());
		if(customer != null)
		{
			reservation.setCustomer(customer);
		}
		
		reservationService.updateReservation(reservation);
	}
	
	@Override
	public ReservationData getReservation(final String reservationPK)
	{
		ReservationModel reservation = reservationService.getReservation(reservationPK);
		ReservationData reservationData = new ReservationData();
		reservationDataPopulator.populate(reservation, reservationData);
		
		return reservationData;
	}

	@Override
	public ListData getReservations(String storeCode,String cellphone, String name, Date fromDate,Date toDate,Integer startIndex, Integer pageSize)
	{
		PaginationSupport ps = reservationService.getReservationList(storeCode, cellphone, name, fromDate, toDate, startIndex, pageSize);
		List<ReservationModel> reservations = ps.getItems();
		List<Object> reservationDatas  = new ArrayList<Object>();
		reservations.forEach( r ->{
			ReservationData reservationData = new ReservationData();
			reservationDataPopulator.populate(r, reservationData);
			reservationDatas.add(reservationData);
			
		});
		
		ListData result = new ListData();
		result.setTotal(ps.getTotalCount());
		result.setRows(reservationDatas);
		
		return result;
	}

	public void setReservationService(ReservationService reservationService)
	{
		this.reservationService = reservationService;
	}

	public void setReservationDataPopulator(ReservationDataPopulator reservationDataPopulator)
	{
		this.reservationDataPopulator = reservationDataPopulator;
	}

	public void setStoreService(StoreService storeService)
	{
		this.storeService = storeService;
	}

	public void setCustomerService(CustomerService customerService)
	{
		this.customerService = customerService;
	}

}
