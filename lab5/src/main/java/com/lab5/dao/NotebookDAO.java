package com.lab5.dao;

import com.lab5.model.Notebook;
import com.lab5.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class NotebookDAO {
	public void generateDemoData() {
		addNotebook(new Notebook("Moleskine", "Classic", 240, "тверда", "Італія", 50000, "клітинка"));
		addNotebook(new Notebook("Brunnen", "CollegeBlock A4", 80, "м’яка", "Німеччина", 200000, "лінійка"));
		addNotebook(new Notebook("Oxford", "Student 96", 96, "тверда", "Франція", 80000, "порожня"));
		addNotebook(new Notebook("Factis", "SoftLine 48", 48, "м’яка", "Іспанія", 30000, "клітинка"));
		addNotebook(new Notebook("YES", "UA Series 60", 60, "тверда", "Україна", 70000, "лінійка"));
	}
	
	// -------- CRUD (вже є у тебе) --------
	/**
	 * Додати новий блокнот
	 */
	public void addNotebook(Notebook notebook) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(notebook);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			throw e;
		}
	}
	
	/**
	 * Редагувати блокнот
	 */
	public void updateNotebook(Notebook notebook) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(notebook);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			throw e;
		}
	}
	
	/**
	 * Видалити блокнот
	 */
	public void deleteNotebook(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Notebook notebook = session.get(Notebook.class, id);
			if (notebook != null) session.remove(notebook);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			throw e;
		}
	}
	
	/**
	 * Отримати блокнот по Id
	 */
	public Notebook getNotebook(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Notebook.class, id);
		}
	}
	
	/**
	 * Отримати всі блокноти
	 */
	public List<Notebook> getAllNotebook() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Notebook", Notebook.class).getResultList();
		}
	}
	
	// -------- Звіти та фільтри --------
	/**
	 * Отримати країни з кількістю блокнотів
	 */
	public List<Object[]> getCountriesWithCount() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Object[]> q = session.createQuery(
					"SELECT n.country, COUNT(n) FROM Notebook n GROUP BY n.country ORDER BY COUNT(n) DESC",
					Object[].class);
			return q.getResultList();
		}
	}
	
	/**
	 * Отримати виробників з кількістю блокнотів
	 */
	public List<Object[]> getManufacturersWithCount() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Object[]> q = session.createQuery(
					"SELECT n.manufacturer, COUNT(n) FROM Notebook n GROUP BY n.manufacturer ORDER BY COUNT(n) DESC",
					Object[].class);
			return q.getResultList();
		}
	}
	
	/**
	 * Отримати країну з максимальною кількістю блокнотів
	 */
	public Optional<Object[]> getCountryMax() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Object[] r = session.createQuery(
							"SELECT n.country, COUNT(n) FROM Notebook n GROUP BY n.country ORDER BY COUNT(n) DESC",
							Object[].class)
					.setMaxResults(1)
					.uniqueResult();
			return Optional.ofNullable(r);
		}
	}
	
	/**
	 * Отримати країну з мінімальною кількістю блокнотів
	 */
	public Optional<Object[]> getCountryMin() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Object[] r = session.createQuery(
							"SELECT n.country, COUNT(n) FROM Notebook n GROUP BY n.country ORDER BY COUNT(n) ASC",
							Object[].class)
					.setMaxResults(1)
					.uniqueResult();
			return Optional.ofNullable(r);
		}
	}
	
	/**
	 * Отримати виробника з максимальною кількістю блокнотів
	 */
	public Optional<Object[]> getManufacturerMax() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Object[] r = session.createQuery(
							"SELECT n.manufacturer, COUNT(n) FROM Notebook n GROUP BY n.manufacturer ORDER BY COUNT(n) DESC",
							Object[].class)
					.setMaxResults(1)
					.uniqueResult();
			return Optional.ofNullable(r);
		}
	}
	
	/**
	 * Отримати виробника з мінімальною кількістю блокнотів
	 */
	public Optional<Object[]> getManufacturerMin() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Object[] r = session.createQuery(
							"SELECT n.manufacturer, COUNT(n) FROM Notebook n GROUP BY n.manufacturer ORDER BY COUNT(n) ASC",
							Object[].class)
					.setMaxResults(1)
					.uniqueResult();
			return Optional.ofNullable(r);
		}
	}
	
	/**
	 * Отримати блокноти з м'якою обкладинкою
	 */
	public List<Notebook> getHardCover() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Notebook n WHERE n.coverType = :t", Notebook.class)
					.setParameter("t", "тверда")
					.getResultList();
		}
	}
	
	/**
	 * Отримати блокноти з м'якою обкладинкою
	 */
	public List<Notebook> getSoftCover() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Notebook n WHERE n.coverType = :t", Notebook.class)
					.setParameter("t", "м’яка")
					.getResultList();
		}
	}
	
	/**
	 * Отримати блокноти за країною виробником
	 */
	public List<Notebook> getByCountry(String country) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Notebook n WHERE n.country = :c", Notebook.class)
					.setParameter("c", country)
					.getResultList();
		}
	}
	
	/**
	 * Фільтрація за стилем сторінок
	 */
	public List<Notebook> filterByPageStyle(String style) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Notebook n WHERE n.pageStyle = :s", Notebook.class)
					.setParameter("s", style)
					.getResultList();
		}
	}
	
	/**
	 * Фільтрація за кількістю сторінок
	 */
	public List<Notebook> filterByPages(int min, int max) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Notebook n WHERE n.pages BETWEEN :min AND :max", Notebook.class)
					.setParameter("min", min)
					.setParameter("max", max)
					.getResultList();
		}
	}
	
	/**
	 * Фільтрація за тиражем
	 */
	public List<Notebook> filterByCirculation(int min, int max) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Notebook n WHERE n.circulation BETWEEN :min AND :max", Notebook.class)
					.setParameter("min", min)
					.setParameter("max", max)
					.getResultList();
		}
	}
}