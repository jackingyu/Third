package com.third.dao.product.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.third.dao.generic.GenericDAO;
import com.third.dao.product.CategoryDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.CategoryModel;


public class DefaultCategoryDao extends GenericDAO<CategoryModel, String> implements CategoryDao
{
	

}
