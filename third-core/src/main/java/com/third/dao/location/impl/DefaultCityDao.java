package com.third.dao.location.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import com.third.dao.generic.GenericDAO;
import com.third.dao.location.CityDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.CityModel;


public class DefaultCityDao extends GenericDAO<CityModel, String> implements CityDao
{


}
