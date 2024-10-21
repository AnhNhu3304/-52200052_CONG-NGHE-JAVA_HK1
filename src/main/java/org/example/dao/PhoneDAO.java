package org.example.dao;

import org.example.domain.Phone;
import org.example.repository.Repository;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class PhoneDAO implements Repository<Phone> {

    @Override
    public boolean add(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSession()) {
            transaction = session.beginTransaction();
            session.save(phone);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Phone get(int id) {
        try (Session session = HibernateUtils.getSession()) {
            return session.get(Phone.class, id);
        } catch (Exception e) {
            System.err.println("Error retrieving Phone with id: " + id);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Phone> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Phone> query = session.createQuery("from Phone", Phone.class);
            return query.list();
        }
    }

    @Override
    public boolean remove(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Phone phone = session.get(Phone.class, id);
            if (phone != null) {
                session.delete(phone);
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
    public boolean remove(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(phone);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(phone);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
    public Phone getHighestPricePhone() {
        Phone highestPricePhone = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "FROM Phone ORDER BY Price DESC";
            Query<Phone> query = session.createQuery(hql, Phone.class);

            query.setMaxResults(1);

            List<Phone> result = query.list();

            if (!result.isEmpty()) {
                highestPricePhone = result.get(0);
            }
        }
        return highestPricePhone;
    }


    public List<Phone> getPhonesSortedByCountry() {
        List<Phone> phonesList;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "FROM Phone ORDER BY Country, Price DESC";

            Query<Phone> query = session.createQuery(hql, Phone.class);

            phonesList = query.list();

        }
        return phonesList;
    }


    public boolean hasPhoneAbove50Million() {
        boolean exists = false;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(p) FROM Phone p WHERE p.price > 50000000";
            Query<Long> query = session.createQuery(hql, Long.class);

            Long count = query.uniqueResult();

            if (count != null && count > 0) {
                exists = true;
            }
        }
        return exists;
    }

    public Phone getFirstPinkPhoneOver15Million() {
        Phone pinkPhone = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "FROM Phone WHERE Color = :color AND Price > :price";
            Query<Phone> query = session.createQuery(hql, Phone.class);

            query.setParameter("color", "Pink");
            query.setParameter("price", 15000000);

            query.setMaxResults(1);

            pinkPhone = query.uniqueResult();
        }
        return pinkPhone;
    }

}
