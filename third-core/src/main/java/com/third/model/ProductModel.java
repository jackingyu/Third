package com.third.model;
// Generated Nov 10, 2016 4:52:00 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * ProductModel generated by hbm2java
 */
public class ProductModel  implements java.io.Serializable {


     private String pk;
     private String code;
     private String producttitle;
     private String producttype;
     private Date createTime;
     private Date modificationTime;
     private UserModel createdBy;

    public ProductModel() {
    }

    public ProductModel(String code, String producttitle, String producttype, Date createTime, Date modificationTime, UserModel createdBy) {
       this.code = code;
       this.producttitle = producttitle;
       this.producttype = producttype;
       this.createTime = createTime;
       this.modificationTime = modificationTime;
       this.createdBy = createdBy;
    }
   
    public String getPk() {
        return this.pk;
    }
    
    public void setPk(String pk) {
        this.pk = pk;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getProducttitle() {
        return this.producttitle;
    }
    
    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }
    public String getProducttype() {
        return this.producttype;
    }
    
    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getModificationTime() {
        return this.modificationTime;
    }
    
    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }
    public UserModel getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(UserModel createdBy) {
        this.createdBy = createdBy;
    }




}


