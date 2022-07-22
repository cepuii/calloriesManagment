package edu.cepuii.calloriesmanagement.service;

import edu.cepuii.calloriesmanagement.model.User;
import edu.cepuii.calloriesmanagement.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author cepuii on 20.07.2022
 */
@Service
public class UserService {
  
  public UserService(UserRepository repository) {
    this.repository = repository;
  }
  
  private final UserRepository repository;
  
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
