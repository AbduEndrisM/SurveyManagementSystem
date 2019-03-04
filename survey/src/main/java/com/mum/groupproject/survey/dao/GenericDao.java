package com.mum.groupproject.survey.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDao<T extends Serializable> {

	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> type;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDao() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public T create(final T t) {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.save(t);
		s.getTransaction().commit();
		s.close();
		return t;
	}

	public void update(final T t) {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.update(t);
		s.getTransaction().commit();
		s.close();
	}

	public String delete(final T t) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			s.delete(s.merge(t));
			s.getTransaction().commit();
			s.close();
			return "well";
		} catch (Exception e) {
			Logger log = Logger.getAnonymousLogger();
			log.log(Level.SEVERE, e.getLocalizedMessage());
			return "no";
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> allObejcts() {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		List<T> list = s.createCriteria(this.type).list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	public T findOne(String id) {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		T t = s.get(this.type, id);
		s.getTransaction().commit();
		s.close();
		return t;

	}

}
