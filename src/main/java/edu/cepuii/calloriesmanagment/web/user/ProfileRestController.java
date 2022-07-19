package edu.cepuii.calloriesmanagment.web.user;

import edu.cepuii.calloriesmanagment.model.User;
import edu.cepuii.calloriesmanagment.web.SecurityUtil;

/**
 * @author cepuii on 20.07.2022
 */
public class ProfileRestController extends AbstractUserController {
  
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
