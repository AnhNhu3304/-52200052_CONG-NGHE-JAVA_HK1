package org.example.dao;
import  org.example.Exception.InvalidOperationException;
import org.example.domain.Manufacture;
import org.example.repository.Repository;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ManufactureDAO implements Repository<Manufacture> {

    @Override
    public boolean add(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(manufacture);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Manufacture get(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Manufacture.class, id);
        }
    }

    @Override
    public List<Manufacture> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Manufacture> query = session.createQuery("from Manufacture", Manufacture.class);
            return query.list();
        }
    }

    @Override
    public boolean remove(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, id);
            if (manufacture != null) {
                session.delete(manufacture);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(manufacture);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(manufacture);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean allManufacturersHaveMoreThan100Employees() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(m) FROM Manufacture m WHERE m.employee > 100";
            Query<Long> query = session.createQuery(hql, Long.class);

            Long count = query.uniqueResult();
            return count != null && count == getAll().size();
        }
    }

    public int getTotalEmployees() {
        Long total = 0L;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(m.employee) FROM Manufacture m";
            Query<Long> query = session.createQuery(hql, Long.class);

            total = query.uniqueResult();
        }
        if (total != null) {
            return total.intValue();
        } else {
            return 0;
        }
    }


    public Manufacture getLastManufacturerBasedInUS() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "FROM Manufacture m WHERE m.location = :location ORDER BY m.id DESC";
            Query<Manufacture> query = session.createQuery(hql, Manufacture.class);
            query.setParameter("location", "US");
            query.setMaxResults(1);
            Manufacture manufacturer = query.uniqueResult();

            if (manufacturer == null) {
                throw new InvalidOperationException("No manufacturer based in the US found.");
            }
            return manufacturer;
        }
    }
}
