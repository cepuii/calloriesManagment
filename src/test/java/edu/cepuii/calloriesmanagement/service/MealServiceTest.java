package edu.cepuii.calloriesmanagement.service;

import static edu.cepuii.calloriesmanagement.MealTestData.FIND_MEAL;
import static edu.cepuii.calloriesmanagement.MealTestData.MEAL;
import static edu.cepuii.calloriesmanagement.MealTestData.NOT_FOUND_FIND_MEAL;
import static edu.cepuii.calloriesmanagement.MealTestData.assertMatch;
import static edu.cepuii.calloriesmanagement.MealTestData.getNew;
import static edu.cepuii.calloriesmanagement.MealTestData.getUpdated;
import static edu.cepuii.calloriesmanagement.MealTestData.getUserMeals;
import static edu.cepuii.calloriesmanagement.MealTestData.getUserMealsWithFilter;
import static edu.cepuii.calloriesmanagement.UserTestData.ADMIN_ID;
import static edu.cepuii.calloriesmanagement.UserTestData.NOT_FOUND;
import static edu.cepuii.calloriesmanagement.UserTestData.USER_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import edu.cepuii.calloriesmanagement.model.Meal;
import edu.cepuii.calloriesmanagement.util.exception.NotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
  
  static {
    SLF4JBridgeHandler.install();
  }
  
  @Autowired
  private MealService service;
  
  @Test
  public void save() {
    service.save(getUpdated(), USER_ID);
    assertMatch(service.getById(FIND_MEAL, USER_ID), getUpdated());
  }
  
  @Test
  public void create() {
    Meal created = service.save(getNew(), USER_ID);
    Integer newId = created.getId();
    Meal newMeal = getNew();
    newMeal.setId(newId);
    assertMatch(service.getById(newId, USER_ID), newMeal);
    assertMatch(created, newMeal);
  }
  
  @Test
  public void duplicateDateTimeCreate() {
    assertThrows(DataAccessException.class,
        () -> service.save(new Meal(null, LocalDateTime.of(2020, 12, 19, 5, 0, 0),
            "User Afternoon snack", 108), USER_ID));
  }
  
  @Test
  public void delete() {
    service.delete(FIND_MEAL, USER_ID);
    assertThrows(DataAccessException.class, () -> service.getById(FIND_MEAL, USER_ID));
  }
  
  @Test
  public void deleteNotFound() {
    assertThrows(NotFoundException.class, () -> service.delete(FIND_MEAL, ADMIN_ID));
  }
  
  @Test
  public void getAll() {
    Collection<Meal> meals = service.getAll(USER_ID);
    assertMatch(meals, getUserMeals());
  }
  
  @Test
  public void getById() {
    Meal meal = service.getById(FIND_MEAL, USER_ID);
    assertEquals(MEAL, meal);
  }
  
  @Test
  public void getByIdNotFound() {
    assertThrows(DataAccessException.class, () -> service.getById(NOT_FOUND_FIND_MEAL, USER_ID));
  }
  
  @Test
  public void getByIdNotFoundByUserId() {
    assertThrows(DataAccessException.class, () -> service.getById(FIND_MEAL, NOT_FOUND));
  }
  
  @Test
  public void getBetweenInclusive() {
    LocalDate startDate = LocalDate.of(2020, 12, 19);
    LocalDate endDate = LocalDate.of(2020, 12, 20);
    Collection<Meal> meals = service.getBetweenInclusive(startDate,
        endDate, USER_ID);
    assertMatch(meals, getUserMealsWithFilter(startDate, endDate));
  }
  
  
  @Test
  public void getBetweenInclusiveWithNulls() {
    assertMatch(service.getBetweenInclusive(null, null, USER_ID), getUserMeals());
  }
}