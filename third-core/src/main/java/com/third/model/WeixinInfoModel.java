package com.third.model;
// Generated Aug 27, 2017 10:39:03 PM by Hibernate Tools 4.3.1



/**
 * WeixinInfoModel generated by hbm2java
 */
public class WeixinInfoModel  implements java.io.Serializable {


     private Integer id;
     private String appId;
     private String appSecret;

    public WeixinInfoModel() {
    }

	
    public WeixinInfoModel(Integer id) {
        this.id = id;
    }
    public WeixinInfoModel(Integer id, String appId, String appSecret) {
       this.id = id;
       this.appId = appId;
       this.appSecret = appSecret;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAppId() {
        return this.appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getAppSecret() {
        return this.appSecret;
    }
    
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }




}


