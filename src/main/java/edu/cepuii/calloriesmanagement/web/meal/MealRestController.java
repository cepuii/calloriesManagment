package edu.cepuii.calloriesmanagement.web.meal;

import static edu.cepuii.calloriesmanagement.util.ValidationUtil.assureIdConsistent;
import static edu.cepuii.calloriesmanagement.util.ValidationUtil.checkNew;

import edu.cepuii.calloriesmanagement.model.Meal;
import edu.cepuii.calloriesmanagement.service.MealService;
import edu.cepuii.calloriesmanagement.to.MealTO;
import edu.cepuii.calloriesmanagement.util.MealUtil;
import edu.cepuii.calloriesmanagement.web.SecurityUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

/**
 * @author cepuii on 20.07.2022
 */
@Controller
public class MealRestController {
  
  private static final Logger LOG = LoggerFactory.getLogger(MealRestController.class);
  private final MealService service;
  
  public MealRestController(MealService service) {
    this.service = service;
  }
  
  public Meal create(Meal meal) {
    int userId = SecurityUtil.authUserId();
    LOG.info("create { }, meal " + meal + " userId " + userId);
    checkNew(meal);
    return service.save(meal, userId);
  }
  
  public void delete(int id) {
    int userId = SecurityUtil.authUserId();
    LOG.info("delete { }, id " + id + " userId " + userId);
    service.delete(id, userId);
  }
  
  public Meal get(int id) {
    int userId = SecurityUtil.authUserId();
    LOG.info("get { }, id " + id + " userId " + userId);
    return service.getById(id, userId);
  }
  
  public void update(Meal meal, int id) {
    int userId = SecurityUtil.authUserId();
    LOG.info("update { }, meal " + meal + " userId " + userId);
    assureIdConsistent(meal, id);
    service.save(meal, userId);
  }
  
  public Collection<MealTO> getAll() {
    int userId = SecurityUtil.authUserId();
    LOG.info("get all { } " + userId);
    return MealUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
  }
  
  public Collection<MealTO> getBetween(@Nullable LocalDate startDate, @Nullable LocalTime startTime,
      @Nullable LocalDate endDate, @Nullable LocalTime endTime) {
    int userId = SecurityUtil.authUserId();
    LOG.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime,
        endTime, userId);
    
    Collection<Meal> mealsDateFiltered = service.getBetweenInclusive(startDate, endDate, userId);
    return MealUtil.getFilteredTos(mealsDateFiltered, SecurityUtil.authUserCaloriesPerDay(),
        startTime, endTime);
  }
}
