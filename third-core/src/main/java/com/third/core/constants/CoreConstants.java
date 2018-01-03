package com.third.core.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public interface CoreConstants {
	interface PaymentType {
		final String DownPayment = "0";
		final String NormalPayment = "1";
	}

	interface PaymentMethod {
		final String Cash = "0";
		final String CreditCard = "1";
		final String O2OAliPay = "2";
	}

	interface ItemCategory {
		final String Suit = "10";
		final String Trousers = "20";
		final String Shirt = "30";
		final String Vest = "40";
		final String Norm = "50";
	}

	interface ReservationChannel {
		final String Weixin = "WEIXIN";
		final String Web = "WEB";
	}

	interface MediaFolder {
		final String SizeOrderFolder = "sizeorders";
	}

	interface OrderStatus {
	    final Integer ALL_STATUS = -1;
		final Integer NEW = 0;
		final Integer STORE_APPROVE = 10;
		final Integer FINICIAL_APPROVE = 20;
		final Integer FACTORY_APPROVE = 30;
		final Integer FACTORY_RECEIPT = 35;
		final Integer FACTORY_DELIVERED = 40;
		final Integer STORE_RECEIPT = 50;
		final Integer STORE_DELIVERED = 60;
		final List<Integer> ALL = Arrays.asList(NEW, STORE_APPROVE,
				FINICIAL_APPROVE, FACTORY_APPROVE, FACTORY_DELIVERED,FACTORY_RECEIPT,
				STORE_RECEIPT, STORE_DELIVERED);

	}

	interface Session {
		static final String CURRENT_CUSTOMER = "currentCustomer";
		static final String CURRENT_USER = "currentUser";
		static final String CURRENT_USER_ID = "currentUserId";
		static final String MENU = "menu";
		static final String STORE = "avaliableStore";
		static final String MOBILE = "isMobile";
	}

	interface Category {
		final String Suit = "A";
		final String Trousers = "B";
		final String Shirt = "C";
		final String Vest = "D";
		final String Norm = "N";
	}
	
	interface SourceType {
		final String NORMAL = "0";
		final String EXHIBITION = "10";
	}
}
