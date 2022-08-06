package edu.cepuii.calloriesmanagement.web.user;


import static edu.cepuii.calloriesmanagement.UserTestData.NOT_FOUND;
import static edu.cepuii.calloriesmanagement.UserTestData.USER_ID;

import edu.cepuii.calloriesmanagement.repository.inmemory.InMemoryUserRepository;
import edu.cepuii.calloriesmanagement.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration("classpath:spring/spring-app.xml")
@RunWith(SpringRunner.class)
public class InMemoryAdminRestControllerSpringTest {
  
  @Autowired
  private AdminRestController controller;
  @Autowired
  private InMemoryUserRepository repository;
  
  @Before
  public void setUp() {
    repository.init();
  }
  
  @Test
  public void delete() {
    controller.delete(USER_ID);
    Assert.assertNull(repository.get(USER_ID));
  }
  
  @Test
  public void delete_not_found() {
    Assert.assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
  }
  
}
