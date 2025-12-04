package com.lab6.dao;

import com.lab6.model.Seller;
import com.lab6.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SellerDAO {
	public void add(Seller seller) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.persist(seller);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
	public void update(Seller seller) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.merge(seller);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
	public void delete(int id) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			Seller seller = session.get(Seller.class, id);
			if (seller != null) session.remove(seller);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
	public Seller getById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Seller.class, id);
		}
	}
	
	public List<Seller> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Seller", Seller.class).getResultList();
		}
	}
	
	public Object[] getTop() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"SELECT sale.seller, SUM(sale.product.price) AS total FROM Sale sale GROUP BY sale.seller ORDER BY total DESC",
					Object[].class).setMaxResults(1).uniqueResult();
		}
	}
}
