package com.example.hrpulse.Services.Database;

import com.example.hrpulse.Services.Objects.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DatabaseSessionManager {
    private final SessionFactory sessionFactory;

    public DatabaseSessionManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean saveEmployee(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.save(employee);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

        return false;
    }

    // Add more methods for other database operations if needed
}
