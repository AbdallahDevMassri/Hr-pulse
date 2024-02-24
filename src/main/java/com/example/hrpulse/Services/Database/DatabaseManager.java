package com.example.hrpulse.Services.Database;

import com.example.hrpulse.Services.Hibernate.HibernateUtil;
import com.example.hrpulse.Services.Interfaces.DataModel;
import com.example.hrpulse.Services.Objects.Department;
import com.example.hrpulse.Services.Objects.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The DatabaseManager class handles interactions with the database using Hibernate.
 * It provides methods for saving, deleting, and querying data in the database.
 */
public class DatabaseManager {

    // The Hibernate SessionFactory, responsible for managing database sessions.
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    /**
     * Gets the Hibernate SessionFactory instance.
     *
     * @return The SessionFactory instance.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Closes the Hibernate SessionFactory if it is open.
     */
    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }


    // Method to perform database operations after form submission for Employee
    public static void performDatabaseOperations(Employee employee) {
        DatabaseSessionManager sessionManager = new DatabaseSessionManager(DatabaseManager.getSessionFactory());

        // Save the employee to the database
        boolean saved = sessionManager.saveEmployee(employee);

        if (saved) {
            // Display confirmation
            System.out.println("Employee saved successfully.");
        } else {
            // Display error
            System.out.println("Error saving employee.");
        }
    }

    // Method to retrieve a list of employees from the database
    public static List<Employee> retrieveEmployees() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Create an HQL query to select all employees
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            List<Employee> employees = query.list();

            // Commit the transaction
            session.getTransaction().commit();
            System.out.println("Retrieved " + employees.size() + " employees.");

            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error retrieving employees: " + e.getMessage());
            return null;
        }
    }

    // Method to perform database operations after form submission for Department
    public static void performDatabaseOperations(Department department) {
        DatabaseSessionManager sessionManager = new DatabaseSessionManager(DatabaseManager.getSessionFactory());

        // Save the department to the database
        boolean saved = sessionManager.saveDepartment(department);

        if (saved) {
            // Display confirmation
            System.out.println("Departments saved successfully.");
        } else {
            // Display error
            System.out.println("Error saving Department.");
        }
    }

    // Method to perform database operations after form submission for Department (update or remove)
    public static void performDatabaseOperations(Department department, boolean update) {
        DatabaseSessionManager sessionManager = new DatabaseSessionManager(DatabaseManager.getSessionFactory());

        if (update) {
            // Update the department in the database
            boolean updated = sessionManager.updateDepartment(department);

            if (updated) {
                // Display confirmation
                System.out.println("Department updated successfully.");
            } else {
                // Display error
                System.out.println("Error updating Department.");
            }
        } else {
            // Remove the department from the database
            boolean removed = sessionManager.removeDepartment(department);

            if (removed) {
                // Display confirmation
                System.out.println("Department removed successfully.");
            } else {
                // Display error
                System.out.println("Error removing Department.");
            }
        }
    }

    // Method to retrieve a list of departments from the database
    public static List<Department> retrieveDepartments() {
        try (Session session = sessionFactory.openSession()) {
            // Begin a transaction
            session.beginTransaction();

            // Create an HQL query to select all departments
            Query<Department> query = session.createQuery("from Department", Department.class);

            // Execute the query and get the list of departments
            List<Department> departments = query.list();

            // Commit the transaction
            session.getTransaction().commit();

            return departments;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to perform database operations after form submission for Employee (update or remove)
    public static void employeePDO(Employee selectedEmployee, boolean update) {
        DatabaseSessionManager sessionManager = new DatabaseSessionManager(DatabaseManager.getSessionFactory());

        if (update) {
            // Update the employee in the database
            boolean updated = sessionManager.updateEmployee(selectedEmployee);

            if (updated) {
                // Display confirmation
                System.out.println("Employee updated successfully.");
            } else {
                // Display error
                System.out.println("Error updating employee.");
            }
        } else {
            // Remove the employee from the database
            boolean removed = sessionManager.removeEmployee(selectedEmployee);

            if (removed) {
                // Display confirmation
                System.out.println("Employee removed successfully.");
            } else {
                // Display error
                System.out.println("Error removing Employee.");
            }
        }
    }


    /**
     * Saves CSV data to the specified database table.
     *
     * @param tableName The name of the database table.
     * @param csvData   The CSV data to be saved.
     */
    public static void saveCSVDataToDatabase(String tableName, List<String[]> csvData) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                // Iterate through CSV data and insert new records if they don't exist
                for (String[] rowData : csvData) {
                    String employeeId = rowData[5];
                    String date = rowData[4];

                    if (!isDataExists(sessionFactory, tableName, employeeId, date)) {
                        String insertQuery = generateInsertQuery(tableName);
                        executeInsertQuery(session, insertQuery, rowData);
                    }
                }

                transaction.commit();
            } catch (HibernateException e) {
                handleDatabaseException(transaction, e);
            }
        }
    }

    /**
     * Executes an insert query with the provided data.
     *
     * @param session    The Hibernate session.
     * @param insertQuery The insert query.
     * @param rowData     The data to be inserted.
     */
    private static void executeInsertQuery(Session session, String insertQuery, String[] rowData) {
        try {
            Query query = session.createNativeQuery(insertQuery);

            // Set the parameters for the query
            query.setParameter("totalWorkHours", rowData[0]);
            query.setParameter("breakTime", rowData[1]);
            query.setParameter("exitHour", rowData[2]);
            query.setParameter("startHour", rowData[3]);
            query.setParameter("dateTable", rowData[4]);
            query.setParameter("employeeId", rowData[5]);

            query.executeUpdate();
        } catch (HibernateException e) {
            throw new RuntimeException("Error executing insert query", e);
        }
    }

    /**
     * Deletes a row from the specified database table.
     *
     * @param tableName   The name of the database table.
     * @param employeeId  The employee ID of the row to be deleted.
     * @param date        The date of the row to be deleted.
     */
    public static void deleteRowFromDatabase(String tableName, String employeeId, String date) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                String deleteQuery = "DELETE FROM " + tableName + " WHERE employee_id = :employee_id AND date = :date";
                Query<?> query = session.createNativeQuery(deleteQuery);
                query.setParameter("employee_id", employeeId);
                query.setParameter("date", date);

                query.executeUpdate();

                transaction.commit();
            } catch (Exception e) {
                handleTransactionException(transaction, e);
            }
        }
    }

    /**
     * Checks if data with the given employee ID and date exists in the specified table.
     *
     * @param sessionFactory The Hibernate SessionFactory.
     * @param tableName      The name of the database table.
     * @param employeeId     The employee ID to check.
     * @param date           The date to check.
     * @return True if data exists, false otherwise.
     */
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

    /**
     * Generates an insert query for the specified table.
     *
     * @param tableName The name of the database table.
     * @return The insert query.
     */
    private static String generateInsertQuery(String tableName) {
        return "INSERT INTO " + tableName +
                " (total_work_hours, break_time, end_of_shift, start_of_shift, date, employee_id) " +
                "VALUES (:totalWorkHours, :breakTime, :exitHour, :startHour, :dateTable, :employeeId)";
    }

    /**
     * Handles exceptions related to database operations.
     *
     * @param transaction The database transaction.
     * @param e            The exception.
     */
    public static void handleDatabaseException(Transaction transaction, HibernateException e) {
        handleTransactionException(transaction, e);
    }

    /**
     * Handles exceptions related to transactions.
     *
     * @param transaction The database transaction.
     * @param e            The exception.
     */
    private static void handleTransactionException(Transaction transaction, Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        handleHibernateException(e);
    }

    /**
     * Handles general Hibernate exceptions.
     *
     * @param e The exception.
     */
    public static void handleHibernateException(Exception e) {
        e.printStackTrace(); // Log or handle the exception appropriately
    }

    /**
     * Checks if the specified database table is empty.
     *
     * @param tableName The name of the database table.
     * @return True if the table is empty, false otherwise.
     */
    public static boolean isDatabaseEmpty(String tableName) {
        try (Session session = sessionFactory.openSession()) {
            String countQuery = "SELECT COUNT(*) FROM " + tableName;
            Query<Number> query = session.createNativeQuery(countQuery);
            Number count = query.getSingleResult();
            return count.longValue() == 0L;
        }
    }

    /**
     * Loads the last saved data from the specified table.
     *
     * @param tableName The name of the database table.
     * @param clazz     The class of the DataModel.
     * @param <T>       The type of the DataModel.
     * @return A list of DataModel objects representing the last saved data.
     */
    public static <T extends DataModel> List<T> loadLastSavedData(String tableName, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            String selectQuery = "SELECT total_work_hours, break_time, end_of_shift, start_of_shift, date, employee_id " +
                    "FROM " + tableName + " ORDER BY total_work_hours DESC, break_time DESC, " +
                    "end_of_shift DESC, start_of_shift DESC, date DESC, employee_id DESC";
            Query<Object[]> query = session.createNativeQuery(selectQuery);
            List<Object[]> resultList = query.getResultList();
            return convertToObjectList(resultList, clazz);
        }
    }

    /**
     * Loads the last saved data for a specific employee from the specified table.
     *
     * @param tableName  The name of the database table.
     * @param clazz      The class of the DataModel.
     * @param employeeId The employee ID to filter the data.
     * @param <T>        The type of the DataModel.
     * @return A list of DataModel objects representing the last saved data for the specified employee.
     */
    public static <T extends DataModel> List<T> loadLastSavedDataByEmployeeId(String tableName, Class<T> clazz, String employeeId) {
        try (Session session = sessionFactory.openSession()) {
            String selectQuery = "SELECT total_work_hours, break_time, end_of_shift, start_of_shift, date, employee_id" +
                    " FROM " + tableName + " WHERE employee_id = :employeeId " +
                    "ORDER BY total_work_hours DESC, break_time DESC, " +
                    "end_of_shift DESC, start_of_shift DESC, date DESC, employee_id DESC";
            Query<Object[]> query = session.createNativeQuery(selectQuery);
            query.setParameter("employeeId", employeeId);
            List<Object[]> resultList = query.getResultList();
            return convertToObjectList(resultList, clazz);
        }
    }

    /**
     * Converts a list of object arrays to a list of DataModel objects.
     *
     * @param resultList The list of object arrays.
     * @param clazz      The class of the DataModel.
     * @param <T>        The type of the DataModel.
     * @return A list of DataModel objects.
     */
    private static <T extends DataModel> List<T> convertToObjectList(List<Object[]> resultList, Class<T> clazz) {
        return resultList.stream()
                .map(row -> {
                    try {
                        // Create an instance of the DataModel and initialize it with CSV data
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
