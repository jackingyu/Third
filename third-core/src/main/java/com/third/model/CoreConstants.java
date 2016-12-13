package com.third.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface CoreConstants
{
	interface PaymentType
	{
		final String DownPayment = "0";
		final String NormalPayment = "1";
	}

	interface PaymentMethod
	{
		final String Cash = "0";
		final String CreditCard = "1";
	}

	interface ItemCategory
	{
		final String Suit = "10";
		final String Trousers = "20";
		final String Shirt = "30";
		final String Vest = "40";
		final String Norm = "50";
	}
	
	interface ReservationChannel
	{
		final String Weixin = "WEIXIN";
		final String Web = "WEB";
	}
	
	interface MediaFolder
	{
		final String SizeOrderFolder = "sizeorders";
	}
	
	interface OrderStatus
	{
		final Integer CREATE = 0;
		final Integer FACTORY_CONFIRM_PRODUCTION = 10;
		final Integer FACTORY_RECEIPT = 11;
		final Integer FACTORY_DELIVERY = 12;
		final Integer STORE_RECEIPT = 30;
		final Integer CUSTOMER_FETCH = 50;
		final List<Integer> ALL = Arrays.asList(CREATE,FACTORY_CONFIRM_PRODUCTION,FACTORY_DELIVERY,FACTORY_RECEIPT,STORE_RECEIPT,CUSTOMER_FETCH);
	
	}

	interface Session
	{
		static final String CURRENT_CUSTOMER = "currentCustomer";
		static final String CURRENT_USER = "currentUser";
		static final String CURRENT_USER_ID = "currentUserId";
	}
}
