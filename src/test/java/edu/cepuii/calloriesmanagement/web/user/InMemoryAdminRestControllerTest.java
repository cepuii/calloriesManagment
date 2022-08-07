package edu.cepuii.calloriesmanagement.web.user;

import static edu.cepuii.calloriesmanagement.UserTestData.NOT_FOUND;
import static edu.cepuii.calloriesmanagement.UserTestData.USER_ID;

import edu.cepuii.calloriesmanagement.repository.inmemory.InMemoryUserRepository;
import edu.cepuii.calloriesmanagement.util.exception.NotFoundException;
import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InMemoryAdminRestControllerTest {
  
  private final static Logger log = LoggerFactory.getLogger(InMemoryAdminRestControllerTest.class);
  
  private static ConfigurableApplicationContext appCtx;
  
  private static AdminRestController controller;
  
  private static InMemoryUserRepository repository;
  
  @BeforeClass
  public static void beforeClass() {
    appCtx = new ClassPathXmlApplicationContext("spring/inmemory.xml",
        "spring/spring-db.xml");
    log.info("\n{}\n", Arrays.toString(appCtx.getBeanDefinitionNames()));
    controller = appCtx.getBean(AdminRestController.class);
    repository = appCtx.getBean(InMemoryUserRepository.class);
  }
  
  @AfterClass
  public static void afterClass() {
    appCtx.close();
  }
  
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