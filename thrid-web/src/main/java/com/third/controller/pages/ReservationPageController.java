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
import com.third.facade.data.ListData;
import com.third.facade.data.ReservationData;
import com.third.facade.data.StoreData;


@Controller
public class ReservationPageController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(ReservationPageController.class);
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
		
		reservation.setComment(comment);
		
		reservationFacade.updateReservation(reservation);
	}

//	@RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
//	@ResponseBody
//	public Object getCustomer(@RequestParam(value = "cellphone") final String cellphone)
//	{
//		CustomerData customer = customerFacade.getCustomerByCellphone(cellphone);
//		return customer;
//	}
//
//	@RequestMapping(value = "/getCustomerList")
//	@ResponseBody
//	public Object getCustomerList(@RequestParam(value = "cellphone", required = false) final String cellphone,
//			@RequestParam(value = "name", required = false) final String name,
//			@RequestParam(value = "page", required = false) final Integer page,
//			@RequestParam(value = "rows", required = false) final Integer rows)
//	{
//		ListData customers = customerFacade.getCustomers(cellphone, name, getStartIndex(page, rows), rows);
//
//		return customers;
//	}
//
//	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
//	@ResponseBody
//	public void createCustomer(@RequestParam(value = "cellphone") final String cellphone,
//			@RequestParam(value = "name") final String name, @RequestParam(value = "QQ", required = false) final String QQ,
//			@RequestParam(value = "email", required = false) final String email,
//			@RequestParam(value = "birthday", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date birthday,
//			@RequestParam(value = "weddingdate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date weddingdate,
//			@RequestParam(value = "source", required = false) final String source,
//			@RequestParam(value = "region", required = false) final String region,
//			@RequestParam(value = "city", required = false) final String city,
//			@RequestParam(value = "adr1", required = false) final String adr1,
//			@RequestParam(value = "comment", required = false) final String comment)
//	{
//
//		CustomerData customer = new CustomerData();
//		customer.setCellphone(cellphone);
//		customer.setName(name);
//		customer.setQQ(QQ);
//		customer.setBirthday(birthday);
//		customer.setEmail(email);
//		customer.setComment(comment);
//		customer.setWeddingdate(weddingdate);
//
//		SourceData sourceData = new SourceData();
//		sourceData.setPk(source);
//		customer.setSource(sourceData);
//
//		AddressData address = new AddressData();
//		address.setAdr1(adr1);
//		CityData cityData = new CityData();
//		cityData.setIsoCode(city);
//		address.setCity(cityData);
//
//		RegionData regionData = new RegionData();
//		regionData.setIsoCode(region);
//		address.setRegion(regionData);
//
//		customer.setAddress(address);
//		customerFacade.createCustomer(customer);
//	}
//
//	@RequestMapping(value = "/modifyCustomer", method = RequestMethod.POST)
//	@ResponseBody
//	public void modifyCustomer(@RequestParam(value = "cellphone") final String cellphone,
//			@RequestParam(value = "name") final String name, @RequestParam(value = "QQ", required = false) final String QQ,
//			@RequestParam(value = "email", required = false) final String email,
//			@RequestParam(value = "birthday", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date birthday,
//			@RequestParam(value = "weddingdate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date weddingdate,
//			@RequestParam(value = "source", required = false) final String source,
//			@RequestParam(value = "region", required = false) final String region,
//			@RequestParam(value = "city", required = false) final String city,
//			@RequestParam(value = "adr1", required = false) final String adr1,
//			@RequestParam(value = "comment", required = false) final String comment)
//	{
//
//		CustomerData customer = new CustomerData();
//		customer.setCellphone(cellphone);
//		customer.setName(name);
//		customer.setQQ(QQ);
//		customer.setBirthday(birthday);
//		customer.setEmail(email);
//		customer.setComment(comment);
//		customer.setWeddingdate(weddingdate);
//
//		SourceData sourceData = new SourceData();
//		sourceData.setPk(source);
//		customer.setSource(sourceData);
//
//		AddressData address = new AddressData();
//		address.setAdr1(adr1);
//		CityData cityData = new CityData();
//		cityData.setIsoCode(city);
//		address.setCity(cityData);
//
//		RegionData regionData = new RegionData();
//		regionData.setIsoCode(region);
//		address.setRegion(regionData);
//
//		customer.setAddress(address);
//		customerFacade.updateCustomer(customer);
//	}
}
