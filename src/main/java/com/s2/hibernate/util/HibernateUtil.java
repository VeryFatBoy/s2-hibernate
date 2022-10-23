package com.s2.hibernate.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.s2.hibernate.model.Tick;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				Properties settings = new Properties();

				settings.put(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(AvailableSettings.URL, "jdbc:mysql://<host>:3306/timeseries_db?createDatabaseIfNotExist=true");
				settings.put(AvailableSettings.USER, "admin");
				settings.put(AvailableSettings.PASS, "<password>");
				settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				settings.put(AvailableSettings.SHOW_SQL, "true");
				settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(AvailableSettings.HBM2DDL_AUTO, "create-drop");

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Tick.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
