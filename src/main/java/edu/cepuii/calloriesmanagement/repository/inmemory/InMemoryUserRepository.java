package edu.cepuii.calloriesmanagement.repository.inmemory;

import edu.cepuii.calloriesmanagement.model.User;
import edu.cepuii.calloriesmanagement.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author cepuii on 22.07.2022
 */
@Repository
public class InMemoryUserRepository implements UserRepository {
  
  @Override
  public User save(User user) {
    return null;
  }
  
  @Override
  public boolean delete(int id) {
    return false;
  }
  
  @Override
  public User get(int id) {
    return null;
  }
  
  @Override
  public User getByEmail(String email) {
    return null;
  }
  
  @Override
  public List<User> getAll() {
    return null;
  }
}
