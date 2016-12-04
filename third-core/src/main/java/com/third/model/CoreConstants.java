package com.third.model;

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


}
