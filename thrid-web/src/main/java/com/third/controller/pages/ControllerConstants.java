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
		final static String PREFIX="";
		final static String FIRSTPAGE= "";
	}
}
