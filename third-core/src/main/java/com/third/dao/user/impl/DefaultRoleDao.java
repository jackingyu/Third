package com.third.dao.user.impl;

import com.third.dao.generic.GenericDAO;
import com.third.dao.user.RoleDao;
import com.third.model.RoleModel;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultRoleDao extends GenericDAO<RoleModel, String>
		implements RoleDao {

}
