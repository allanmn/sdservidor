package com.example.sdservidor.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.example.sdservidor.Models.JwtSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class SessionDAO {

    private final SessionFactory sessionFactory;

    public SessionDAO() {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void save(JwtSession session) {
        try (Session currentSession = sessionFactory.openSession()) {
            Transaction transaction = currentSession.beginTransaction();
            currentSession.save(session);
            transaction.commit();
        }
    }

    public JwtSession getSessionById(Long sessionId) {
        try (Session currentSession = sessionFactory.openSession()) {
            return currentSession.get(JwtSession.class, sessionId);
        }
    }

    public JwtSession getSessionByToken(String token) {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<JwtSession> query = currentSession.createQuery("FROM JwtSession WHERE token = :token", JwtSession.class);
            query.setParameter("token", token);
            return query.setMaxResults(1).uniqueResult();
        }
    }

    public List<JwtSession> getSessionsByUserId(Long userId) {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<JwtSession> query = currentSession.createQuery("FROM JwtSession WHERE user.id = :userId", JwtSession.class);
            query.setParameter("userId", userId);
            return query.list();
        }
    }

    public void updateJwtToken(Long sessionId, String newJwtToken) {
        try (Session currentSession = sessionFactory.openSession()) {
            Transaction transaction = currentSession.beginTransaction();
            JwtSession jwt = currentSession.get(JwtSession.class, sessionId);
            jwt.setToken(newJwtToken);
            currentSession.update(jwt);
            transaction.commit();
        }
    }

    public void delete(JwtSession session) {
        try (Session currentSession = sessionFactory.openSession()) {
            Transaction transaction = currentSession.beginTransaction();
            currentSession.delete(session);
            transaction.commit();
        }
    }
}