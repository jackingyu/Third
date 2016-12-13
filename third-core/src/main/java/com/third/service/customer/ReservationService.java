package com.third.service.customer;

import java.util.Date;
import java.util.List;

import com.third.dao.util.PaginationSupport;
import com.third.model.ReservationModel;

public interface ReservationService
{
	public void createrReservation(ReservationModel reservation);

	public void updateReservation(ReservationModel reservation);
	
	public ReservationModel getReservation(final String reservationPK);
	
	public List<ReservationModel> getReservationsForCustomer(final String customerPK);

	PaginationSupport getReservationList(String storePK, String cellphone, String name, Date from, Date to, Integer startIndex,
			Integer pageSize);
}
