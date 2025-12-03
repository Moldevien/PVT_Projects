package com.lab6.dao;

import com.lab6.model.Sale;
import com.lab6.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class SaleDAO {
	public void add(Sale sale) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.persist(sale);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
	public void update(Sale sale) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.merge(sale);
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
			Sale s = session.get(Sale.class, id);
			if (s != null) session.remove(s);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
	public Sale getById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Sale.class, id);
		}
	}
	
	public List<Sale> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Sale s ORDER BY s.saleDate DESC", Sale.class).getResultList();
		}
	}
	
	public List<Sale> getByDate(LocalDate date) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Sale> q = session.createQuery("FROM Sale s WHERE s.saleDate = :d ORDER BY s.saleDate DESC", Sale.class);
			q.setParameter("d", date);
			return q.getResultList();
		}
	}
	
	public List<Sale> getBetweenDates(LocalDate start, LocalDate end) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Sale> q = session.createQuery("FROM Sale s WHERE s.saleDate BETWEEN :st AND :en ORDER BY s.saleDate DESC", Sale.class);
			q.setParameter("st", start);
			q.setParameter("en", end);
			return q.getResultList();
		}
	}
	
	public List<Sale> getBySeller(int sellerId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Sale> q = session.createQuery("FROM Sale s WHERE s.seller.id = :id ORDER BY s.saleDate DESC", Sale.class);
			q.setParameter("id", sellerId);
			return q.getResultList();
		}
	}
	
	public List<Sale> getByClient(int buyerId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Sale> q = session.createQuery("FROM Sale s WHERE s.client.id = :id ORDER BY s.saleDate DESC", Sale.class);
			q.setParameter("id", buyerId);
			return q.getResultList();
		}
	}
	
	public Object[] getTopSeller() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"SELECT s.seller, SUM(s.product.price) AS total FROM Sale s GROUP BY s.seller ORDER BY total DESC",
					Object[].class).setMaxResults(1).uniqueResult();
		}
	}
	
	public Object[] getTopClient() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"SELECT s.client, SUM(s.product.price) AS total FROM Sale s GROUP BY s.client ORDER BY total DESC",
					Object[].class).setMaxResults(1).uniqueResult();
		}
	}
	
	public Double getAveragePurchase() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("SELECT AVG(s.product.price) FROM Sale s", Double.class).getSingleResult();
		}
	}
	
	public Object[] getMostPopularProduct() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"SELECT s.product, COUNT(s.product) AS count FROM Sale s GROUP BY s.product ORDER BY count DESC",
					Object[].class).setMaxResults(1).uniqueResult();
		}
	}
}