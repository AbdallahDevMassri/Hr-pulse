package com.example.hrpulse.Services.Database;

import com.example.hrpulse.Services.Hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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

    public static void saveToDatabase(String tableName, List<String[]> csvData) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            for (String[] rowData : csvData) {
                String insertQuery = "INSERT INTO " + tableName +
                        " (total_work_hours, break_time, end_of_shift, start_of_shift, date, employee_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

                // Creating a query object
                Query query = session.createNativeQuery(insertQuery);

                // Setting parameters for the query based on rowData
                query.setParameter(1, rowData[0]);
                query.setParameter(2, rowData[1]);
                query.setParameter(3, rowData[2]);
                query.setParameter(4, rowData[3]);
                query.setParameter(5, rowData[4]);
                query.setParameter(6, rowData[5]);

                // Execute the query
                query.executeUpdate();
            }

            transaction.commit();
        }
    }


}
