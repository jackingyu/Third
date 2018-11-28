package com.third.model;
// Generated Nov 21, 2018 10:12:13 AM by Hibernate Tools 4.3.1



/**
 * DistrictModel generated by hbm2java
 */
public class DistrictModel  implements java.io.Serializable {


     private String isoCode;
     private String name;
     private CityModel city;

    public DistrictModel() {
    }

	
    public DistrictModel(String isoCode) {
        this.isoCode = isoCode;
    }
    public DistrictModel(String isoCode, String name, CityModel city) {
       this.isoCode = isoCode;
       this.name = name;
       this.city = city;
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
    public CityModel getCity() {
        return this.city;
    }
    
    public void setCity(CityModel city) {
        this.city = city;
    }




}


