package com.third.model;
// Generated Dec 9, 2016 11:47:25 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * CustomerModel generated by hbm2java
 */
public class CustomerModel  implements java.io.Serializable {


     private String pk;
     private String cellphone;
     private String name;
     private Date birthday;
     private Date weddingDate;
     private String comment;
     private String email;
     private String QQ;
     private SourceModel source;
     private AddressModel address;
     private Date modificationTime;
     private Date createTime;
     private UserModel createdBy;

    public CustomerModel() {
    }

    public CustomerModel(String cellphone, String name, Date birthday, Date weddingDate, String comment, String email, String QQ, SourceModel source, AddressModel address, Date modificationTime, Date createTime, UserModel createdBy) {
       this.cellphone = cellphone;
       this.name = name;
       this.birthday = birthday;
       this.weddingDate = weddingDate;
       this.comment = comment;
       this.email = email;
       this.QQ = QQ;
       this.source = source;
       this.address = address;
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
    public String getCellphone() {
        return this.cellphone;
    }
    
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public Date getWeddingDate() {
        return this.weddingDate;
    }
    
    public void setWeddingDate(Date weddingDate) {
        this.weddingDate = weddingDate;
    }
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getQQ() {
        return this.QQ;
    }
    
    public void setQQ(String QQ) {
        this.QQ = QQ;
    }
    public SourceModel getSource() {
        return this.source;
    }
    
    public void setSource(SourceModel source) {
        this.source = source;
    }
    public AddressModel getAddress() {
        return this.address;
    }
    
    public void setAddress(AddressModel address) {
        this.address = address;
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


