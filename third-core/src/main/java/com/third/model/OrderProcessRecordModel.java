package com.third.model;
// Generated Nov 21, 2018 10:12:13 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * OrderProcessRecordModel generated by hbm2java
 */
public class OrderProcessRecordModel  implements java.io.Serializable {


     private String pk;
     private String orderCode;
     private String sizeOrderExternalId;
     private String sizeOrderPk;
     private String productCode;
     private String productTitle;
     private Integer quantity;
     private String name;
     private String storeCode;
     private String storeName;
     private String fromStatus;
     private String toStatus;
     private String message;
     private Date processTime;
     private Date modificationTime;
     private Date createTime;
     private UserModel createdBy;

    public OrderProcessRecordModel() {
    }

    public OrderProcessRecordModel(String orderCode, String sizeOrderExternalId, String sizeOrderPk, String productCode, String productTitle, Integer quantity, String name, String storeCode, String storeName, String fromStatus, String toStatus, String message, Date processTime, Date modificationTime, Date createTime, UserModel createdBy) {
       this.orderCode = orderCode;
       this.sizeOrderExternalId = sizeOrderExternalId;
       this.sizeOrderPk = sizeOrderPk;
       this.productCode = productCode;
       this.productTitle = productTitle;
       this.quantity = quantity;
       this.name = name;
       this.storeCode = storeCode;
       this.storeName = storeName;
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
    public String getSizeOrderExternalId() {
        return this.sizeOrderExternalId;
    }
    
    public void setSizeOrderExternalId(String sizeOrderExternalId) {
        this.sizeOrderExternalId = sizeOrderExternalId;
    }
    public String getSizeOrderPk() {
        return this.sizeOrderPk;
    }
    
    public void setSizeOrderPk(String sizeOrderPk) {
        this.sizeOrderPk = sizeOrderPk;
    }
    public String getProductCode() {
        return this.productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getProductTitle() {
        return this.productTitle;
    }
    
    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getStoreCode() {
        return this.storeCode;
    }
    
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }
    public String getStoreName() {
        return this.storeName;
    }
    
    public void setStoreName(String storeName) {
        this.storeName = storeName;
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


