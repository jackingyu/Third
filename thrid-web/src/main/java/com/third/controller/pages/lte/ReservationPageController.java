package com.third.controller.pages.lte;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.util.DataTableCriterias;
import com.third.facade.customer.CustomerFacade;
import com.third.facade.customer.ReservationFacade;
import com.third.facade.data.AddressData;
import com.third.facade.data.CityData;
import com.third.facade.data.ComboboxData;
import com.third.facade.data.CustomerData;
import com.third.facade.data.ListData;
import com.third.facade.data.RegionData;
import com.third.facade.data.ReservationData;
import com.third.facade.data.SourceData;
import com.third.facade.data.StoreData;
import com.third.facade.local.I18NFacade;
import com.third.model.CoreConstants;


@Controller
public class ReservationPageController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(ReservationPageController.class);
	private static final String RESERVATIONPK_PATH_VARIABLE_PATTERN = "/{reservationPK:.*}";

	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	@Resource(name = "reservationFacade")
	private ReservationFacade reservationFacade;

	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;

	@RequestMapping(value = "/reservation/reservationlistpage", method = RequestMethod.GET)
	public String getReservationListPage(final Model model, final HttpServletRequest request, final HttpServletResponse response)
	{
		fillAllStore2View(model);
		return ControllerConstants.LTE.RESERVATIONLISTPAGE;
	}


	@RequestMapping(value = "/reservation/createreservationpage", method = RequestMethod.GET)
	public String getCreateReservationPage(final Model model, final HttpServletRequest request, final HttpServletResponse response)
	{
		fillAllStore2View(model);
		return ControllerConstants.LTE.RESERVATIONDETAILSPAGE;
	}

	@RequestMapping(value = "/reservation/modifyreservationpage/" + RESERVATIONPK_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String getModifyReservationPage(@PathVariable("reservationPK") final String reservationPK, final Model model,
			final HttpServletRequest request, final HttpServletResponse response)
	{

		ReservationData reservation = reservationFacade.getReservation(reservationPK);
		model.addAttribute("reservation", reservation);
		fillStore2View(model, reservation.getStore().getCode());
		return ControllerConstants.LTE.RESERVATIONDETAILSPAGE;
	}


	@RequestMapping(value = "/reservation/reservationlist", method = RequestMethod.GET)
	@ResponseBody
	public Object searchReservations(
			@RequestParam(value = "cellphone", required = false) final String cellphone,
			@RequestParam(value = "storeCode", required = true) final String storeCode,
			@RequestParam(value = "startDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") final Date startDate,
			@RequestParam(value = "endDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") final Date endDate,
			@RequestParam(value = "name", required = false) final String name, final DataTableCriterias criterias)
	{
		ListData results = reservationFacade.getReservations(storeCode, cellphone, name, startDate, endDate,
				getStartIndexForDT(criterias), getPagesizeForDT(criterias));

		List<Object> reservations = results.getRows();

		List<String[]> listDatas = new ArrayList<String[]>();

		DTResults dtResult = initDTResults(results);

		for (int i = 0; i < reservations.size(); i++)
		{
			ReservationData r = (ReservationData) reservations.get(i);
			String[] row = new String[5];
			row[0] = r.getName();
			row[1] = r.getCellphone();
			row[2] = DateUtils.formatDate(r.getReservationDate(), "yyyy-MM-dd");
			row[3] = r.getStore().getName();
			row[4] = r.getPk();
			listDatas.add(row);
		}
		;

		dtResult.setData(listDatas);

		return dtResult;
	}

	@RequestMapping(value = "/reservation/save", method = RequestMethod.POST)
	@ResponseBody
	public void saveReservation(@RequestParam(value = "cellphone") final String cellphone,
			@RequestParam(value = "name") final String name, @RequestParam(value = "storeCode") final String storeCode,
			@RequestParam(value = "reservationPK", required = false) final String pk,
			@RequestParam(value = "reservationDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") final Date reservationDate,
			@RequestParam(value = "comment", required = false) final String comment)
	{
		ReservationData reservation = new ReservationData();
		reservation.setCellphone(cellphone);
		reservation.setName(name);
		reservation.setChannel(CoreConstants.ReservationChannel.Web);
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

		if (StringUtils.isNotEmpty(pk))
			reservationFacade.updateReservation(reservation);
		else
			reservationFacade.createReservation(reservation);
	}
}
