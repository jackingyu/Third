package com.third.model;
// Generated Aug 27, 2017 10:39:03 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * ReservationModel generated by hbm2java
 */
public class ReservationModel  implements java.io.Serializable {


     private String pk;
     private CustomerModel customer;
     private CityModel city;
     private StoreModel store;
     private String name;
     private String cellphone;
     private Integer status;
     private Date reservationDate;
     private String channel;
     private String comment;
     private Date modificationTime;
     private Date createTime;
     private UserModel createdBy;

    public ReservationModel() {
    }

    public ReservationModel(CustomerModel customer, CityModel city, StoreModel store, String name, String cellphone, Integer status, Date reservationDate, String channel, String comment, Date modificationTime, Date createTime, UserModel createdBy) {
       this.customer = customer;
       this.city = city;
       this.store = store;
       this.name = name;
       this.cellphone = cellphone;
       this.status = status;
       this.reservationDate = reservationDate;
       this.channel = channel;
       this.comment = comment;
       this.modificationTime = modificationTime;
       this.createTime = createTime;
       this.createdBy = createdBy;
    }
   
    public String getPk() {
        return this.pk;
    }
    
    public void setPk(String pk) {
        this.pk = pk;
    }
    public CustomerModel getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
    public CityModel getCity() {
        return this.city;
    }
    
    public void setCity(CityModel city) {
        this.city = city;
    }
    public StoreModel getStore() {
        return this.store;
    }
    
    public void setStore(StoreModel store) {
        this.store = store;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getCellphone() {
        return this.cellphone;
    }
    
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getReservationDate() {
        return this.reservationDate;
    }
    
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
    public String getChannel() {
        return this.channel;
    }
    
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Date getModificationTime() {
        return this.modificationTime;
    }
    
    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public UserModel getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(UserModel createdBy) {
        this.createdBy = createdBy;
    }




}


