package edu.cepuii.caloriesmanagment.service;

import static edu.cepuii.caloriesmanagment.UserTestData.ADMIN;
import static edu.cepuii.caloriesmanagment.UserTestData.GUEST;
import static edu.cepuii.caloriesmanagment.UserTestData.NOT_FOUND;
import static edu.cepuii.caloriesmanagment.UserTestData.USER;
import static edu.cepuii.caloriesmanagment.UserTestData.USER_ID;
import static edu.cepuii.caloriesmanagment.UserTestData.getNew;
import static edu.cepuii.caloriesmanagment.UserTestData.getUpdated;
import static org.junit.Assert.assertThrows;

import edu.cepuii.caloriesmanagment.MatcherFactory;
import edu.cepuii.caloriesmanagment.MatcherFactory.Matcher;
import edu.cepuii.caloriesmanagment.model.Role;
import edu.cepuii.caloriesmanagment.model.User;
import edu.cepuii.caloriesmanagment.util.exception.NotFoundException;
import java.util.Collection;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;

public abstract class AbstractUserServiceTest extends AbstractServiceTest {
  
  private final static Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(
      "registered", "roles", "meals");
  @Autowired
  private UserService service;
  @Autowired
  private CacheManager cacheManager;
  
  @Before
  public void setUp() {
    cacheManager.getCache("users").clear();
  }
  
  @Test
  public void create() {
    User created = service.save(getNew());
    Integer id = created.getId();
    User newUser = getNew();
    newUser.setId(id);
    USER_MATCHER.assertMatch(created, newUser);
    USER_MATCHER.assertMatch(service.get(id), newUser);
  }
  
  @Test
  public void duplicateMailCreate() {
    assertThrows(DataAccessException.class,
        () -> service.save(new User(null, "duplicate", "admin@gmail.com", "newPass", Role.USER)));
  }
  
  @Test
  public void delete() {
    service.delete(USER_ID);
    assertThrows(NotFoundException.class, () -> service.get(USER_ID));
  }
  
  @Test
  public void get() {
    User user = service.get(USER_ID);
    USER_MATCHER.assertMatch(user, USER);
  }
  
  @Test
  public void getNotFound() {
    assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
  }
  
  @Test
  public void getByEmail() {
    User user = service.getByEmail("admin@gmail.com");
    USER_MATCHER.assertMatch(user, ADMIN);
  }
  
  @Test
  public void getAll() {
    Collection<User> users = service.getAll();
    USER_MATCHER.assertMatch(users, ADMIN, GUEST, USER);
  }
  
  @Test
  public void update() {
    User updated = getUpdated();
    service.save(updated);
    USER_MATCHER.assertMatch(service.get(USER_ID), getUpdated());
  }
  
  @Test
  public void createWithException() throws Exception {
    validateRootCause(ConstraintViolationException.class,
        () -> service.save(new User(null, "  ", "mail@yandex.ru", "password", Role.USER)));
    validateRootCause(ConstraintViolationException.class,
        () -> service.save(new User(null, "User", "  ", "password", Role.USER)));
    validateRootCause(ConstraintViolationException.class,
        () -> service.save(new User(null, "User", "mail@yandex.ru", "  ", Role.USER)));
  }
}

