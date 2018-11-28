package com.third.dao.user.impl;

import com.third.dao.generic.GenericDAO;
import com.third.dao.user.MenuDao;
import com.third.model.MenuModel;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultTemplateDao extends GenericDAO<MenuModel, String>
		implements MenuDao {

}
