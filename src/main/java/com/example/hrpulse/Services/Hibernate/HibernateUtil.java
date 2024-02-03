package com.example.hrpulse.Services.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The HibernateUtil class is responsible for managing the Hibernate SessionFactory.
 */
public class HibernateUtil {

    // The Hibernate SessionFactory instance.
    private static SessionFactory sessionFactory;

    static {
        try {
            // Load configuration from hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();

            // Explicitly add annotated classes to the configuration
            configuration.addAnnotatedClass(com.example.hrpulse.Services.Objects.Employee.class);
            configuration.addAnnotatedClass(com.example.hrpulse.Services.Objects.BankInfo.class);
            configuration.addAnnotatedClass(com.example.hrpulse.Services.Objects.Department.class);

            // Build the SessionFactory
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            // Log any initialization error and throw an exception
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retrieves the Hibernate SessionFactory.
     *
     * @return The SessionFactory instance.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
