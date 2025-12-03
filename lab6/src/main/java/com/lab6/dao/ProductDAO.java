package com.lab6.dao;

import com.lab6.model.Product;
import com.lab6.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAO {
	public void add(Product product) {
		Transaction tx = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			tx = s.beginTransaction();
			s.persist(product);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
	public void update(Product product) {
		Transaction tx = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			tx = s.beginTransaction();
			s.merge(product);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
	public void delete(int id) {
		Transaction tx = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			tx = s.beginTransaction();
			Product product = s.get(Product.class, id);
			if (product != null) s.remove(product);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
	public Product getById(int id) {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			return s.get(Product.class, id);
		}
	}
	
	public List<Product> getAll() {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			return s.createQuery("FROM Product", Product.class).getResultList();
		}
	}
	
	public Object[] getMostPopular() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"SELECT s.product, COUNT(s.product) AS count FROM Sale s GROUP BY s.product ORDER BY count DESC",
					Object[].class).setMaxResults(1).uniqueResult();
		}
	}
}
