package com.example.hrpulse.Services.Database;

import com.example.hrpulse.Services.CSV.CsvRow;
import com.example.hrpulse.Services.Hibernate.HibernateUtil;
import com.example.hrpulse.Services.Interfaces.DataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static void saveCSVDataToDatabase(String tableName, List<String[]> csvData) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                csvData.forEach(rowData -> {
                    String employeeId = rowData[5];
                    String date = rowData[4];
                    if (!isDataExists(sessionFactory, tableName, employeeId, date)) {
                        String insertQuery = generateInsertQuery(tableName);
                        Query query = session.createNativeQuery(insertQuery);
                        setQueryParameters(query, rowData, query.getComment());
                        query.executeUpdate();
                    }
                });
                transaction.commit();
            } catch (HibernateException e) {
                handleDatabaseException(transaction, e);
            }
        }
    }


    public static void deleteRowFromDatabase(CsvRow row) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                String deleteQuery = "DELETE FROM employeeshiftdata WHERE employee_id = :employee_id";
                Query<?> query = session.createNativeQuery(deleteQuery);
                query.setParameter("employee_id", row.getEmployeeId());
                query.executeUpdate();

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }


    public static boolean isDataExists(SessionFactory sessionFactory, String tableName, String employeeId, String date) {
        try (Session session = sessionFactory.openSession()) {
            String checkExistenceQuery = "SELECT 1 FROM " + tableName + " WHERE employee_id = :employeeId AND date = :date";
            Query query = session.createNativeQuery(checkExistenceQuery);
            query.setParameter("employeeId", employeeId);
            query.setParameter("date", date);
            return query.uniqueResult() != null;
        } catch (HibernateException e) {
            handleHibernateException(e);
            return false;
        }
    }


    public static void insertRowIntoDatabase(CsvRow row) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String insertQuery = generateInsertQuery("employeeshiftdata");
            Query query = session.createNativeQuery(insertQuery);
            setQueryParameters(query, row.toStringArray(), row.getComments());
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            handleDatabaseException(transaction, e);
        }
    }


    private static String generateInsertQuery(String tableName) {
        return "INSERT INTO " + tableName +
                " (total_work_hours, break_time, end_of_shift, start_of_shift, date, employee_id, comments) " +
                "VALUES (:totalWorkHours, :breakTime, :exitHour, :startHour, :dateTable, :employeeId, :comments)";
    }

    public static void setQueryParameters(Query query, String[] rowData, String comments) {
        query.setParameter("totalWorkHours", rowData[0]);
        query.setParameter("breakTime", rowData[1]);
        query.setParameter("exitHour", rowData[2]);
        query.setParameter("startHour", rowData[3]);
        query.setParameter("dateTable", rowData[4]);
        query.setParameter("employeeId", rowData[5]);
        query.setParameter("comments", comments);

    }

    public static void handleDatabaseException(Transaction transaction, HibernateException e) {
        handleTransactionException(transaction, e);
    }

    private static void handleTransactionException(Transaction transaction, Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        handleHibernateException(e);
    }

    public static void handleHibernateException(Exception e) {
        e.printStackTrace(); // Log or handle the exception appropriately
    }

    public static boolean isDatabaseEmpty(String tableName) {
        try (Session session = sessionFactory.openSession()) {
            String countQuery = "SELECT COUNT(*) FROM " + tableName;
            Query<Number> query = session.createNativeQuery(countQuery);
            Number count = query.getSingleResult();
            return count.longValue() == 0L;
        }
    }

    public static <T extends DataModel> List<T> loadLastSavedData(String tableName, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            String selectQuery = "SELECT total_work_hours, break_time, end_of_shift, start_of_shift, date, employee_id, comments " +
                    "FROM " + tableName + " ORDER BY total_work_hours DESC, break_time DESC, " +
                    "end_of_shift DESC, start_of_shift DESC, date DESC, employee_id DESC";
            Query<Object[]> query = session.createNativeQuery(selectQuery);
            List<Object[]> resultList = query.getResultList();
            return convertToObjectList(resultList, clazz);
        }
    }

    public static <T extends DataModel> List<T> loadLastSavedDataByEmployeeId(String tableName, Class<T> clazz, String employeeId) {
        try (Session session = sessionFactory.openSession()) {
            String selectQuery = "SELECT total_work_hours, break_time, end_of_shift, start_of_shift, date, employee_id, comments " +
                    "FROM " + tableName + " WHERE employee_id = :employeeId " +
                    "ORDER BY total_work_hours DESC, break_time DESC, " +
                    "end_of_shift DESC, start_of_shift DESC, date DESC, employee_id DESC";
            Query<Object[]> query = session.createNativeQuery(selectQuery);
            query.setParameter("employeeId", employeeId);
            List<Object[]> resultList = query.getResultList();
            return convertToObjectList(resultList, clazz);
        }
    }

    private static <T extends DataModel> List<T> convertToObjectList(List<Object[]> resultList, Class<T> clazz) {
        return resultList.stream()
                .map(row -> {
                    try {
                        T instance = clazz.getDeclaredConstructor().newInstance();
                        instance.initializeFromCsvData(Arrays.copyOf(row, row.length, String[].class));
                        return instance;
                    } catch (Exception e) {
                        throw new RuntimeException("Error creating instance of " + clazz.getSimpleName(), e);
                    }
                })
                .collect(Collectors.toList());
    }
}
