package edu.cepuii.calloriesmanagement;

import edu.cepuii.calloriesmanagement.model.Role;
import edu.cepuii.calloriesmanagement.model.User;

public class UserTestData {
  
  public static final int USER_ID = 1;
  public static final int ADMIN_ID = 2;
  public static final int GUEST_ID = 3;
  public static final int NOT_FOUND = 10;
  
  public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password",
      Role.USER);
  public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin",
      Role.ADMIN);
  public static final User GUEST = new User(GUEST_ID, "Guest", "guest@gmail.com", "guest");
}
