package edu.cepuii.caloriesmanagment.web.user;

import static edu.cepuii.caloriesmanagment.util.ValidationUtil.checkNotFound;
import static edu.cepuii.caloriesmanagment.util.ValidationUtil.checkNotFoundWithId;

import edu.cepuii.caloriesmanagment.model.User;
import edu.cepuii.caloriesmanagment.service.UserService;
import java.util.Collection;

/**
 * @author cepuii on 19.07.2022
 */
public class AbstractUserController {
  
  private final UserService service;
  
  public AbstractUserController(UserService service) {
    this.service = service;
  }
  
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
  
  public Collection<User> getAll() {
    return service.getAll();
  }
  
  public void update(User user, int id) {
    checkNotFoundWithId(service.save(user), user.getId());
  }
}
