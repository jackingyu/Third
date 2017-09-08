package com.third.facade.data;

import java.math.BigDecimal;
import java.util.Date;

import com.third.model.OrderModel;
import com.third.model.SourceModel;


public class SalesQuationData
{
	private String pk;
	private String customerName;
	private String cellphone;
	private String contactinfo;
	private Date weddingDate;
	private Date tryDate;
	private Date photoDate;
	private Date deliveryDate;
	private String coSalesperson;
	private String comment;
	private String paidamount;
	private String paymentMethod;
	private String orderCode;
	private SourceData source;

	public String getPk()
	{
		return pk;
	}

	public void setPk(String pk)
	{
		this.pk = pk;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	public String getCellphone()
	{
		return cellphone;
	}

	public void setCellphone(String cellphone)
	{
		this.cellphone = cellphone;
	}

	public String getContactinfo()
	{
		return contactinfo;
	}

	public void setContactinfo(String contactinfo)
	{
		this.contactinfo = contactinfo;
	}

	public Date getWeddingDate()
	{
		return weddingDate;
	}

	public void setWeddingDate(Date weddingDate)
	{
		this.weddingDate = weddingDate;
	}

	public Date getTryDate()
	{
		return tryDate;
	}

	public void setTryDate(Date tryDate)
	{
		this.tryDate = tryDate;
	}

	public Date getPhotoDate()
	{
		return photoDate;
	}

	public void setPhotoDate(Date photoDate)
	{
		this.photoDate = photoDate;
	}

	public Date getDeliveryDate()
	{
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate)
	{
		this.deliveryDate = deliveryDate;
	}

	public String getCoSalesperson()
	{
		return coSalesperson;
	}

	public void setCoSalesperson(String coSalesperson)
	{
		this.coSalesperson = coSalesperson;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getPaidamount()
	{
		return paidamount;
	}

	public void setPaidamount(String paidamount)
	{
		this.paidamount = paidamount;
	}

	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	public String getOrderCode()
	{
		return orderCode;
	}

	public void setOrderCode(String orderCode)
	{
		this.orderCode = orderCode;
	}

	public SourceData getSource()
	{
		return source;
	}

	public void setSource(SourceData source)
	{
		this.source = source;
	}
}
