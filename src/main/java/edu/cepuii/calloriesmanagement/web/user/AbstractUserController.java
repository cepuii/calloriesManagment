package edu.cepuii.calloriesmanagement.web.user;

import static edu.cepuii.calloriesmanagement.util.ValidationUtil.checkNotFound;
import static edu.cepuii.calloriesmanagement.util.ValidationUtil.checkNotFoundWithId;

import edu.cepuii.calloriesmanagement.model.User;
import edu.cepuii.calloriesmanagement.service.UserService;
import java.util.List;

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
  
  public List<User> getAll() {
    return service.getAll();
  }
  
  public void update(User user, int id) {
    checkNotFoundWithId(service.save(user), user.getId());
  }
}
