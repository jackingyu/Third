package com.third.facade.data;

import java.util.Date;
import java.util.List;


public class OrderData extends AbstractData
{
	private String orderCode;

	private CustomerData customer;
	private String cellphone;
	private Date weddingDate;
	private Date tryDate;
	private Date photoDate;
	private Date deliveryDate;

	private String receiveable;
	private String openamount;
	private String paidamount;
	private List<PaymentData> payments;

	private String coSalesperson;
	private Date orderDate;
	private String comment;
	private UserData salesPerson;
	private SourceData source;
	private StoreData store;
	private List<OrderEntryData> entries;
	private String customerName;
	private String contactinfo;
   private Integer status;
   private String statusText;
   
	public String getOrderCode()
	{
		return orderCode;
	}

	public void setOrderCode(String orderCode)
	{
		this.orderCode = orderCode;
	}

	public String getCoSalesperson()
	{
		return coSalesperson;
	}

	public void setCoSalesperson(String coSalesperson)
	{
		this.coSalesperson = coSalesperson;
	}


	public String getCellphone()
	{
		return cellphone;
	}

	public void setCellphone(String cellphone)
	{
		this.cellphone = cellphone;
	}

	public Date getOrderDate()
	{
		return orderDate;
	}

	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
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

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getReceiveable()
	{
		return receiveable;
	}

	public void setReceiveable(String receiveable)
	{
		this.receiveable = receiveable;
	}

	public String getOpenamount()
	{
		return openamount;
	}

	public void setOpenamount(String openamount)
	{
		this.openamount = openamount;
	}

	public UserData getSalesPerson()
	{
		return salesPerson;
	}

	public void setSalesPerson(UserData salesPerson)
	{
		this.salesPerson = salesPerson;
	}

	public SourceData getSource()
	{
		return source;
	}

	public void setSource(SourceData source)
	{
		this.source = source;
	}

	public StoreData getStore()
	{
		return store;
	}

	public void setStore(StoreData store)
	{
		this.store = store;
	}

	public List<PaymentData> getPayments()
	{
		return payments;
	}

	public void setPayments(List<PaymentData> payments)
	{
		this.payments = payments;
	}

	public List<OrderEntryData> getEntries()
	{
		return entries;
	}

	public void setEntries(List<OrderEntryData> entries)
	{
		this.entries = entries;
	}

	public Date getWeddingDate()
	{
		return weddingDate;
	}

	public void setWeddingDate(Date weddingDate)
	{
		this.weddingDate = weddingDate;
	}

	public CustomerData getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerData customer)
	{
		this.customer = customer;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public String getStatusText()
	{
		return statusText;
	}

	public void setStatusText(String statusText)
	{
		this.statusText = statusText;
	}

	public String getPaidamount()
	{
		return paidamount;
	}

	public void setPaidamount(String paidamount)
	{
		this.paidamount = paidamount;
	}

	public String getContactinfo()
	{
		return contactinfo;
	}

	public void setContactinfo(String contactinfo)
	{
		this.contactinfo = contactinfo;
	}

}
