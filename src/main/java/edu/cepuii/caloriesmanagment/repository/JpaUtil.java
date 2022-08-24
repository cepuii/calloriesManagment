package edu.cepuii.caloriesmanagment.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class JpaUtil {
  
  @PersistenceContext
  EntityManager entityManager;
  
  public void clear2ndLevelHibernateCache() {
    Session s = (Session) entityManager.getDelegate();
    SessionFactory sessionFactory = (SessionFactory) s.getSessionFactory();
    sessionFactory.getCache().evictAllRegions();
  }
}
