package com.third.service.customer.impl;

import java.util.Date;
import java.util.List;

import com.third.dao.customer.ReservationDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.ReservationModel;
import com.third.service.customer.ReservationService;
import org.springframework.stereotype.Service;

@Service
public class DefaultReservationService implements ReservationService {
	private ReservationDao reservationDao;

	@Override
	public void createrReservation(ReservationModel reservation)
	{
		reservationDao.save(reservation);
	}

	@Override
	public PaginationSupport getReservationList(final String storeCode,
			final String cellphone, final String name, final Date from,
			final Date to, Integer startIndex, Integer pageSize)
	{
		return reservationDao.findReservations(storeCode, cellphone, name, from,
				to, startIndex, pageSize);
	}

	@Override
	public void updateReservation(ReservationModel reservation)
	{
		reservationDao.update(reservation);
	}

	@Override
	public ReservationModel getReservation(final String reservationPK)
	{
		return reservationDao.get(reservationPK);
	}

	public void setReservationDao(ReservationDao reservationDao)
	{
		this.reservationDao = reservationDao;
	}

	@Override
	public List<ReservationModel> getReservationsForCustomer(String customerPK)
	{
		return reservationDao.findReservationsForCustomer(customerPK);

	}

}
