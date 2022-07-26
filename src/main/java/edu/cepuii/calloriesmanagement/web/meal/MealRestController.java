package edu.cepuii.calloriesmanagement.web.meal;

import static edu.cepuii.calloriesmanagement.util.ValidationUtil.checkNotFoundWithId;

import edu.cepuii.calloriesmanagement.model.Meal;
import edu.cepuii.calloriesmanagement.service.MealService;
import edu.cepuii.calloriesmanagement.to.MealTO;
import edu.cepuii.calloriesmanagement.util.MealUtil;
import edu.cepuii.calloriesmanagement.web.SecurityUtil;
import java.util.Collection;
import org.springframework.stereotype.Controller;

/**
 * @author cepuii on 20.07.2022
 */
@Controller
public class MealRestController {
  
  private final MealService service;
  
  public MealRestController(MealService service) {
    this.service = service;
  }
  
  public Meal create(Meal meal) {
    int userId = SecurityUtil.authUserId();
    return service.save(meal, userId);
  }
  
  public void delete(int id) {
    int userId = SecurityUtil.authUserId();
    checkNotFoundWithId(service.delete(id, userId), id);
  }
  
  public Meal get(int id) {
    int userId = SecurityUtil.authUserId();
    return checkNotFoundWithId(service.getById(id, userId), id);
  }
  
  public void update(Meal meal, int id) {
    int userId = SecurityUtil.authUserId();
    checkNotFoundWithId(service.save(meal, userId), id);
  }
  
  public Collection<MealTO> getAll() {
    return MealUtil.checkExcess(service.getAllMeals(), SecurityUtil.authUserCaloriesPerDay());
  }
  
}
