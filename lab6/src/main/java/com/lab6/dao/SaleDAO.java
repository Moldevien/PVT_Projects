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
			Sale sale = session.get(Sale.class, id);
			if (sale != null) session.remove(sale);
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
			return session.createQuery("FROM Sale sale ORDER BY sale.saleDate DESC", Sale.class).getResultList();
		}
	}
	
	public List<Sale> getByDate(LocalDate date) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Sale> q = session.createQuery("FROM Sale sale WHERE sale.saleDate = :d ORDER BY sale.saleDate DESC", Sale.class);
			q.setParameter("d", date);
			return q.getResultList();
		}
	}
	
	public List<Sale> getBetweenDates(LocalDate start, LocalDate end) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Sale> q = session.createQuery("FROM Sale sale WHERE sale.saleDate BETWEEN :st AND :en ORDER BY sale.saleDate DESC", Sale.class);
			q.setParameter("st", start);
			q.setParameter("en", end);
			return q.getResultList();
		}
	}
	
	public List<Sale> getBySeller(int sellerId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Sale> q = session.createQuery("FROM Sale sale WHERE sale.seller.id = :id ORDER BY sale.saleDate DESC", Sale.class);
			q.setParameter("id", sellerId);
			return q.getResultList();
		}
	}
	
	public List<Sale> getByClient(int buyerId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Sale> q = session.createQuery("FROM Sale sale WHERE sale.client.id = :id ORDER BY sale.saleDate DESC", Sale.class);
			q.setParameter("id", buyerId);
			return q.getResultList();
		}
	}
	
	public Object[] getTopSeller() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"SELECT sale.seller, SUM(sale.product.price) AS total FROM Sale sale GROUP BY sale.seller ORDER BY total DESC",
					Object[].class).setMaxResults(1).uniqueResult();
		}
	}
	
	public Object[] getTopClient() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"SELECT sale.client, SUM(sale.product.price) AS total FROM Sale sale GROUP BY sale.client ORDER BY total DESC",
					Object[].class).setMaxResults(1).uniqueResult();
		}
	}
	
	public Double getAveragePurchase() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("SELECT AVG(sale.product.price) FROM Sale sale", Double.class).getSingleResult();
		}
	}
	
	public Object[] getMostPopularProduct() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"SELECT sale.product, COUNT(sale.product) AS count FROM Sale sale GROUP BY sale.product ORDER BY count DESC",
					Object[].class).setMaxResults(1).uniqueResult();
		}
	}
}