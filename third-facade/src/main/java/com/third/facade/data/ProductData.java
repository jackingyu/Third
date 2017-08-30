package com.third.facade.data;

public class ProductData extends AbstractData
{
	private String code;
   private String producttitle;
   private String producttype;

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getProducttitle()
	{
		return producttitle;
	}

	public void setProducttitle(String producttitle)
	{
		this.producttitle = producttitle;
	}

	public String getProducttype()
	{
		return producttype;
	}

	public void setProducttype(String producttype)
	{
		this.producttype = producttype;
	}
	

}
