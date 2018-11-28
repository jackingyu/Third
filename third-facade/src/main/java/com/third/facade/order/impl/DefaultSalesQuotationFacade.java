package com.third.facade.order.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.third.core.constants.CoreConstants;
import com.third.dao.util.PaginationSupport;
import com.third.facade.data.DTResults;
import com.third.facade.data.SalesQuotationData;
import com.third.facade.order.SalesQuotationFacade;
import com.third.facade.populator.SalesQuotationDataPopulator;
import com.third.facade.utils.DTResultConvertor;
import com.third.facade.utils.TextMapperUtils;
import com.third.model.CustomerModel;
import com.third.model.OrderModel;
import com.third.model.PaymentModel;
import com.third.model.SalesQuotationModel;
import com.third.model.SourceModel;
import com.third.model.StoreModel;
import com.third.service.customer.CustomerService;
import com.third.service.customer.SourceService;
import com.third.service.order.OrderService;
import com.third.service.order.SalesQuotationService;
import com.third.service.user.UserService;
import org.springframework.stereotype.Component;

@Component
public class DefaultSalesQuotationFacade implements SalesQuotationFacade {

	private SalesQuotationService salesQuotationService;
	private SourceService sourceService;
	private CustomerService customerService;
	private OrderService orderService;
	private UserService userService;
	private SalesQuotationDataPopulator salesQuotationDataPopulator;

	@Override
	public String saveSalesQuotation(SalesQuotationData salesQuotationData)
	{
		SalesQuotationModel salesQuotation = null;
		if (StringUtils.isNotEmpty(salesQuotationData.getPk()))
			salesQuotation = salesQuotationService
					.getSalesQuotation(salesQuotationData.getPk());
		else
		{
			salesQuotation = new SalesQuotationModel();
			salesQuotation.setCreateTime(new Date());
			salesQuotation.setCreateDate(new Date());
		}

		salesQuotation.setCellphone(salesQuotationData.getCellphone());
		salesQuotation.setCustomerName(salesQuotationData.getCustomerName());
		salesQuotation.setComment(salesQuotationData.getComment());
		salesQuotation.setPaidamount(BigDecimal
				.valueOf(Double.valueOf(salesQuotationData.getPaidamount())));
		salesQuotation.setTotalamount(BigDecimal
		        .valueOf(Double.valueOf(salesQuotationData.getTotalamount())));
		salesQuotation.setPaymentMethod(salesQuotationData.getPaymentMethod());
        salesQuotation.setCoSalesperson(salesQuotationData.getCoSalesperson());
        salesQuotation.setDeliveryDate(salesQuotationData.getDeliveryDate());
        salesQuotation.setWeddingDate(salesQuotationData.getWeddingDate());
        salesQuotation.setTryDate(salesQuotationData.getTryDate());
        salesQuotation.setPhotoDate(salesQuotationData.getPhotoDate());
        //database level implement this
        //salesQuotation.setCreateDate(Date.from(Instant.now()));
        if(salesQuotationData.getSource()!=null)
        {
          SourceModel sourceModel = sourceService.getSource(salesQuotationData.getSource().getPk());
          salesQuotation.setSource(sourceModel);
        }
        
        salesQuotation.setCreatedBy(userService.getCurrentUser());
        
		salesQuotationService.saveSalesQuotation(salesQuotation);
		
		
		return salesQuotation.getPk();
	}

	@Override
	public DTResults getSalesQuotation(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String> sp)
	{
		PaginationSupport ps = this.salesQuotationService
				.getSalesQuotations(startDate, endDate, startIndex, pageSize, sp);
		DTResults result = DTResultConvertor.convertPS2DT(ps);
		List<Object[]> arrays = result.getData();

		// translate paymentmethod to paymentmoethodtext
		arrays.forEach(a -> {
			a[4] = TextMapperUtils.getPaymentMethodText(a[4].toString());
		});

		return result;
	}

	public void setSalesQuotationService(SalesQuotationService salesQuotationService)
	{
		this.salesQuotationService = salesQuotationService;
	}

	
	public void setSalesQuotationDataPopulator(
			SalesQuotationDataPopulator salesQuotationDataPopulator)
	{
		this.salesQuotationDataPopulator = salesQuotationDataPopulator;
	}

	@Override
	public SalesQuotationData getSalesQuotation(String pk)
	{
		SalesQuotationModel sq = salesQuotationService.getSalesQuotation(pk);
		SalesQuotationData sqData = new SalesQuotationData();
		salesQuotationDataPopulator.populate(sq, sqData);
		return sqData;
	}

	public void setSourceService(SourceService sourceService)
	{
		this.sourceService = sourceService;
	}

	public void setCustomerService(CustomerService customerService)
	{
		this.customerService = customerService;
	}

	public void setOrderService(OrderService orderService)
	{
		this.orderService = orderService;
	}
	

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	@Override
	public String convert2SalesOrder(String pk)
	{
		SalesQuotationModel sq = salesQuotationService.getSalesQuotation(pk);
		OrderModel so  = new OrderModel();
		
		
		CustomerModel customer = customerService.getCustomerByCellphone(sq.getCellphone());
		
		if(customer==null)
		{
			customer = new CustomerModel();
			customer.setCellphone(sq.getCellphone());
			customer.setSource(sq.getSource());
			customer.setName(sq.getCustomerName());
			customer.setWeddingDate(sq.getWeddingDate());
			customerService.createCustomer(customer);
		}
		
		so.setCode(Long.toString(System.currentTimeMillis()));
		so.setCoSalesperson(sq.getCoSalesperson());
		so.setCustomerName(sq.getCustomerName());
		so.setDeliveryDate(sq.getDeliveryDate());
		so.setTryDate(sq.getTryDate());
		so.setPhotoDate(sq.getPhotoDate());
		so.setStatus(CoreConstants.OrderStatus.NEW);
		StoreModel store = (StoreModel) userService.getCurrentUser().getStores().iterator().next();
		so.setStore(store);
		so.setCustomer(customer);
		so.setReceiveable(sq.getTotalamount());
		so.setPaidamount(sq.getPaidamount());
		so.setOpenamount(sq.getTotalamount().subtract(sq.getPaidamount()));
		so.setSource(sq.getSource());
		so.setOrderDate(sq.getCreateDate());
		PaymentModel pm = new PaymentModel();
		pm.setAmount(sq.getPaidamount());
		pm.setPaymentMethod(sq.getPaymentMethod());
		pm.setPaidTime(sq.getCreateTime());
		pm.setPaymentEntryNo(0);
		pm.setPaymentType(CoreConstants.PaymentType.DownPayment);
		pm.setStore(so.getStore());
		so.setPayments(Arrays.asList(pm));
		so.setOrderDate(new Date());
		so.setSalesperson(userService.getCurrentUser());
	
		orderService.createOrder(so);
		
		sq.setOrderCode(so.getCode());
		salesQuotationService.saveSalesQuotation(sq);
		
		return so.getCode();
	}

}
