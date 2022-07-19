package edu.cepuii.calloriesmanagment.web.user;

import static edu.cepuii.calloriesmanagment.util.ValidationUtil.checkNotFound;
import static edu.cepuii.calloriesmanagment.util.ValidationUtil.checkNotFoundWithId;

import edu.cepuii.calloriesmanagment.model.User;
import edu.cepuii.calloriesmanagment.service.UserService;
import java.util.List;

/**
 * @author cepuii on 19.07.2022
 */
public class AbstractUserController {
  
  private UserService service;
  
  public User create(User user) {
    return service.save(user);
  }
  
  public void delete(int id) {
    checkNotFoundWithId(service.delete(id), id);
  }
  
  public User get(int id) {
    return checkNotFoundWithId(service.get(id), id);
  }
  
  public User getByEmail(String email) {
    return checkNotFound(service.getByEmail(email), "email=" + email);
  }
  
  public List<User> getAll() {
    return service.getAll();
  }
  
  public void update(User user, int id) {
    checkNotFoundWithId(service.save(user), user.getId());
  }
}
