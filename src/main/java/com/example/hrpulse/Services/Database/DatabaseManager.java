package com.example.hrpulse.Services.Database;

import com.example.hrpulse.Services.Hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

public class DatabaseManager {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
