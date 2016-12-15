package com.third.model;
// Generated Dec 14, 2016 10:14:28 PM by Hibernate Tools 4.3.1



/**
 * CityModel generated by hbm2java
 */
public class CityModel  implements java.io.Serializable {


     private String isoCode;
     private String name;
     private RegionModel region;

    public CityModel() {
    }

	
    public CityModel(String isoCode) {
        this.isoCode = isoCode;
    }
    public CityModel(String isoCode, String name, RegionModel region) {
       this.isoCode = isoCode;
       this.name = name;
       this.region = region;
    }
   
    public String getIsoCode() {
        return this.isoCode;
    }
    
    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public RegionModel getRegion() {
        return this.region;
    }
    
    public void setRegion(RegionModel region) {
        this.region = region;
    }




}


