package com.third.facade.order.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.third.core.constants.CoreConstants;
import com.third.dao.util.PaginationSupport;
import com.third.facade.data.DTResults;
import com.third.facade.data.ListData;
import com.third.facade.data.OrderData;
import com.third.facade.data.OrderEntryData;
import com.third.facade.data.PaymentData;
import com.third.facade.data.SizeAttributeData;
import com.third.facade.data.SizeAttributeGroupData;
import com.third.facade.data.TextMapper;
import com.third.facade.order.OrderFacade;
import com.third.facade.populator.ConfigurablePopulator;
import com.third.facade.populator.OrderDataPopulator;
import com.third.facade.populator.OrderEntryDataPopulator;
import com.third.facade.populator.SizeAttributeDataPopulator;
import com.third.facade.populator.option.OrderOption;
import com.third.facade.utils.DTResultConvertor;
import com.third.facade.utils.TextMapperUtils;
import com.third.model.CustomerModel;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;
import com.third.model.PaymentModel;
import com.third.model.ProductModel;
import com.third.model.SizeAttributeModel;
import com.third.model.StoreModel;
import com.third.model.UserModel;
import com.third.service.customer.CustomerService;
import com.third.service.customer.SourceService;
import com.third.service.media.MediaService;
import com.third.service.order.OrderService;
import com.third.service.order.PaymentService;
import com.third.service.order.impl.NewOrderAction;
import com.third.service.product.ProductService;
import com.third.service.product.SizeAttributeService;
import com.third.service.store.StoreService;
import com.third.service.user.UserService;

public class DefaultOrderFacade implements OrderFacade {
	private OrderService orderService;
	private CustomerService customerService;
	private PaymentService paymentService;
	private OrderDataPopulator orderDataPopulator;
	private ConfigurablePopulator<OrderModel, OrderData, OrderOption> orderConfiguredPopulator;
	private OrderEntryDataPopulator orderEntryDataPopulator;
	private SizeAttributeDataPopulator sizeAttributeDataPopulator;
	private UserService userService;
	private StoreService storeService;
	private SourceService sourceService;
	private ProductService productService;
	private SizeAttributeService sizeAttributeService;
	private MediaService mediaService;
	private NewOrderAction newOrderAction;
	private static final Logger LOG = Logger
			.getLogger(DefaultOrderFacade.class);

	@Override
	public void createOrder(OrderData orderData)
	{
		OrderModel order = new OrderModel();

		// TODO 需要考虑订单上店铺的拉取策略,可以考虑改完前台手工设置
		StoreModel store = storeService
				.getStoreForCode(orderData.getStore().getCode());

		order.setCode(orderData.getOrderCode());
		order.setCellphone(orderData.getCellphone());
		order.setComment(orderData.getComment());
		order.setCoSalesperson(orderData.getCoSalesperson());
		CustomerModel customer = customerService
				.getCustomerByCellphone(orderData.getCellphone());
		order.setCustomer(customer);
		order.setCustomerName(orderData.getCustomerName());

		order.setDeliveryDate(orderData.getDeliveryDate());
		order.setOrderDate(Date.from(Instant.now()));
		order.setPhotoDate(orderData.getPhotoDate());
		order.setSalesperson(userService.getCurrentUser());
		order.setSource(customer.getSource());
		order.setTryDate(orderData.getTryDate());
		order.setWeddingDate(orderData.getWeddingDate());
		order.setReceiveable(
				BigDecimal.valueOf(Double.valueOf(orderData.getReceiveable())));
		order.setOpenamount(order.getReceiveable());
		order.setPaidamount(new BigDecimal(0));
		// init order status
		order.setStatus(0);

		// TODO:need to update
		// order.setStore(store.get());
		order.setStore(store);

		if (!CollectionUtils.isEmpty(orderData.getPayments()))
		{
			List<PaymentModel> paymentModels = new ArrayList<PaymentModel>();
			orderData.getPayments().forEach(p -> {
				PaymentModel payment = new PaymentModel();
				payment.setAmount(p.getAmount());
				payment.setCreatedBy(userService.getCurrentUser());
				payment.setPaidTime(new Date());
				payment.setPaymentMethod(p.getPaymentMethod());
				payment.setPaymentType(p.getPaymentType());
				// TODO:
				payment.setStore(store);
				paymentModels.add(payment);
			});
			order.setPayments(paymentModels);
		}
		orderService.createOrder(order);

		orderData.setPk(order.getPk());

		newOrderAction.perform(order);
	}

	@Override
	public DTResults getOrders(Date startDate, Date endDate, Integer startIndex,
			Integer pageSize, Map<String, String> sp)
	{
		PaginationSupport ps = orderService.getOrders(startDate, endDate,
				startIndex, pageSize, sp);
		List<Object[]> rows = ps.getItems();
		for(Object[] row:rows)
		{
		    row[5] = TextMapperUtils.getOrderStatusText(Integer.valueOf(row[5].toString()));
		}
		
		return DTResultConvertor.convertPS2DT(ps);
	}

	@Override
	public OrderData getOrder(String orderCode)
	{
		OrderModel order = orderService.getOrderForCode(orderCode);
		OrderData orderData = new OrderData();
		orderDataPopulator.populate(order, orderData);
		return orderData;
	}

	@Override
	public OrderData getOrderForOptions(String orderCode,
			Collection<OrderOption> orderOptions)
	{
		OrderModel order = orderService.getOrderForCode(orderCode);
		OrderData orderData = new OrderData();
		orderConfiguredPopulator.populate(order, orderData, orderOptions);
		return orderData;
	}

	@Override
	public void updateOrder(OrderData orderData)
	{
		if (StringUtils.isBlank(orderData.getPk()))
			return;

		OrderModel order = orderService
				.getOrderForCode(orderData.getOrderCode());

		order.setCellphone(orderData.getCellphone());
		order.setComment(orderData.getComment());
		order.setCoSalesperson(orderData.getCoSalesperson());
		CustomerModel customer = customerService
				.getCustomerByCellphone(orderData.getCellphone());
		order.setCustomer(customer);
		order.setCustomerName(orderData.getCustomerName());
		order.setContactinfo(orderData.getContactinfo());

		order.setDeliveryDate(orderData.getDeliveryDate());
		order.setOrderDate(orderData.getOrderDate());
		order.setPhotoDate(orderData.getPhotoDate());

		if (orderData.getSource() != null)
			order.setSource(
					sourceService.getSource(orderData.getSource().getPk()));

		order.setTryDate(orderData.getTryDate());
		order.setWeddingDate(orderData.getWeddingDate());

		if (!CollectionUtils.isEmpty(orderData.getPayments()))
		{
			List<PaymentModel> paymentModels = new ArrayList<PaymentModel>();
			List<PaymentModel> oldPaymentModels = order.getPayments();
			orderData.getPayments().forEach(p -> {
				if (StringUtils.isBlank(p.getPk()))
				{
					PaymentModel payment = new PaymentModel();
					payment.setAmount(p.getAmount());
					payment.setCreatedBy(userService.getCurrentUser());
					payment.setPaymentMethod(p.getPaymentMethod());
					payment.setPaymentType(p.getPaymentType());
					payment.setStore(order.getStore());
					payment.setPaidTime(new Date());
					paymentModels.add(payment);
				} else
				{
					Optional<PaymentModel> payment = oldPaymentModels.stream()
							.filter(p1 -> p1.getPk().equals(p.getPk()))
							.findFirst();
					payment.get().setAmount(p.getAmount());
					payment.get().setPaymentType(p.getPaymentType());
					payment.get().setPaymentMethod(p.getPaymentMethod());
					paymentModels.add(payment.get());
				}
			});
			order.setPayments(paymentModels);

			BigDecimal paidamount = new BigDecimal(0);
			for (int i = 0; i < paymentModels.size(); i++)
			{
				paidamount = paidamount.add(paymentModels.get(i).getAmount());
			}

			order.setPaidamount(paidamount);
			order.setOpenamount(order.getReceiveable().subtract(paidamount));
		}

		orderService.upateOrder(order);
	}

	@Override
	public void createOrderEntry(final OrderEntryData orderEntryData)
	{
		OrderEntryModel orderEntry = new OrderEntryModel();
		orderEntry.setComment(orderEntryData.getComment());
		// orderEntry.setDeliveryDate(orderEntryData.getDeliveryDate());
		UserModel designer = userService
				.getUserById(orderEntryData.getDesigner().getUserId());
		orderEntry.setDesigner(designer);
		orderEntry.setItemCategory(orderEntryData.getItemCategory());
		orderEntry.setProductTitle(orderEntryData.getProductTitle());
		orderEntry.setQuantity(orderEntryData.getQuantity());
		orderEntry.setSizeDate(orderEntryData.getSizeDate());
		orderEntry.setSizeDetails(orderEntryData.getSizeDetails());
		orderEntry.setTryDate(orderEntryData.getTryDate());
		orderEntry.setStyle(orderEntryData.getStyle());
		orderEntry.setCustomerName(orderEntryData.getCustomerName());
		orderEntry.setExternalId(orderEntryData.getExternalId());
		orderEntry.setStatus(0);

		OrderModel order = orderService
				.getOrderForCode(orderEntryData.getOrderCode());
		orderEntry.setOrder(order);
		orderEntry.setCreatedBy(userService.getCurrentUser());
		orderEntry.setDeliveryDate(order.getDeliveryDate());

		StoreModel store = null;
		if (orderEntryData.getStore() != null)
			store = storeService
					.getStoreForCode(orderEntryData.getStore().getCode());
		else
			store = order.getStore();
		orderEntry.setStore(store);

		if (StringUtils.isNotEmpty(orderEntryData.getProduct().getCode()))
		{
			ProductModel product = productService
					.getProductForCode(orderEntryData.getProduct().getCode());
			orderEntry.setProduct(product);
		}

		orderService.createOrderEntry(orderEntry);
		orderEntryData.setPk(orderEntry.getPk());
	}

	@Override
	public void updateOrderEntry(final OrderEntryData orderEntryData)
	{
		OrderEntryModel orderEntry = orderService
				.getOrderEntry(orderEntryData.getPk());
		orderEntry.setComment(orderEntryData.getComment());
		// orderEntry.setDeliveryDate(orderEntryData.getDeliveryDate());
		UserModel designer = userService
				.getUserById(orderEntryData.getDesigner().getUserId());
		orderEntry.setDesigner(designer);
		// orderEntry.setItemCategory(orderEntryData.getItemCategory());
		orderEntry.setProductTitle(orderEntryData.getProductTitle());
		// orderEntry.setQuantity(orderEntryData.getQuantity());
		orderEntry.setSizeDate(orderEntryData.getSizeDate());
		orderEntry.setSizeDetails(orderEntryData.getSizeDetails());
		orderEntry.setTryDate(orderEntryData.getTryDate());
		orderEntry.setStyle(orderEntryData.getStyle());
		orderEntry.setCustomerName(orderEntryData.getCustomerName());
		orderEntry.setExternalId(orderEntryData.getExternalId());
        orderEntry.setProductTitle(orderEntryData.getProductTitle());
        
		if (StringUtils.isNotEmpty(orderEntryData.getProduct().getCode()))
		{
			ProductModel product = productService
					.getProductForCode(orderEntryData.getProduct().getCode());
			orderEntry.setProduct(product);
		}

		orderService.updateOrderEntry(orderEntry);
	}
	
	@Override
	public void updateOrderEntryActualTryDate(final OrderEntryData orderEntryData)
	{
	    OrderEntryModel entryModel = null;
	   
	    if (StringUtils.isNotEmpty(orderEntryData.getPk()))
            entryModel = orderService.getOrderEntry(orderEntryData.getPk());
        else if (StringUtils.isNotEmpty(orderEntryData.getExternalId()))
            entryModel = orderService.getOrderEntryForExternalId(orderEntryData.getExternalId());
	    
	    if(null==entryModel)
	        return;
	    
	    entryModel.setActualTryDate(orderEntryData.getActualTryDate());
	   
	    orderService.updateOrderEntry(entryModel);
	}

	@Override
	public void removeOrderEntry(String orderEntryPK)
	{
		orderService.removeOrderEntry(orderEntryPK);
	}

	@Override
	public Map<String, SizeAttributeGroupData> getSizeAttributes(
			final Integer itemCategory)
	{
		List<SizeAttributeModel> sizeAttributes = sizeAttributeService
				.getSizeAttributeForItemCategory(itemCategory);

		Map<String, SizeAttributeGroupData> groups = new HashMap<String, SizeAttributeGroupData>();

		Map<Integer, List<SizeAttributeModel>> sizeAttributeModels = sizeAttributes
				.stream()
				.collect(Collectors.groupingBy(SizeAttributeModel::getGroup));

		sizeAttributeModels.forEach((k, v) -> {
			SizeAttributeGroupData groupData = new SizeAttributeGroupData();
			groupData.setGroupId(k.toString());
			groupData.setGroupText(
					TextMapper.SizeAttributeGroup.get(groupData.getGroupId()));

			List<SizeAttributeModel> sizeAttributes1 = v;
			List<SizeAttributeData> sizeAttributeDatas = new ArrayList<SizeAttributeData>();

			sizeAttributes1.forEach(s -> {
				SizeAttributeData sizeAttributeData = new SizeAttributeData();
				sizeAttributeDataPopulator.populate(s, sizeAttributeData);
				sizeAttributeDatas.add(sizeAttributeData);
			});

			groupData.setAttributes(sizeAttributeDatas);
			groups.put(groupData.getGroupId(), groupData);
		});

		return groups;
	}

	@Override
	public OrderEntryData getSizeDatas(String orderEntryPK)
	{
		OrderEntryModel entryModel = orderService.getOrderEntry(orderEntryPK);
		OrderEntryData entry = new OrderEntryData();
		entry.setItemCategory(entryModel.getItemCategory());

		String sizeDetails = entryModel.getSizeDetails();
		List<SizeAttributeData> sizeDatas = JSON.parseArray(sizeDetails,
				SizeAttributeData.class);

		Map<String, SizeAttributeGroupData> sizeDataGroups = new HashMap<String, SizeAttributeGroupData>();

		if (!CollectionUtils.isEmpty(sizeDatas))
		{
			Map<String, List<SizeAttributeData>> sizeDataMap = sizeDatas
					.stream().collect(
							Collectors.groupingBy(SizeAttributeData::getGroup));

			sizeDataMap.forEach((k, v) -> {
				SizeAttributeGroupData groupData = new SizeAttributeGroupData();
				groupData.setGroupId(k.toString());
				groupData.setGroupText(TextMapper.SizeAttributeGroup
						.get(groupData.getGroupId()));
				groupData.setAttributes(v);

				sizeDataGroups.put(groupData.getGroupId(), groupData);
			});
		}
		entry.setSizeDatas(sizeDataGroups);

		return entry;
	}

	public String uploadMediaForOrderEntry(final String entryPK,
			final MultipartFile media)
	{
		final String mediaId = UUID.randomUUID().toString();
		final String mediaUrl = mediaService.createMedia(mediaId,
				CoreConstants.MediaFolder.SizeOrderFolder, media);
		OrderEntryModel orderEntry = orderService.getOrderEntry(entryPK);
		orderEntry.setSizeImage(mediaUrl);
		orderService.updateOrderEntry(orderEntry);

		return mediaUrl;
	}

	public String getMediaForOrderEntry(final String entryPK)
	{
		OrderEntryModel entry = orderService.getOrderEntry(entryPK);
		return entry.getSizeImage();
	}

	@Override
	public List<OrderData> getOrdersForCustomer(String cellphone)
	{
		CustomerModel customer = customerService
				.getCustomerByCellphone(cellphone);
		List<OrderModel> orders = orderService
				.getOrdersForCustomer(customer.getPk());
		if (CollectionUtils.isEmpty(orders))
			return Collections.EMPTY_LIST;

		List<OrderData> orderDatas = new ArrayList<OrderData>();
		List<OrderOption> orderOptions = Arrays.asList(OrderOption.BASIC);
		orders.forEach(o -> {
			OrderData od = new OrderData();
			orderConfiguredPopulator.populate(o, od, orderOptions);
			orderDatas.add(od);
		});

		return orderDatas;
	}

	@Override
	public String[] createPayment(PaymentData payment)
	{
		OrderModel orderModel = orderService
				.getOrderForCode(payment.getOrderCode());
		Integer paymentEntryNo = orderModel.getPayments().size();

		PaymentModel paymentModel = new PaymentModel();
		paymentModel.setAmount(payment.getAmount());
		paymentModel.setPaidTime(payment.getPaidTime());
		paymentModel.setCreateTime(new Date());
		paymentModel.setPaymentEntryNo(paymentEntryNo);
		paymentModel.setPaymentMethod(payment.getPaymentMethod());
		paymentModel.setPaymentType(payment.getPaymentType());
		paymentModel.setOrder(orderModel);
		paymentModel.setStore(orderModel.getStore());
		paymentService.createPayment(paymentModel);

		orderModel.setPaidamount(
				orderModel.getPaidamount().add(payment.getAmount()));
		orderModel.setOpenamount(orderModel.getReceiveable()
				.subtract(orderModel.getPaidamount()));
		orderService.upateOrder(orderModel);

		String[] orderamount = { orderModel.getPaidamount().toString(),
				orderModel.getOpenamount().toString() };

		return orderamount;
	}

	@Override
	public String[] removePayment(final String paymentPK)
	{
		PaymentModel paymentModel = paymentService.getPayment(paymentPK);
		OrderModel orderModel = paymentModel.getOrder();
		paymentService.removePayment(paymentPK);

		orderModel.setPaidamount(
				orderModel.getPaidamount().subtract(paymentModel.getAmount()));
		orderModel.setOpenamount(orderModel.getReceiveable()
				.subtract(orderModel.getPaidamount()));
		orderService.upateOrder(orderModel);

		String[] orderamount = { orderModel.getPaidamount().toString(),
				orderModel.getOpenamount().toString() };

		return orderamount;
	}

	public void setOrderService(OrderService orderService)
	{
		this.orderService = orderService;
	}

	public void setCustomerService(CustomerService customerService)
	{
		this.customerService = customerService;
	}

	public void setOrderDataPopulator(OrderDataPopulator orderDataPopulator)
	{
		this.orderDataPopulator = orderDataPopulator;
	}

	public void setSizeAttributeDataPopulator(
			SizeAttributeDataPopulator sizeAttributeDataPopulator)
	{
		this.sizeAttributeDataPopulator = sizeAttributeDataPopulator;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public void setStoreService(StoreService storeService)
	{
		this.storeService = storeService;
	}

	public void setSourceService(SourceService sourceService)
	{
		this.sourceService = sourceService;
	}

	public void setSizeAttributeService(
			SizeAttributeService sizeAttributeService)
	{
		this.sizeAttributeService = sizeAttributeService;
	}

	public void setOrderEntryDataPopulator(
			OrderEntryDataPopulator orderEntryDataPopulator)
	{
		this.orderEntryDataPopulator = orderEntryDataPopulator;
	}

	public void setMediaService(MediaService mediaService)
	{
		this.mediaService = mediaService;
	}

	public void setOrderConfiguredPopulator(
			ConfigurablePopulator<OrderModel, OrderData, OrderOption> orderConfiguredPopulator)
	{
		this.orderConfiguredPopulator = orderConfiguredPopulator;
	}

	public void setPaymentService(PaymentService paymentService)
	{
		this.paymentService = paymentService;
	}

	@Override
	public OrderEntryData getOrderEntry(String orderEntryPK)
	{
		OrderEntryModel orderEntryModel = orderService
				.getOrderEntry(orderEntryPK);
		OrderEntryData orderEntry = new OrderEntryData();
		orderEntryDataPopulator.populate(orderEntryModel, orderEntry);
		return orderEntry;
	}

	public void setProductService(ProductService productService)
	{
		this.productService = productService;
	}

	/*
	 * 严格校验每一行的状态,如果量身单和订单有一行不为目标状态,则不返回任何结果
	 * 
	 * @see
	 * com.third.facade.order.OrderFacade#getOrderEntriesByPKorId(java.lang.
	 * String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public DTResults getOrderEntriesByPKorId(String entryPK, String externalId,
			Integer status)
	{
		OrderEntryModel entryModel;
		// pk has first priority
		if (StringUtils.isNotEmpty(entryPK))
			entryModel = orderService.getOrderEntry(entryPK);
		else if (StringUtils.isNotEmpty(externalId))
			entryModel = orderService.getOrderEntryForExternalId(externalId);
		else
			return new DTResults();

		if (entryModel == null)
			return new DTResults();

		OrderModel orderModel = entryModel.getOrder();

		if (orderModel.getStatus() != status)
			return new DTResults();

		List<OrderEntryModel> entries = orderModel.getOrderEntries();

		DTResults result = new DTResults();
		List<Object[]> rows = new ArrayList<Object[]>();
		result.setRecordsFiltered(entries.size());
		result.setRecordsTotal(entries.size());

		for (int i = 0; i < entries.size(); i++)
		{
			OrderEntryModel entry = entries.get(i);

			if (entryModel.getStatus() != status)
				return new DTResults();

			String[] row = { entry.getExternalId(),
					TextMapperUtils
							.getItemCategoryText(entry.getItemCategory()),
					entry.getProductTitle(), orderModel.getCustomerName(),
					entry.getPk(), orderModel.getCode() };
			rows.add(row);
		}

		result.setData(rows);

		return result;
	}

	@Override
	public DTResults getOrderEntriesByPKorId(String entryPK, String externalId)
	{
		OrderEntryModel entryModel;
		// pk has first priority
		if (StringUtils.isNotEmpty(entryPK))
			entryModel = orderService.getOrderEntry(entryPK);
		else if (StringUtils.isNotEmpty(externalId))
			entryModel = orderService.getOrderEntryForExternalId(externalId);
		else
			return new DTResults();

		if (entryModel == null)
			return new DTResults();

		OrderModel orderModel = entryModel.getOrder();
		List<OrderEntryModel> entries = orderModel.getOrderEntries();

		DTResults result = new DTResults();
		List<Object[]> rows = new ArrayList<Object[]>();
		result.setRecordsFiltered(entries.size());
		result.setRecordsTotal(entries.size());

		for (int i = 0; i < entries.size(); i++)
		{
			OrderEntryModel entry = entries.get(i);
			String[] row = { entry.getExternalId(),
					TextMapperUtils
							.getItemCategoryText(entry.getItemCategory()),
					entry.getProductTitle(), orderModel.getCustomerName(),
					entry.getPk(), orderModel.getCode() };
			rows.add(row);
		}

		result.setData(rows);

		return result;
	}

	@Override
	public DTResults getOrderEntries(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String> sp)
	{

		DTResults result = DTResultConvertor.convertPS2DT(orderService
				.getOrderEntries(startDate, endDate, startIndex, pageSize, sp));
		List<Object[]> datas = result.getData();

		datas.forEach(d -> {
			// 量身单类型
		    if(d[0]!=null)
			d[0] = TextMapperUtils.getItemCategoryText(d[0].toString());
		    
		    if(d[5]!=null)
			// 订单状态
			d[5] = TextMapperUtils
					.getOrderStatusText(Integer.valueOf(d[5].toString()));
		});

		return result;
	}

	public boolean isExist(final String orderCode)
	{
		return orderService.getOrderForCode(orderCode) != null;
	}

	@Override
	public List<OrderData> getOrdersForCurrentUser(Integer orderStatus)
	{
		HashMap<String, String> sp = new HashMap<String, String>();
		sp.put("orderStatus", orderStatus.toString());
		sp.put("salesperson", userService.getCurrentUser().getUserId());

		List<OrderData> orders = new ArrayList<OrderData>();
		List<OrderModel> orderModels = orderService.getOrders(sp);

		orderModels.forEach(o -> {
			OrderData od = new OrderData();
			orderConfiguredPopulator.populate(o, od,
					Arrays.asList(OrderOption.BASIC));
			orders.add(od);
		});

		return orders;
	}

	public void setNewOrderAction(NewOrderAction newOrderAction)
	{
		this.newOrderAction = newOrderAction;
	}

	@Override
	public List<Object[]> exportOrderEntries(Date startDate, Date endDate,
			int startIndex, int pageSize, Map<String, String> sp)
	{
		PaginationSupport ps = orderService.getOrderEntriesWithSizeData(
				startDate, endDate, startIndex, pageSize, sp);

		List<Object[]> datas = ps.getItems();
		List<Object[]> exportResults = new ArrayList<Object[]>();
		int itemCategory = Integer.valueOf(sp.get("itemCategory"));
		List<SizeAttributeModel> sizeAttributes = sizeAttributeService
				.getSizeAttributeForItemCategory(itemCategory);
		HashMap<String, String> sizeAttributesSorter = new HashMap<String, String>();
		sizeAttributes.forEach(s -> {
			sizeAttributesSorter.put(s.getName(), "");
		});

//select e.itemCategory,e.externalId,e.deliveryDate,e.customerName,e.product.code,e.style,e.comment,e.sizeDetails
		Object[] title1 = {"量身单号码","交付日","顾客姓名","布料","布料补充说明","款式","试装日","实际试装日"};
		Object[] title= ArrayUtils.addAll(title1,sizeAttributesSorter.keySet().toArray());
		int arrayLength = sizeAttributes.size();
		exportResults.add(title);
		
		datas.forEach(d -> {
			Object[] basicInformation =  Arrays.copyOfRange(d, 0, 8);
			Object[] sizeDataArrays = new String[arrayLength];

			List<SizeAttributeData> attributeDatas = JSON
					.parseArray(d[8].toString(), SizeAttributeData.class);
			HashMap<String, String> attrDataMap = new HashMap<String, String>();

			for (SizeAttributeData attrData : attributeDatas)
			{
				attrDataMap.put(attrData.getName(), attrData.getValue());
			}
			int i = 0;

			Iterator<String> itr = sizeAttributesSorter.keySet().iterator();
			
			while (itr.hasNext())
			{
				String k = itr.next();
				sizeDataArrays[i] = attrDataMap.containsKey(k)? attrDataMap.get(k): StringUtils.EMPTY;
				i = i + 1;
			}
			
			exportResults.add(ArrayUtils.addAll(basicInformation, sizeDataArrays));

		});

		return exportResults;
	}

}
