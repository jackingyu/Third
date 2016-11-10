package com.third.model;
// Generated Nov 10, 2016 4:52:00 PM by Hibernate Tools 4.3.1



/**
 * AddressModel generated by hbm2java
 */
public class AddressModel  implements java.io.Serializable {


     private String pk;
     private RegionModel region;
     private CityModel city;
     private String adr1;
     private String adr2;
     private String tel1;
     private String tel2;

    public AddressModel() {
    }

    public AddressModel(RegionModel region, CityModel city, String adr1, String adr2, String tel1, String tel2) {
       this.region = region;
       this.city = city;
       this.adr1 = adr1;
       this.adr2 = adr2;
       this.tel1 = tel1;
       this.tel2 = tel2;
    }
   
    public String getPk() {
        return this.pk;
    }
    
    public void setPk(String pk) {
        this.pk = pk;
    }
    public RegionModel getRegion() {
        return this.region;
    }
    
    public void setRegion(RegionModel region) {
        this.region = region;
    }
    public CityModel getCity() {
        return this.city;
    }
    
    public void setCity(CityModel city) {
        this.city = city;
    }
    public String getAdr1() {
        return this.adr1;
    }
    
    public void setAdr1(String adr1) {
        this.adr1 = adr1;
    }
    public String getAdr2() {
        return this.adr2;
    }
    
    public void setAdr2(String adr2) {
        this.adr2 = adr2;
    }
    public String getTel1() {
        return this.tel1;
    }
    
    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }
    public String getTel2() {
        return this.tel2;
    }
    
    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }




}


