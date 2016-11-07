package com.third.dao.store.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import com.third.dao.generic.GenericDAO;
import com.third.dao.store.StoreDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.StoreModel;


public class DefaultStoreDao extends GenericDAO<StoreModel, String> implements StoreDao
{


}
