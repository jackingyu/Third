package com.third.dao.customer;

import java.util.Date;
import java.util.List;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.ReservationModel;

public interface ReservationDao extends IGenericDAO<ReservationModel, String> {
	PaginationSupport findReservations(String store, String cellphone,
			String name, Date from, Date to, Integer startIndex,
			Integer pageSize);

	List<ReservationModel> findReservationsForCustomer(final String customerPK);

	Integer countReservationForCustomer(final String cellphone);
}
