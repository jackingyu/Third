package com.third.dao.customer.impl;

import com.third.dao.customer.SubscribeDao;
import com.third.dao.generic.GenericDAO;
import com.third.model.SubscribeModel;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultSubscribeDao extends GenericDAO<SubscribeModel, String>
		implements SubscribeDao {

}
