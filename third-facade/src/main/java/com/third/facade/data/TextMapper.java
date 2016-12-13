package com.third.facade.data;

import java.util.HashMap;

import com.third.model.CoreConstants;


public class TextMapper
{
	public static HashMap<String, String> PaymentType = new HashMap<String, String>();
	public static HashMap<String, String> PaymentMethod = new HashMap<String, String>();
	public static HashMap<String, String> ItemCategory = new HashMap<String, String>();
	public static HashMap<String, String> SizeAttributeGroup = new HashMap<String, String>();
	public static HashMap<String, String> ReservationChannel= new HashMap<String, String>();
	public static HashMap<String, String> OrderStatus = new HashMap<String, String>();


	static
	{
		PaymentType.put(CoreConstants.PaymentType.DownPayment, "订金");
		PaymentType.put(CoreConstants.PaymentType.NormalPayment, "付款");
	}

	static
	{
		PaymentMethod.put(CoreConstants.PaymentMethod.CreditCard, "信用卡");
		PaymentMethod.put(CoreConstants.PaymentMethod.Cash, "现金");
	}

	static
	{
		ItemCategory.put(CoreConstants.ItemCategory.Suit, "西服量身单");
		ItemCategory.put(CoreConstants.ItemCategory.Trousers, "西裤量身单");
		ItemCategory.put(CoreConstants.ItemCategory.Shirt, "衬衫量身单");
		ItemCategory.put(CoreConstants.ItemCategory.Vest, "背心量身单");
		ItemCategory.put(CoreConstants.ItemCategory.Norm, "普通销售项");
	}

	static
	{
		SizeAttributeGroup.put("10", "量");
		SizeAttributeGroup.put("20", "裁");
		SizeAttributeGroup.put("30", "试");
	}
	
	static
	{
		ReservationChannel.put(CoreConstants.ReservationChannel.Weixin, "微信");
		ReservationChannel.put(CoreConstants.ReservationChannel.Web, "网站");
	}
	
	static
	{
		OrderStatus.put(CoreConstants.OrderStatus.CREATE.toString(), "新建");
		OrderStatus.put(CoreConstants.OrderStatus.FACTORY_CONFIRM_PRODUCTION.toString(), "工厂排产");
		OrderStatus.put(CoreConstants.OrderStatus.FACTORY_RECEIPT.toString(), "工厂收货");
		OrderStatus.put(CoreConstants.OrderStatus.FACTORY_DELIVERY.toString(), "工厂发货");
		OrderStatus.put(CoreConstants.OrderStatus.STORE_RECEIPT.toString(), "门店收货");
		OrderStatus.put(CoreConstants.OrderStatus.CUSTOMER_FETCH.toString(), "顾客取件");

	}

}