package com.third.controller.pages;

public interface ControllerConstants
{
	interface Pages
	{
		final static String PREFIX = "/pages";
		final static String MASTER = PREFIX + "/master";
		final static String LOGIN = PREFIX + "/login";
	}

	interface Fragements
	{
		final static String PREFIX = "/fragments";
		final static String USERGROULIST = PREFIX + "/user/userGroupList";
		final static String USERLIST = PREFIX + "/user/userList";
		final static String CUSTOMERLIST = PREFIX + "/sales/customerList";
		final static String ORDERLIST = PREFIX + "/sales/orderList";
		final static String SIZEDATAPANEL = PREFIX + "/sales/sizeDataPanel";
		final static String RESERVATIONLIST = PREFIX + "/sales/reservationList";
		final static String ORDERPROCESS = PREFIX + "/orderprocess/orderProcess";
		final static String IMAGE = PREFIX + "/template/image";
	}

	interface WeiXin
	{
		final static String PREFIX="/pages/weixin";
		final static String FIRSTPAGE= PREFIX+"/first_page";
		final static String REGISTERPAGE= PREFIX+"/register";
		final static String ORDERLISTPAGE= PREFIX+"/orderlist";
		final static String ORDERDETAILPAGE= PREFIX+"/orderdetail";
		
		final static String RESERVATIONPAGE= PREFIX+"/reservation";
		final static String RESERVATIONMODIFYPAGE= PREFIX+"/reservationmodify";
		static final String RESERVATIONLISTPAGE = PREFIX+"/reservationlist";
		static final String RESERVATIONDETAILPAGE = PREFIX+"/reservationdetail";
		
		static final String STORELISTPAGE = PREFIX+"/storelist";
		static final String STOREDETAILPAGE = PREFIX+"/storedetail";
		
	}
	
}
