package com.third.facade.customer;

import java.util.Date;

import com.third.facade.data.ListData;
import com.third.facade.data.ReservationData;

public interface ReservationFacade
{
	public String createReservation(final ReservationData reservationData);

	public void updateReservation(final ReservationData reservationData);
	
	public ReservationData getReservation(final String reservationPK);

	ListData getReservations(final String storeCode,final String cellphone, final String name, final Date fromDate,final Date toDate,final Integer startIndex, final Integer pageSize);

}
