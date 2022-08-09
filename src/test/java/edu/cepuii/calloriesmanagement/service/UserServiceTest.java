package edu.cepuii.calloriesmanagement.service;

import static edu.cepuii.calloriesmanagement.UserTestData.ADMIN;
import static edu.cepuii.calloriesmanagement.UserTestData.GUEST;
import static edu.cepuii.calloriesmanagement.UserTestData.NOT_FOUND;
import static edu.cepuii.calloriesmanagement.UserTestData.USER;
import static edu.cepuii.calloriesmanagement.UserTestData.USER_ID;
import static edu.cepuii.calloriesmanagement.UserTestData.getNew;
import static org.junit.Assert.assertThrows;

import edu.cepuii.calloriesmanagement.MatcherFactory;
import edu.cepuii.calloriesmanagement.MatcherFactory.Matcher;
import edu.cepuii.calloriesmanagement.model.Role;
import edu.cepuii.calloriesmanagement.model.User;
import edu.cepuii.calloriesmanagement.util.exception.NotFoundException;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {
  
  private final static Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(
      "registered", "roles");
  @Autowired
  private UserService service;
  
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
}