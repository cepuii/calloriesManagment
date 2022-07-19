package edu.cepuii.calloriesmanagment.repository;

import edu.cepuii.calloriesmanagment.model.User;
import java.util.List;

/**
 * @author cepuii on 19.07.2022
 */
public interface UserRepository {
  
  User save(User user);
  
  boolean delete(int id);
  
  User get(int id);
  
  User getByEmail(String email);
  
  List<User> getAll();
  
}
