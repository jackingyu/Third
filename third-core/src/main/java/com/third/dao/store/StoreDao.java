package com.third.dao.store;

import java.util.List;

import com.third.dao.generic.IGenericDAO;
import com.third.model.CustomerModel;
import com.third.model.MenuModel;
import com.third.model.StoreModel;

public interface StoreDao extends IGenericDAO<StoreModel, String> {

	List<StoreModel> findStoreByName(final String name);

	StoreModel getStoreForCode(final String code);

}
