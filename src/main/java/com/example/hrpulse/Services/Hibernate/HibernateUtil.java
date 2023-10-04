package com.example.hrpulse.Services.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure();

            // Explicitly add the Employee and BankInfo classes
            configuration.addAnnotatedClass(com.example.hrpulse.Services.Objects.Employee.class);
            configuration.addAnnotatedClass(com.example.hrpulse.Services.Objects.BankInfo.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
