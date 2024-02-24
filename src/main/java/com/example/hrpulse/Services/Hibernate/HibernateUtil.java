package com.example.hrpulse.Services.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

<<<<<<< HEAD
/**
 * The HibernateUtil class is responsible for managing the Hibernate SessionFactory.
 */
public class HibernateUtil {

    // The Hibernate SessionFactory instance.
=======
public class HibernateUtil {

>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
    private static SessionFactory sessionFactory;

    static {
        try {
<<<<<<< HEAD
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
=======
            Configuration configuration = new Configuration().configure();

            // Explicitly add the Employee and BankInfo classes
            configuration.addAnnotatedClass(com.example.hrpulse.Services.Objects.Employee.class);
            configuration.addAnnotatedClass(com.example.hrpulse.Services.Objects.BankInfo.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

<<<<<<< HEAD
    /**
     * Retrieves the Hibernate SessionFactory.
     *
     * @return The SessionFactory instance.
     */
=======
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

<<<<<<< HEAD

=======
    public static void shutdown() {
        getSessionFactory().close();
    }
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
}
