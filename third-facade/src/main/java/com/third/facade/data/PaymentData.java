package com.third.facade.data;

import java.math.BigDecimal;
import java.util.Date;


public class PaymentData extends AbstractData
{
	private String paymentMethod;
	private String paymentType;
	private String paymentMethodText;
	private String paymentTypeText;
	private BigDecimal amount;
	private Date paidTime;
	private String orderCode;
	private String storeCode;

	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentType()
	{
		return paymentType;
	}

	public void setPaymentType(String paymentType)
	{
		this.paymentType = paymentType;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public Date getPaidTime()
	{
		return paidTime;
	}

	public void setPaidTime(Date paidTime)
	{
		this.paidTime = paidTime;
	}

	public String getOrderCode()
	{
		return orderCode;
	}

	public void setOrderCode(String orderCode)
	{
		this.orderCode = orderCode;
	}

	public String getStoreCode()
	{
		return storeCode;
	}

	public void setStoreCode(String storeCode)
	{
		this.storeCode = storeCode;
	}

	public String getPaymentMethodText()
	{
		return paymentMethodText;
	}

	public void setPaymentMethodText(String paymentMethodText)
	{
		this.paymentMethodText = paymentMethodText;
	}

	public String getPaymentTypeText()
	{
		return paymentTypeText;
	}

	public void setPaymentTypeText(String paymentTypeText)
	{
		this.paymentTypeText = paymentTypeText;
	}

}
