package com.third.dao.customer.impl;

import com.third.dao.customer.WeixinInfoDao;
import com.third.dao.generic.GenericDAO;
import com.third.model.WeixinInfoModel;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultWeixinInfoDao extends GenericDAO<WeixinInfoModel, Integer>
		implements WeixinInfoDao {

}
