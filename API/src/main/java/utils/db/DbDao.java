package utils.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DbDao {
    public void save(Object entity) {
        Transaction tx1 = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            session.save(entity);
            tx1.commit();
        } catch (HibernateException e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        }
    }

    public void update(Object entity) {
        Transaction tx1 = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            session.update(entity);
            tx1.commit();
        } catch (HibernateException e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        }
    }

    public void delete(Object entity) {
        Transaction tx1 = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            session.delete(entity);
            tx1.commit();
        } catch (HibernateException e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        }
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM " + entityClass.getSimpleName(), entityClass).list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> List<T> findAll(Class<T> entityClass, Integer parameter, String parameterName) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM " + entityClass.getSimpleName() + " WHERE :name = :num", entityClass)
                    .setParameter("num", parameter)
                    .setParameter("name", parameterName)
                    .list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T findById(Class<T> entityClass, int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM " + entityClass.getSimpleName() + " WHERE id = :id", entityClass)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}