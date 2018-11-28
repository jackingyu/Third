package com.third.dao.store.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.third.dao.generic.GenericDAO;
import com.third.dao.store.StoreDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.StoreModel;
import com.third.model.UserModel;

@Repository
public class DefaultStoreDao extends GenericDAO<StoreModel, String>
        implements StoreDao {
    private final static String FIND_BY_CODE_SQL = "from com.third.model.StoreModel s where s.id=?";

    @Override
    public List<StoreModel> findStoreByName(String name) {
        if (StringUtils.isEmpty(name))
            return this.list();

        DetachedCriteria dcStore = DetachedCriteria.forClass(StoreModel.class);

        dcStore.add(Restrictions.like("name", generateLikeParameter(name)));

        return this.findByCriteria(dcStore);

    }

    @Override
    public StoreModel getStoreForCode(String code) {
        List<StoreModel> stores = this.find(FIND_BY_CODE_SQL, code);
        return CollectionUtils.isEmpty(stores) ? null : stores.get(0);
    }
}
