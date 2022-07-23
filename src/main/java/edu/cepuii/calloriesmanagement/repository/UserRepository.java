package edu.cepuii.calloriesmanagement.repository;

import edu.cepuii.calloriesmanagement.model.User;
import java.util.Collection;

/**
 * @author cepuii on 19.07.2022
 */
public interface UserRepository {
  
  User save(User user);
  
  boolean delete(int id);
  
  User get(int id);
  
  User getByEmail(String email);
  
  Collection<User> getAll();
  
}
