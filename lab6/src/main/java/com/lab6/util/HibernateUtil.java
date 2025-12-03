package com.lab6.util;

import com.lab6.model.Client;
import com.lab6.model.Product;
import com.lab6.model.Sale;
import com.lab6.model.Seller;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
				configuration.addAnnotatedClass(Client.class);
				configuration.addAnnotatedClass(Product.class);
				configuration.addAnnotatedClass(Sale.class);
				configuration.addAnnotatedClass(Seller.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("Hibernate Java Config serviceRegistry created");
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				
				return sessionFactory;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sessionFactory;
	}
}
