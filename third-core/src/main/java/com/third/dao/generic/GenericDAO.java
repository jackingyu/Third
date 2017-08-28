package com.third.dao.generic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.third.dao.util.PaginationSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;


@SuppressWarnings("unchecked")
public class GenericDAO<T, ID extends Serializable> extends HibernateDaoSupport implements IGenericDAO<T, ID>
{

	private Log logger = LogFactory.getLog(getClass());

	protected SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	protected Class<T> entityClass;

	public GenericDAO()
	{
	}

	protected Class getEntityClass()
	{
		if (entityClass == null)
		{
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			logger.debug("T class = " + entityClass.getName());
		}
		return entityClass;
	}

	public void saveOrUpdate(T t) throws DataAccessException
	{
		this.currentSession().clear();
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	public T load(ID id) throws DataAccessException
	{
		return (T) getHibernateTemplate().load(getEntityClass(), id);
	}

	public T get(ID id) throws DataAccessException
	{
		return (T) getHibernateTemplate().get(getEntityClass(), id);
	}

	public boolean contains(T t) throws DataAccessException
	{
		return getHibernateTemplate().contains(t);
	}

	public void delete(T t, LockMode lockMode) throws DataAccessException
	{
		getHibernateTemplate().delete(t, lockMode);
	}

	public void delete(T t) throws DataAccessException
	{
		getHibernateTemplate().delete(t);
	}

	public void deleteAll(Collection<T> entities) throws DataAccessException
	{
		getHibernateTemplate().deleteAll(entities);
	}

	public List<T> find(String queryString, Object value) throws DataAccessException
	{
		return (List<T>) getHibernateTemplate().find(queryString, value);
	}

	public List<T> find(String queryString, Object[] values) throws DataAccessException
	{
		return (List<T>) getHibernateTemplate().find(queryString, values);
	}

	public List<T> findByCriteria(DetachedCriteria criteria) throws DataAccessException
	{
		return (List<T>) getHibernateTemplate().findByCriteria(criteria);
	}

	public List<T> findByExample(T exampleEntity) throws DataAccessException
	{
		return getHibernateTemplate().findByExample(exampleEntity);
	}

	public List<T> findByNamedParam(String queryString, String[] paramNames, Object[] values) throws DataAccessException
	{
		return (List<T>) getHibernateTemplate().findByNamedParam(queryString, paramNames, values);
	}

	public List<T> find(String queryString) throws DataAccessException
	{
		return (List<T>) getHibernateTemplate().find(queryString);
	}

	public void refresh(T t, LockMode lockMode) throws DataAccessException
	{
		getHibernateTemplate().refresh(t, lockMode);
	}

	public void refresh(T t) throws DataAccessException
	{
		getHibernateTemplate().refresh(t);
	}

	public Serializable save(T t) throws DataAccessException
	{
		return getHibernateTemplate().save(t);
	}

	public void saveOrUpdateAll(Collection<T> entities) throws DataAccessException
	{

		getHibernateTemplate().saveOrUpdate(entities);

	}

	public void update(T t, LockMode lockMode) throws DataAccessException
	{
		getHibernateTemplate().update(t, lockMode);
	}

	public void update(T t) throws DataAccessException
	{
		getHibernateTemplate().update(t);
	}

	public List<T> list() throws DataAccessException
	{
		return getHibernateTemplate().loadAll(getEntityClass());

	}

	public List<T> findByNamedQuery(String queryName) throws DataAccessException
	{
		return (List<T>) getHibernateTemplate().findByNamedQuery(queryName);
	}

	public List<T> findByNamedQuery(String queryName, Object value) throws DataAccessException
	{
		List<T> result = (List<T>) getHibernateTemplate().findByNamedQuery(queryName, value);
		return result;
	}

	public List<T> findByNamedQuery(String queryName, Object[] values) throws DataAccessException
	{
		return (List<T>) getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int startIndex)
	{
		return (PaginationSupport) getHibernateTemplate().executeWithNativeSession(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException
			{
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				int totalCount = Integer.valueOf(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
				criteria.setProjection(null);
				List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
				return new PaginationSupport(items, totalCount, pageSize, startIndex);
			}
		});
	}

	public PaginationSupport findPageByQuery(final String hsql, final int pageSize, final int startIndex)
	{
		return (PaginationSupport) getHibernateTemplate().execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException
			{
				//get total count
				String countsql = null;
				if(hsql.contains("select"))
				{
					String[] sql1 = hsql.split("from");
					countsql = "select count(*) from "+sql1[1];
				}else
					countsql = "select count(*) from "+hsql;
				
				Query countquery = session.createQuery(countsql);
				Long totalCount = (Long) countquery.list().get(0);
				
				//get result
				Query query = session.createQuery(hsql);
				query.setFirstResult(startIndex);
				query.setMaxResults(pageSize);
				List items = query.list();
			
				return new PaginationSupport(items, totalCount.intValue(), pageSize, startIndex);

			}
		});
	}

	public void merge(T t) throws DataAccessException
	{
		this.getHibernateTemplate().merge(t);
		this.getHibernateTemplate().flush();
	}

	public List<T> saveOrUpdateList(final List<T> entites)
	{
		return (List<T>) this.getHibernateTemplate().execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException
			{
				for (int i = 0; i < entites.size(); i++)
				{
					session.saveOrUpdate(entites.get(i));
					session.flush();
					session.clear();
				}

				return entites;
			}
		});
	}

	public List searchByQuery(String queryString)
	{
		Query query = this.currentSession().createQuery(queryString);
		return query.list();
	}

	public Integer countByQuery(String queryString)
	{
		Integer count = ((Number) this.currentSession().createQuery(queryString).uniqueResult()).intValue();
		return count;
	}

	public List searchBySQL(String sql)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired
	public void setSessionFactoryOverride(SessionFactory sessionFactory)
	{

		super.setSessionFactory(sessionFactory);
	}

	protected String generateLikeParameter(final String parameter)
	{
		return "%" + parameter + "%";
	}

}
