package com.third.dao.location.impl;

import com.third.dao.generic.GenericDAO;
import com.third.dao.location.DistrictDao;
import com.third.model.DistrictModel;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultDistrictDao extends GenericDAO<DistrictModel, String> implements DistrictDao{

}
