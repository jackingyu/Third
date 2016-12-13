package com.third.controller.weixin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.third.controller.pages.ControllerConstants;
import com.third.facade.customer.CustomerFacade;
import com.third.facade.customer.ReservationFacade;
import com.third.facade.data.CustomerData;
import com.third.facade.data.ReservationData;
import com.third.facade.data.StoreData;
import com.third.facade.store.StoreFacade;
import com.third.model.CoreConstants;


@Controller
@RequestMapping("/wx")
public class WeixinReservationController extends AbstractWeixinController
{
	private static final Logger LOG = Logger.getLogger(WeixinReservationController.class);
	
	@Resource(name="storeFacade")
	private StoreFacade storeFacade;
	
	@Resource(name="reservationFacade")
	private ReservationFacade reservationFacade;
	
	@Resource(name="customerFacade")
	private CustomerFacade customerFacade;
	
	@RequestMapping(value = "/getReservationPage")
	public String getReservationPage(@RequestParam(value="reservationPK",required=false) final String reservationPK,final Model model)
	{
	   fillInStoreList(model);
	   
	   if(StringUtils.isEmpty(reservationPK))
		 return ControllerConstants.WeiXin.RESERVATIONPAGE;
	   
	   ReservationData reservation = reservationFacade.getReservation(reservationPK);
	   
	   model.addAttribute("reservation",reservation);
	   
	   return ControllerConstants.WeiXin.RESERVATIONMODIFYPAGE;
	}
	
	
	@RequestMapping(value = "/createReservation")
	public String createReservation(@RequestParam(value ="name",required=true)final String name,
			@RequestParam(value ="phone",required=true)final String cellphone,
			@RequestParam(value ="date",required=true) @DateTimeFormat(pattern = "yyyy-MM-dd")final Date reservationDate,
			@RequestParam(value ="store_select",required=true)final String storeCode,
			final Model model)
	{
		ReservationData reservationData = new ReservationData();
		reservationData.setCellphone(cellphone);
		reservationData.setName(name);
		reservationData.setReservationDate(reservationDate);
		reservationData.setChannel(CoreConstants.ReservationChannel.Weixin);
		StoreData store = new StoreData();
		store.setCode(storeCode);
		reservationData.setStore(store);
		CustomerData customer = customerFacade.getCurrentCustomer();
		reservationData.setCustomer(customer);
		
		reservationFacade.createReservation(reservationData);
		
		return "redirect:/wx/getReservationList";
	}
	
	@RequestMapping(value = "/modifyReservation")
	public String modifyReservation(@RequestParam(value ="name",required=true)final String name,
			@RequestParam(value ="phone",required=true)final String cellphone,
			@RequestParam(value ="date",required=true) @DateTimeFormat(pattern = "yyyy-MM-dd")final Date reservationDate,
			@RequestParam(value ="store_select",required=true)final String storeCode,
			@RequestParam(value ="reservationPK",required=true)final String reservationPK,
			final Model model)
	{
		ReservationData reservationData = new ReservationData();
		reservationData.setPk(reservationPK);
		reservationData.setCellphone(cellphone);
		reservationData.setName(name);
		reservationData.setReservationDate(reservationDate);
		reservationData.setChannel(CoreConstants.ReservationChannel.Weixin);
		StoreData store = new StoreData();
		store.setCode(storeCode);
		reservationData.setStore(store);
		CustomerData customer = customerFacade.getCurrentCustomer();
		reservationData.setCustomer(customer);
		
		reservationFacade.updateReservation(reservationData);
		
		return "redirect:/wx/getReservationList";
	}
	
	@RequestMapping(value = "/getReservationList")
	public String getReservationList(final Model model)
	{
		CustomerData customer = customerFacade.getCurrentCustomer();
		List<ReservationData> reservations = reservationFacade.getReservationsForCustomer(customer.getCellphone());
	   model.addAttribute("reservations",reservations);
	   
		return ControllerConstants.WeiXin.RESERVATIONLISTPAGE;
	}
	
	@RequestMapping(value = "/getReservationDetail")
	public String getReservationDetail(@RequestParam(value="reservationPK") final String reservationPK,final Model model)
	{
		ReservationData reservation = reservationFacade.getReservation(reservationPK);
		model.addAttribute("reservation",reservation);
		return ControllerConstants.WeiXin.RESERVATIONDETAILPAGE;
	}

}
