package edu.cepuii.caloriesmanagment.repository.dataJpa;

import edu.cepuii.caloriesmanagment.model.User;
import edu.cepuii.caloriesmanagment.repository.UserRepository;
import java.util.Collection;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class DataJpaUserRepository implements UserRepository {
  
  private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");
  private final CrudUserRepository userRepository;
  
  public DataJpaUserRepository(CrudUserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @Override
  public User save(User user) {
    return userRepository.save(user);
  }
  
  @Override
  public boolean delete(int id) {
    return userRepository.delete(id) != 0;
  }
  
  @Override
  public User get(int id) {
    return userRepository.findById(id).orElse(null);
  }
  
  @Override
  public User getByEmail(String email) {
    return userRepository.findUserByEmail(email).orElse(null);
  }
  
  @Override
  public Collection<User> getAll() {
    return userRepository.findAll(SORT_NAME_EMAIL);
  }
  
  @Override
  public User getWithMeals(int id) {
    return userRepository.getWithMeals(id);
  }
}
