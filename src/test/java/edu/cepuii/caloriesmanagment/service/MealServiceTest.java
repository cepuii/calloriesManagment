package edu.cepuii.caloriesmanagment.service;

import static edu.cepuii.caloriesmanagment.MealTestData.FIND_MEAL;
import static edu.cepuii.caloriesmanagment.MealTestData.MEAL;
import static edu.cepuii.caloriesmanagment.MealTestData.NOT_FOUND_FIND_MEAL;
import static edu.cepuii.caloriesmanagment.MealTestData.getNew;
import static edu.cepuii.caloriesmanagment.MealTestData.getUpdated;
import static edu.cepuii.caloriesmanagment.MealTestData.getUserMeals;
import static edu.cepuii.caloriesmanagment.MealTestData.getUserMealsWithFilter;
import static edu.cepuii.caloriesmanagment.Profiles.ACTIVE_DB;
import static edu.cepuii.caloriesmanagment.UserTestData.ADMIN_ID;
import static edu.cepuii.caloriesmanagment.UserTestData.NOT_FOUND;
import static edu.cepuii.caloriesmanagment.UserTestData.USER_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.slf4j.LoggerFactory.getLogger;

import edu.cepuii.caloriesmanagment.MatcherFactory;
import edu.cepuii.caloriesmanagment.MatcherFactory.Matcher;
import edu.cepuii.caloriesmanagment.model.Meal;
import edu.cepuii.caloriesmanagment.util.exception.NotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(ACTIVE_DB)
public class MealServiceTest {
  
  private static final Matcher<Meal> MEAL_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(
      "user");
  
  private static final Logger log = getLogger("result");
  
  private static final StringBuilder results = new StringBuilder();
  
  @Rule
  public final Stopwatch stopwatch = new Stopwatch() {
    @Override
    protected void finished(long nanos, Description description) {
      String result = String.format("\n%-25s %7d", description.getMethodName(),
          TimeUnit.NANOSECONDS.toMillis(nanos));
      results.append(result);
      log.info(result + " ms\n");
    }
  };
  @Autowired
  private MealService service;
  
  @AfterClass
  public static void printResult() {
    log.info("""
        ----------------------
        Test          Duration, ms
        """ + results + "\n----------------------");
  }
  
  @Test
  public void save() {
    service.save(getUpdated(), USER_ID);
    MEAL_MATCHER.assertMatch(service.getById(FIND_MEAL, USER_ID), getUpdated());
  }
  
  @Test
  public void create() {
    Meal created = service.save(getNew(), USER_ID);
    Integer newId = created.getId();
    Meal newMeal = getNew();
    newMeal.setId(newId);
    MEAL_MATCHER.assertMatch(service.getById(newId, USER_ID), newMeal);
    MEAL_MATCHER.assertMatch(created, newMeal);
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
    assertThrows(NotFoundException.class, () -> service.getById(FIND_MEAL, USER_ID));
  }
  
  @Test
  public void deleteNotFound() {
    assertThrows(NotFoundException.class, () -> service.delete(FIND_MEAL, ADMIN_ID));
  }
  
  @Test
  public void getAll() {
    Collection<Meal> meals = service.getAll(USER_ID);
    MEAL_MATCHER.assertMatch(meals, getUserMeals());
  }
  
  @Test
  public void getById() {
    Meal meal = service.getById(FIND_MEAL, USER_ID);
    assertEquals(MEAL, meal);
  }
  
  @Test
  public void getByIdNotFound() {
    assertThrows(NotFoundException.class, () -> service.getById(NOT_FOUND_FIND_MEAL, USER_ID));
  }
  
  @Test
  public void getByIdNotFoundByUserId() {
    assertThrows(NotFoundException.class, () -> service.getById(FIND_MEAL, NOT_FOUND));
  }
  
  @Test
  public void getBetweenInclusive() {
    LocalDate startDate = LocalDate.of(2020, 12, 19);
    LocalDate endDate = LocalDate.of(2020, 12, 20);
    Collection<Meal> meals = service.getBetweenInclusive(startDate,
        endDate, USER_ID);
    MEAL_MATCHER.assertMatch(meals, getUserMealsWithFilter(startDate, endDate));
  }
  
  
  @Test
  public void getBetweenInclusiveWithNulls() {
    MEAL_MATCHER.assertMatch(service.getBetweenInclusive(null, null, USER_ID), getUserMeals());
  }
}