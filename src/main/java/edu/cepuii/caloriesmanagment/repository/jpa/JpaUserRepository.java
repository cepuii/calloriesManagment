package edu.cepuii.caloriesmanagment.repository.jpa;

import edu.cepuii.caloriesmanagment.model.User;
import edu.cepuii.caloriesmanagment.repository.UserRepository;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {
  
  @PersistenceContext
  private EntityManager em;
  
  @Transactional
  @Override
  public User save(User user) {
    if (user.isNew()) {
      em.persist(user);
      return user;
    } else {
      return em.merge(user);
    }
  }
  
  @Transactional
  @Override
  public boolean delete(int id) {
    return em.createNamedQuery(User.DELETE)
        .setParameter("id", id)
        .executeUpdate() != 0;
  }
  
  @Override
  public User get(int id) {
    return em.find(User.class, id);
  }
  
  @Override
  public User getByEmail(String email) {
    return em.createNamedQuery(User.BY_EMAIL, User.class)
        .setParameter(1, email)
        .getSingleResult();
  }
  
  @Override
  public Collection<User> getAll() {
    return em.createNamedQuery(User.ALL_SORTED, User.class)
        .getResultList();
  }
}
