package com.third.model;
// Generated Dec 14, 2016 10:14:28 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * OrderProcessRecordModel generated by hbm2java
 */
public class OrderProcessRecordModel  implements java.io.Serializable {


     private String pk;
     private String orderCode;
     private String fromStatus;
     private String toStatus;
     private String message;
     private Date processTime;
     private Date modificationTime;
     private Date createTime;
     private UserModel createdBy;

    public OrderProcessRecordModel() {
    }

    public OrderProcessRecordModel(String orderCode, String fromStatus, String toStatus, String message, Date processTime, Date modificationTime, Date createTime, UserModel createdBy) {
       this.orderCode = orderCode;
       this.fromStatus = fromStatus;
       this.toStatus = toStatus;
       this.message = message;
       this.processTime = processTime;
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
    public String getOrderCode() {
        return this.orderCode;
    }
    
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    public String getFromStatus() {
        return this.fromStatus;
    }
    
    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
    }
    public String getToStatus() {
        return this.toStatus;
    }
    
    public void setToStatus(String toStatus) {
        this.toStatus = toStatus;
    }
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getProcessTime() {
        return this.processTime;
    }
    
    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
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


