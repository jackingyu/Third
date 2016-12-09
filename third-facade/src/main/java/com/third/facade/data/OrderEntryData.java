package com.third.facade.data;

import java.util.Date;
import java.util.Map;

import com.third.model.OrderModel;
import com.third.model.UserModel;


public class OrderEntryData extends AbstractData
{
	private Integer entryNo;
	private String itemCategory;
	private String itemCategoryText;
	private String style;
	private ProductData product;
	private String productTitle;
	private Integer quantity;
	private String designer;
	private Date deliveryDate;
	private Date sizeDate;
	private Date tryDate;
	private String sizeDetails;
	private Date createTime;
	private Date modificationTime;
	private String comment;
	private UserData createBy;
	private String orderCode;
	private String storeName;
	private String customerName;
	private Map<String, SizeAttributeGroupData> sizeDatas;
	private String sizeImageUrl;

	public Integer getEntryNo()
	{
		return entryNo;
	}

	public void setEntryNo(Integer entryNo)
	{
		this.entryNo = entryNo;
	}

	public String getItemCategory()
	{
		return itemCategory;
	}

	public void setItemCategory(String itemCategory)
	{
		this.itemCategory = itemCategory;
	}

	public String getStyle()
	{
		return style;
	}

	public void setStyle(String style)
	{
		this.style = style;
	}

	public ProductData getProduct()
	{
		return product;
	}

	public void setProduct(ProductData product)
	{
		this.product = product;
	}

	public String getProductTitle()
	{
		return productTitle;
	}

	public void setProductTitle(String productTitle)
	{
		this.productTitle = productTitle;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	public String getDesigner()
	{
		return designer;
	}

	public void setDesigner(String designer)
	{
		this.designer = designer;
	}

	public Date getDeliveryDate()
	{
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate)
	{
		this.deliveryDate = deliveryDate;
	}

	public Date getSizeDate()
	{
		return sizeDate;
	}

	public void setSizeDate(Date sizeDate)
	{
		this.sizeDate = sizeDate;
	}

	public String getSizeDetails()
	{
		return sizeDetails;
	}

	public void setSizeDetails(String sizeDetails)
	{
		this.sizeDetails = sizeDetails;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Date getModificationTime()
	{
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime)
	{
		this.modificationTime = modificationTime;
	}


	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public UserData getCreateBy()
	{
		return createBy;
	}

	public void setCreateBy(UserData createBy)
	{
		this.createBy = createBy;
	}

	public String getOrderCode()
	{
		return orderCode;
	}

	public void setOrderCode(String orderCode)
	{
		this.orderCode = orderCode;
	}

	public String getStoreName()
	{
		return storeName;
	}

	public void setStoreName(String storeName)
	{
		this.storeName = storeName;
	}

	public Date getTryDate()
	{
		return tryDate;
	}

	public void setTryDate(Date tryDate)
	{
		this.tryDate = tryDate;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	public String getItemCategoryText()
	{
		return itemCategoryText;
	}

	public void setItemCategoryText(String itemCategoryText)
	{
		this.itemCategoryText = itemCategoryText;
	}

	public Map<String, SizeAttributeGroupData> getSizeDatas()
	{
		return sizeDatas;
	}

	public void setSizeDatas(Map<String, SizeAttributeGroupData> sizeDatas)
	{
		this.sizeDatas = sizeDatas;
	}

	public String getSizeImageUrl()
	{
		return sizeImageUrl;
	}

	public void setSizeImageUrl(String sizeImageUrl)
	{
		this.sizeImageUrl = sizeImageUrl;
	}

}
