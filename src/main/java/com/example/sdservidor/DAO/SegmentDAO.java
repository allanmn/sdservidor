package com.example.sdservidor.DAO;

import com.example.sdservidor.Models.JwtSession;
import com.example.sdservidor.Models.Segment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class SegmentDAO {

    private SessionFactory sessionFactory;

    public SegmentDAO() {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public String saveOrUpdateSegment(Segment segment) {
        String res = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(segment);
            transaction.commit();
        } catch (Exception ex) {
            res = ex.getMessage();
        }

        return res;
    }

    public Segment getSegmentById(Long segmentId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Segment.class, segmentId);
        }
    }

    public List<Segment> getSegmentsNotBlocked() {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<Segment> query = currentSession.createQuery("FROM Segment WHERE bloqueado = false", Segment.class);
            return query.list();
        }
    }


    public List<Segment> getAllSegments() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Segment", Segment.class).getResultList();
        }
    }

    public void deleteSegment(Segment segment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(segment);
            transaction.commit();
        }
    }
}
