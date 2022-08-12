package edu.cepuii.caloriesmanagment.service;

import static edu.cepuii.caloriesmanagment.util.ValidationUtil.checkNotFound;
import static edu.cepuii.caloriesmanagment.util.ValidationUtil.checkNotFoundWithId;

import edu.cepuii.caloriesmanagment.model.User;
import edu.cepuii.caloriesmanagment.repository.UserRepository;
import edu.cepuii.caloriesmanagment.util.exception.NotFoundException;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @author cepuii on 20.07.2022
 */
@Service
public class UserService {
  
  private final UserRepository repository;
  public UserService(UserRepository repository) {
    this.repository = repository;
  }
  
  
  public User save(User user) {
    User newUser = repository.save(user);
    if (newUser == null) {
      throw new NotFoundException("User not found");
    }
    return newUser;
  }
  
  public boolean delete(int id) {
    if (!repository.delete(id)) {
      throw new NotFoundException("Not found user, id" + id);
    }
    return true;
  }
  
  public User get(int id) {
    return checkNotFoundWithId(repository.get(id), id);
  }
  
  public User getByEmail(String email) {
    return checkNotFound(repository.getByEmail(email), email);
  }
  
  public Collection<User> getAll() {
    return repository.getAll();
  }
}