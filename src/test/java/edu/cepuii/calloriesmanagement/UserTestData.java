package edu.cepuii.calloriesmanagement;

import static edu.cepuii.calloriesmanagement.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

import edu.cepuii.calloriesmanagement.model.Role;
import edu.cepuii.calloriesmanagement.model.User;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class UserTestData {
  
  public static final int USER_ID = START_SEQ;
  public static final int ADMIN_ID = START_SEQ + 1;
  public static final int GUEST_ID = START_SEQ + 2;
  
  public static final int NOT_FOUND = 10;
  
  
  public static final User USER = new User(USER_ID, "User", "user@gmail.com", "password",
      Role.USER);
  public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin",
      Role.ADMIN);
  public static final User GUEST = new User(GUEST_ID, "Guest", "guest@gmail.com", "guest");
  
  public static User getNew() {
    return new User(null, "New", "new@mail.com", "newPass", false, new Date(),
        Collections.singleton(Role.USER), 1555);
  }
  
  public static User getUpdated() {
    User updated = new User(USER);
    updated.setEmail("update@gmail.com");
    updated.setName("UpdatedName");
    updated.setCaloriesPerDay(330);
    updated.setPassword("newPass");
    updated.setEnabled(false);
    updated.setRoles(Collections.singletonList(Role.ADMIN));
    return updated;
  }
  
  public static void assertMatch(User actual, User expected) {
    assertThat(actual).usingRecursiveComparison().ignoringFields("registered", "roles")
        .isEqualTo(expected);
  }
  
  public static void assertMatch(Iterable<User> actual, User... expected) {
    assertMatch(actual, Arrays.asList(expected));
  }
  
  public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
    assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields("registered",
        "roles").isEqualTo(expected);
  }
}
