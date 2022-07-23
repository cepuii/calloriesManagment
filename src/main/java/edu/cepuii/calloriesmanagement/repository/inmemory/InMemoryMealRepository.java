package edu.cepuii.calloriesmanagement.repository.inmemory;

import edu.cepuii.calloriesmanagement.model.Meal;
import edu.cepuii.calloriesmanagement.repository.MealRepository;
import edu.cepuii.calloriesmanagement.util.MealUtil;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author cepuii on 16.07.2022
 */
@Repository
public class InMemoryMealRepository implements MealRepository {
  
  private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
  public static final int CALORIES_PER_DAY = 2000;
  private final static AtomicInteger generateId = new AtomicInteger(1);
  private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
  
  {
    MealUtil.getMeals().forEach(this::save);
  }
  
  @Override
  public Meal save(Meal meal) {
    if (meal.isNew()) {
      meal.setId(generateId.incrementAndGet());
      log.info("create and add new meal, id:" + meal.getId());
      repository.put(meal.getId(), meal);
      return meal;
    }
    log.info(" update meal, id: " + meal.getId());
    return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
  }
  
  @Override
  public boolean delete(int id) {
    log.info("delete meal, id: " + id);
    return repository.remove(id, getMealById(id));
  }
  
  @Override
  public Collection<Meal> getAllMeals() {
    log.info("get All Meals");
    return repository.values();
  }
  
  @Override
  public Meal getMealById(int id) {
    log.info("get meal by id: " + id);
    return repository.get(id);
  }
}
