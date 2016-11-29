package com.third.facade.data;

import java.util.HashMap;


public class TextMapper
{
	public static HashMap<String, String> PaymentType = new HashMap<String, String>();
	public static HashMap<String, String> PaymentMethod = new HashMap<String, String>();
	public static HashMap<String, String> ItemCategory = new HashMap<String, String>();


	static
	{
		PaymentType.put("0", "订金");
		PaymentType.put("1", "付款");
	}

	static
	{
		PaymentMethod.put("0", "信用卡");
		PaymentMethod.put("1", "现金");
	}

	static
	{
		ItemCategory.put("10", "西服量身单");
		ItemCategory.put("20", "西裤量身单");
		ItemCategory.put("30", "衬衫量身单");
		ItemCategory.put("40", "背心量身单");
		ItemCategory.put("50", "普通销售项");
	}

}
