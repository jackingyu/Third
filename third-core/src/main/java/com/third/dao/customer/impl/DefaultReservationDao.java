package com.third.dao.customer.impl;

import java.util.Date;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.third.dao.customer.ReservationDao;
import com.third.dao.generic.GenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.ReservationModel;


public class DefaultReservationDao extends GenericDAO<ReservationModel, String> implements ReservationDao
{
	private String FIND_RESERVATION = "from com.third.model.Reservation r where r.reservationDate >= ? and r.reservationDate <= ?";;

	@Override
	public PaginationSupport findReservations(final String storeCode,final String cellphone, final String name, final Date fromDate, final Date toDate,
			Integer startIndex, Integer pageSize)
	{
		DetachedCriteria dc = DetachedCriteria.forClass(ReservationModel.class);

		if (!StringUtils.isEmpty(cellphone))
			dc.add(Restrictions.like("cellphone", generateLikeParameter(cellphone)));

		if (!StringUtils.isEmpty(name))
			dc.add(Restrictions.like("name", generateLikeParameter(name)));
		
		dc.add(Restrictions.between("reservationDate", fromDate, toDate));
		dc.createCriteria("store").add(Restrictions.eq("id", storeCode));

		PaginationSupport ps = findPageByCriteria(dc, pageSize, startIndex);

		return ps;
	}


}
