package edu.cepuii.calloriesmanagment.service;

import edu.cepuii.calloriesmanagment.model.User;
import edu.cepuii.calloriesmanagment.repository.UserRepository;
import java.util.List;

/**
 * @author cepuii on 20.07.2022
 */
public class UserService {
  
  private UserRepository repository;
  
  public User save(User user) {
    return repository.save(user);
  }
  
  public boolean delete(int id) {
    return repository.delete(id);
  }
  
  public User get(int id) {
    return repository.get(id);
  }
  
  public User getByEmail(String email) {
    return repository.getByEmail(email);
  }
  
  public List<User> getAll() {
    return repository.getAll();
  }
}
