package edu.cepuii.calloriesmanagement.repository.jpa;

import edu.cepuii.calloriesmanagement.model.Meal;
import edu.cepuii.calloriesmanagement.model.User;
import edu.cepuii.calloriesmanagement.repository.MealRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {
  
  @PersistenceContext
  private EntityManager em;
  
  @Transactional
  @Override
  public Meal save(Meal meal, int userId) {
    User ref = em.getReference(User.class, userId);
    meal.setUser(ref);
    if (meal.isNew()) {
      em.persist(meal);
      return meal;
    } else {
      return em.merge(meal);
    }
  }
  
  @Transactional
  @Override
  public boolean delete(int id, int userId) {
    return em.createNamedQuery(Meal.DELETE)
        .setParameter("id", id)
        .setParameter("userId", userId)
        .executeUpdate() != 0;
  }
  
  @Override
  public Meal get(int id, int userId) {
    List<Meal> resultList = em.createNamedQuery(Meal.GET, Meal.class)
        .setParameter("id", id)
        .setParameter("userId", userId)
        .getResultList();
    return DataAccessUtils.singleResult(resultList);
  }
  
  @Override
  public Collection<Meal> getAll(int userId) {
    return em.createNamedQuery(Meal.GET_ALL_SORT, Meal.class)
        .setParameter("userId", userId)
        .getResultList();
  }
  
  @Override
  public Collection<Meal> getBetweenHalfOpen(LocalDateTime start, LocalDateTime end, int userId) {
    return em.createNamedQuery(Meal.GET_BY_FILTER, Meal.class)
        .setParameter("userId", userId)
        .setParameter("start", start)
        .setParameter("end", end)
        .getResultList();
  }
}
