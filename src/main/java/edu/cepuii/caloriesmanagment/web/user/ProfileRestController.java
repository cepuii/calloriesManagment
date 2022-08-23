package edu.cepuii.caloriesmanagment.web.user;

import edu.cepuii.caloriesmanagment.model.User;
import edu.cepuii.caloriesmanagment.service.UserService;
import edu.cepuii.caloriesmanagment.web.SecurityUtil;
import org.springframework.stereotype.Controller;

/**
 * @author cepuii on 20.07.2022
 */
@Controller
public class ProfileRestController extends AbstractUserController {
  
  public ProfileRestController(UserService service) {
    super(service);
  }
  
  public User get() {
    return super.get(SecurityUtil.authUserId());
  }
  
  public void delete() {
    super.delete(SecurityUtil.authUserId());
  }
  
  public void update(User user) {
    super.update(user, SecurityUtil.authUserId());
  }
}
