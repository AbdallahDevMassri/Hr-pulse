package com.example.hrpulse.Services.Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class ShiftDataService {
    private SessionFactory sessionFactory;  // Initialize this with your Hibernate session factory

    public ShiftData saveShiftData(ShiftData shiftData) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(shiftData);
            tx.commit();
            return shiftData;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public ShiftData updateShiftData(ShiftData shiftData) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(shiftData);
            tx.commit();
            return shiftData;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void deleteShiftData(ShiftData shiftData) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(shiftData);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

