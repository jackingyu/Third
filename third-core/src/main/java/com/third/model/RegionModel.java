package com.third.model;
// Generated Aug 27, 2017 10:39:03 PM by Hibernate Tools 4.3.1


import java.util.ArrayList;
import java.util.Collection;

/**
 * RegionModel generated by hbm2java
 */
public class RegionModel  implements java.io.Serializable {


     private String isoCode;
     private String name;
     private Collection citys = new ArrayList(0);

    public RegionModel() {
    }

	
    public RegionModel(String isoCode) {
        this.isoCode = isoCode;
    }
    public RegionModel(String isoCode, String name, Collection citys) {
       this.isoCode = isoCode;
       this.name = name;
       this.citys = citys;
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
    public Collection getCitys() {
        return this.citys;
    }
    
    public void setCitys(Collection citys) {
        this.citys = citys;
    }




}


