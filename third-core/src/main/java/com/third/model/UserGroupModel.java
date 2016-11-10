package com.third.model;
// Generated Nov 10, 2016 4:52:00 PM by Hibernate Tools 4.3.1


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * UserGroupModel generated by hbm2java
 */
public class UserGroupModel  implements java.io.Serializable {


     private String pk;
     private String groupId;
     private String name;
     private Set users = new HashSet(0);
     private Collection roles = new ArrayList(0);

    public UserGroupModel() {
    }

    public UserGroupModel(String groupId, String name, Set users, Collection roles) {
       this.groupId = groupId;
       this.name = name;
       this.users = users;
       this.roles = roles;
    }
   
    public String getPk() {
        return this.pk;
    }
    
    public void setPk(String pk) {
        this.pk = pk;
    }
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getUsers() {
        return this.users;
    }
    
    public void setUsers(Set users) {
        this.users = users;
    }
    public Collection getRoles() {
        return this.roles;
    }
    
    public void setRoles(Collection roles) {
        this.roles = roles;
    }




}


