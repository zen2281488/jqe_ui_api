package utils.db;

import models.db.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .configure()
                        .setProperty("hibernate.connection.username", System.getenv("DB_USER"))
                        .setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"));
                configuration.addAnnotatedClass(Car.class);
                configuration.addAnnotatedClass(House.class);
                configuration.addAnnotatedClass(ParkingPlace.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(EngineType.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());

                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                throw new RuntimeException("Ошибка инициализации Hibernate SessionFactory", e);
            }
        }
        return sessionFactory;
    }
}
