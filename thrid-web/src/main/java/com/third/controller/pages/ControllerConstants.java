package com.third.controller.pages;

public interface ControllerConstants {
	interface Pages {
		final static String PREFIX = "/pages";
		final static String MASTER = PREFIX + "/master";
		final static String LOGIN = PREFIX + "/login";
	}

	interface Fragements {
		final static String PREFIX = "/fragments";
		final static String USERGROULIST = PREFIX + "/user/userGroupList";
		final static String USERLIST = PREFIX + "/user/userList";
		final static String CUSTOMERLIST = PREFIX + "/sales/customerList";
		final static String ORDERLIST = PREFIX + "/sales/orderList";
		final static String SIZEDATAPANEL = PREFIX + "/sales/sizeDataPanel";
		final static String RESERVATIONLIST = PREFIX + "/sales/reservationList";
		final static String ORDERPROCESS = PREFIX
				+ "/orderprocess/orderProcess";
		final static String IMAGE = PREFIX + "/template/image";
	}

	interface WeiXin {
		final static String PREFIX = "/pages/weixin";
		final static String FIRSTPAGE = PREFIX + "/first_page";
		final static String REGISTERPAGE = PREFIX + "/register";
		final static String ORDERLISTPAGE = PREFIX + "/orderlist";
		final static String ORDERDETAILPAGE = PREFIX + "/orderdetail";

		final static String RESERVATIONPAGE = PREFIX + "/reservation";
		final static String RESERVATIONMODIFYPAGE = PREFIX
				+ "/reservationmodify";
		static final String RESERVATIONLISTPAGE = PREFIX + "/reservationlist";
		static final String RESERVATIONDETAILPAGE = PREFIX
				+ "/reservationdetail";

		static final String STORELISTPAGE = PREFIX + "/storelist";
		static final String STOREDETAILPAGE = PREFIX + "/storedetail";
		static final String REGISTER = PREFIX + "/register";
		static final String BIND = PREFIX + "/bind";
		static final String MEMBERPAGE = PREFIX + "/member";
		static final String ERRORPAGE = PREFIX + "/error";
		static final String ERRORURL = "/wxerror";

	}

	interface LTE {
		final static String PREFIX = "/lte";

		final static String PAGE = "/lte/pages";
		final static String FRAGEMENTS = "/fragments/lte";

		final static String HOMEPAGE = PAGE + "/home";
		final static String MHOMEPAGE = PAGE + "/mhome";

		final static String ORDERLISTPAGE = PAGE + "/sales/orderList";
		final static String ORDERDETAILSPAGE = PAGE + "/sales/orderDetails";
		final static String CREATEORDERPAGE = PAGE + "/sales/createOrder";
		final static String MODIFYORDERPAGE = PAGE + "/sales/modifyOrder";
		static final String CREATESIZEORDERPAGE = PAGE
				+ "/sales/createSizeOrder";
		static final String ORDERENTRYDETAILPAGE = PAGE
				+ "/sales/orderEntryDetails";
		static final String ORDERENTRYSCANPAGE = PAGE+ "/sales/scanSizeOrder";
		static final String ORDERENTRYLISTPAGE = PAGE + "/sales/orderEntryList";
		static final String SCANORDERPAGE = PAGE + "/factory/scanOrder";
		static final String ORDERPROCESSRECORDPAGE = PAGE + "/sales/orderProcessRecord";

		final static String LOGINPAGE = PAGE + "/security/login";

		final static String CUSTOMERLISTPAGE = PAGE + "/customer/customerList";
		final static String CREATECUSTOMERPAGE = PAGE
				+ "/customer/customerDetails";
		final static String MODIFYCUSTOMERPAGE = PAGE
				+ "/customer/customerDetails";
		final static String SOURCELISTPAGE = PAGE + "/customer/sourceList";
		final static String SOURCELISTFORSTOREPAGE = PAGE + "/sales/sourceList";
		final static String PAYMENTLISTPAGE = PAGE + "/fi/paymentList";
		final static String STOREDASHBOARDPAGE = PAGE + "/fi/storeDashboard";

		final static String USERLISTPAGE = PAGE + "/user/userList";
		final static String USERDETAILSPAGE = PAGE + "/user/userDetails";

		static final String RESERVATIONLISTPAGE = PAGE
				+ "/customer/reservationList";

		static final String RESERVATIONDETAILSPAGE = FRAGEMENTS
				+ "/customer/reservationDetails";

		static final String PRODUCTLISTPAGE = PAGE + "/product/productList";
		static final String MODIFYPRODUCTPAGE = PAGE + "/product/modifyProduct";
		static final String SALESQUOTATIONLISTPAGE = PAGE + "/sales/salesQuotationList";

		static final String SALESQUOTATIONPAGE = PAGE + "/sales/salesQuotation";
		static final String ORDERITEMFRAGMENT = PAGE + "/sales/orderItem";
		
		static final String NOAUTHPAGE = PAGE + "/security/401";

		static final String MYORDERPAGE = PAGE + "/sales/myOrder";
		
		final static String STORELISTPAGE = PAGE+"/store/storeList";
		final static String STOREDETAILSPAGE = PAGE+"/store/storeDetails";

	}

}
