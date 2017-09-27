package com.third.facade.data;

public class AddressData extends AbstractData {
	private CityData city;
	private RegionData region;
	private DistrictData district;
	private String adr1;
	private String adr2;
	private String tel1;
	private String tel2;

	public String getAdr1()
	{
		return adr1;
	}

	public void setAdr1(String adr1)
	{
		this.adr1 = adr1;
	}

	public String getAdr2()
	{
		return adr2;
	}

	public void setAdr2(String adr2)
	{
		this.adr2 = adr2;
	}

	public String getTel1()
	{
		return tel1;
	}

	public void setTel1(String tel1)
	{
		this.tel1 = tel1;
	}

	public String getTel2()
	{
		return tel2;
	}

	public void setTel2(String tel2)
	{
		this.tel2 = tel2;
	}

	public CityData getCity()
	{
		return city;
	}

	public void setCity(CityData city)
	{
		this.city = city;
	}

	public RegionData getRegion()
	{
		return region;
	}

	public void setRegion(RegionData region)
	{
		this.region = region;
	}

	public DistrictData getDistrict()
	{
		return district;
	}

	public void setDistrict(DistrictData district)
	{
		this.district = district;
	}

}
