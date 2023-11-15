package com.example.sdservidor.DAO;

import com.example.sdservidor.Models.Point;
import com.example.sdservidor.Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class PointDAO {

    private final SessionFactory sessionFactory;

    public PointDAO() {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public String addPoint(Point point) {
        String response = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(point);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            response = e.getMessage();
        }

        return response;
    }

    public Point getPointById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Point.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Point> getAllPoints() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Point", Point.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updatePoint(Point point) {
        String response = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(point);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            response = e.getMessage();
        }

        return response;
    }

    public void deletePoint(Point point) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(point);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}