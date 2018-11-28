package com.third.model;
// Generated Nov 21, 2018 10:12:13 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * TextMessageModel generated by hbm2java
 */
public class TextMessageModel  implements java.io.Serializable {


     private String pk;
     private String type;
     private String message;
     private String receiver;
     private Boolean sentTime;
     private Boolean sent;
     private Date modificationTime;
     private Date createTime;

    public TextMessageModel() {
    }

    public TextMessageModel(String type, String message, String receiver, Boolean sentTime, Boolean sent, Date modificationTime, Date createTime) {
       this.type = type;
       this.message = message;
       this.receiver = receiver;
       this.sentTime = sentTime;
       this.sent = sent;
       this.modificationTime = modificationTime;
       this.createTime = createTime;
    }
   
    public String getPk() {
        return this.pk;
    }
    
    public void setPk(String pk) {
        this.pk = pk;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    public String getReceiver() {
        return this.receiver;
    }
    
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public Boolean getSentTime() {
        return this.sentTime;
    }
    
    public void setSentTime(Boolean sentTime) {
        this.sentTime = sentTime;
    }
    public Boolean getSent() {
        return this.sent;
    }
    
    public void setSent(Boolean sent) {
        this.sent = sent;
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




}


