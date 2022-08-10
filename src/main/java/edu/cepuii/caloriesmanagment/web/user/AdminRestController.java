package edu.cepuii.caloriesmanagment.web.user;

import edu.cepuii.caloriesmanagment.model.User;
import edu.cepuii.caloriesmanagment.service.UserService;
import java.util.Collection;
import org.springframework.stereotype.Controller;

/**
 * @author cepuii on 19.07.2022
 */
@Controller

public class AdminRestController extends AbstractUserController {
  
  public AdminRestController(UserService service) {
    super(service);
  }
  
  @Override
  public Collection<User> getAll() {
    return super.getAll();
  }
  
  @Override
  public User get(int id) {
    return super.get(id);
  }
  
  @Override
  public User create(User user) {
    return super.create(user);
  }
  
  @Override
  public void delete(int id) {
    super.delete(id);
  }
  
  @Override
  public void update(User user, int id) {
    super.update(user, id);
  }
  
  @Override
  public User getByEmail(String email) {
    return super.getByEmail(email);
  }
}


