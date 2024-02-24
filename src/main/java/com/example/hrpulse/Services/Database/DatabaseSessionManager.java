package com.example.hrpulse.Services.Database;

import com.example.hrpulse.Services.Objects.Department;
import com.example.hrpulse.Services.Objects.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * The DatabaseSessionManager class manages database sessions for saving, updating, and removing Employee and Department entities.
 */
public class DatabaseSessionManager {

    // The Hibernate SessionFactory, responsible for managing database sessions.
    private final SessionFactory sessionFactory;

    /**
     * Constructs a DatabaseSessionManager with the provided SessionFactory.
     *
     * @param sessionFactory The Hibernate SessionFactory.
     */
    public DatabaseSessionManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Saves an Employee entity to the database.
     *
     * @param employee The Employee entity to be saved.
     * @return True if the operation is successful, false otherwise.
     */
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

    /**
     * Saves a Department entity to the database.
     *
     * @param department The Department entity to be saved.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean saveDepartment(Department department) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.save(department);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Updates a Department entity in the database.
     *
     * @param department The Department entity to be updated.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean updateDepartment(Department department) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.update(department);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Removes a Department entity from the database.
     *
     * @param department The Department entity to be removed.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean removeDepartment(Department department) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.delete(department);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Updates an Employee entity in the database.
     *
     * @param selectedEmployee The Employee entity to be updated.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean updateEmployee(Employee selectedEmployee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.update(selectedEmployee);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Removes an Employee entity from the database.
     *
     * @param employee The Employee entity to be removed.
     * @return True if the operation is successful, false otherwise.
     */
    public boolean removeEmployee(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.delete(employee);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

        return false;
    }
}
