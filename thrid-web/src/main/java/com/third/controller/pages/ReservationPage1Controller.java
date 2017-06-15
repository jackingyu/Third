package com.third.controller.pages;


import java.time.LocalDate;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.facade.customer.ReservationFacade;
import com.third.facade.data.CustomerData;
import com.third.facade.data.ListData;
import com.third.facade.data.ReservationData;
import com.third.facade.data.StoreData;


@Controller
public class ReservationPage1Controller extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(ReservationPage1Controller.class);
	@Resource(name = "reservationFacade")
	private ReservationFacade reservationFacade;


	@RequestMapping(value = "/getReservationListPage", method = RequestMethod.GET)
	public String getReservationListPage(Model model)
	{
		LocalDate now = LocalDate.now();
	   LocalDate defaultStartDate = now.minusDays(1);
	   LocalDate defaultEndDate = now.plusDays(1);
	   model.addAttribute("defaultStartDate", defaultStartDate);
	   model.addAttribute("defaultEndDate", defaultEndDate);
	   
		return ControllerConstants.Fragements.RESERVATIONLIST;
	}
	
	@RequestMapping(value = "/getReservations")
	@ResponseBody
	public Object getReservationList(@RequestParam(value = "cellphone", required = false) final String cellphone,
			@RequestParam(value = "name", required = false) final String name,
			@RequestParam(value = "store", required = false) final String storeCode,
			@RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date fromDate,
			@RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date toDate,
			@RequestParam(value = "page", required = false) final Integer page,
			@RequestParam(value = "rows", required = false) final Integer rows)
	{
		ListData reservations = reservationFacade.getReservations(storeCode,cellphone, name,fromDate,toDate, getStartIndex(page, rows), rows);

		return reservations;
	}
	
	@RequestMapping(value = "/getReservation")
	@ResponseBody
	public Object getReservation(@RequestParam(value = "reservationPK") final String reservationPK)
	{
      ReservationData reservation = reservationFacade.getReservation(reservationPK);
      
		return reservation;
	}
	
	@RequestMapping(value = "/createReservation", method = RequestMethod.POST)
	@ResponseBody
	public String createReservation(@RequestParam(value = "cellphone") final String cellphone,
			@RequestParam(value = "name") final String name,  
			@RequestParam(value = "store") final String storeCode,
			@RequestParam(value = "channel") final String channel,
			@RequestParam(value = "reservationDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date reservationDate,
			@RequestParam(value = "comment", required = false) final String comment)
	{
		ReservationData reservation = new ReservationData();
		reservation.setCellphone(cellphone);
		reservation.setName(name);
		reservation.setChannel(channel);
		reservation.setReservationDate(reservationDate);
		StoreData store = new StoreData();
		store.setCode(storeCode);
		reservation.setStore(store);
		reservation.setComment(comment);
		
		//TODO:系统暂时根据填写的手机号选择用户,需要考虑如果一个现有用户要留一个不同的手机号做预约如何处理呢
		//后台部分因为界面上是根据手机号检索用户,所以做一个强制关联
		CustomerData customer = new CustomerData();
		customer.setCellphone(cellphone);
		
		final String reservationPK = reservationFacade.createReservation(reservation);
		
		return reservationPK;
	}
	
	@RequestMapping(value = "/modifyReservation", method = RequestMethod.POST)
	@ResponseBody
	public void modifyReservation(@RequestParam(value = "cellphone") final String cellphone,
			@RequestParam(value = "name") final String name,  
			@RequestParam(value = "store") final String storeCode,
			@RequestParam(value = "channel") final String channel,
			@RequestParam(value = "reservationPK") final String pk,
			@RequestParam(value = "reservationDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date reservationDate,
			@RequestParam(value = "comment", required = false) final String comment)
	{
		ReservationData reservation = new ReservationData();
		reservation.setCellphone(cellphone);
		reservation.setName(name);
		reservation.setChannel(channel);
		reservation.setReservationDate(reservationDate);
		StoreData store = new StoreData();
		store.setCode(storeCode);
		reservation.setPk(pk);
		reservation.setStore(store);
		//TODO:系统暂时根据填写的手机号选择用户,需要考虑如果一个现有用户要留一个不同的手机号做预约如何处理呢
		//后台部分因为界面上是根据手机号检索用户,所以做一个强制关联
		CustomerData customer = new CustomerData();
		customer.setCellphone(cellphone);
		
		reservation.setCustomer(customer);
		reservation.setComment(comment);
		
		reservationFacade.updateReservation(reservation);
	}
}
