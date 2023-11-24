package com.example.hrpulse.Services.Database;

import com.example.hrpulse.Services.Objects.Department;
import com.example.hrpulse.Services.Objects.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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
    public Department getDepartmentById(int departmentId) {
        try (Session session = sessionFactory.openSession()) {
            // Open a new session

            // Retrieve the department using its ID
            Department department = session.get(Department.class, departmentId);

            // You now have the department object
            return department;
        }
    }

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

    // Add more methods for other database operations if needed
}
