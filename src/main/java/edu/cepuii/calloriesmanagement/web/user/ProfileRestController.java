package edu.cepuii.calloriesmanagement.web.user;

import edu.cepuii.calloriesmanagement.model.User;
import edu.cepuii.calloriesmanagement.service.UserService;
import edu.cepuii.calloriesmanagement.web.SecurityUtil;
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
