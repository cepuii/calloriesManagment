package edu.cepuii.calloriesmanagement.service;

import static edu.cepuii.calloriesmanagement.UserTestData.ADMIN;
import static edu.cepuii.calloriesmanagement.UserTestData.GUEST;
import static edu.cepuii.calloriesmanagement.UserTestData.NOT_FOUND;
import static edu.cepuii.calloriesmanagement.UserTestData.USER;
import static edu.cepuii.calloriesmanagement.UserTestData.USER_ID;
import static edu.cepuii.calloriesmanagement.UserTestData.assertMatch;
import static edu.cepuii.calloriesmanagement.UserTestData.getNew;
import static org.junit.Assert.assertThrows;

import edu.cepuii.calloriesmanagement.model.Role;
import edu.cepuii.calloriesmanagement.model.User;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {
  
  static {
    SLF4JBridgeHandler.install();
  }
  
  @Autowired
  private UserService service;
  
  @Test
  public void create() {
    User created = service.save(getNew());
    Integer id = created.getId();
    User newUser = getNew();
    newUser.setId(id);
    assertMatch(created, newUser);
    assertMatch(service.get(id), newUser);
  }
  
  @Test
  public void duplicateMailCreate() {
    assertThrows(DataAccessException.class,
        () -> service.save(new User(null, "duplicate", "admin@gmail.com", "newPass", Role.USER)));
  }
  
  @Test
  public void delete() {
    service.delete(USER_ID);
    assertThrows(EmptyResultDataAccessException.class, () -> service.get(USER_ID));
  }
  
  @Test
  public void get() {
    User user = service.get(USER_ID);
    assertMatch(user, USER);
  }
  
  @Test
  public void getNotFound() {
    assertThrows(EmptyResultDataAccessException.class, () -> service.get(NOT_FOUND));
  }
  
  @Test
  public void getByEmail() {
    User user = service.getByEmail("admin@gmail.com");
    assertMatch(user, ADMIN);
  }
  
  @Test
  public void getAll() {
    Collection<User> users = service.getAll();
    assertMatch(users, ADMIN, GUEST, USER);
  }
}