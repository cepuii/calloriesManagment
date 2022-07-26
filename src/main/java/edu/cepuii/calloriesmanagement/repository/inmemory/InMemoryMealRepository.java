package edu.cepuii.calloriesmanagement.repository.inmemory;

import edu.cepuii.calloriesmanagement.model.Meal;
import edu.cepuii.calloriesmanagement.repository.MealRepository;
import edu.cepuii.calloriesmanagement.util.MealUtil;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author cepuii on 16.07.2022
 */
@Repository
public class InMemoryMealRepository implements MealRepository {
  private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
  private final static AtomicInteger generateId = new AtomicInteger(1);
  private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
  
  {
    MealUtil.getMeals().forEach(meal -> save(meal, 1));
  }
  
  @Override
  public Meal save(Meal meal, int userId) {
    if (meal.isNew()) {
      meal.setId(generateId.incrementAndGet());
      meal.setUserId(userId);
      log.info("create and add new meal, id:" + meal.getId() + " by user: " + userId);
      repository.put(meal.getId(), meal);
      return meal;
    }
    log.info(" update meal, id: " + meal.getId() + " by user: " + userId);
    if (repository.get(meal.getId()).getUserId() != userId) {
      return null;
    }
    return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
  }
  
  @Override
  public boolean delete(int id, int userId) {
    log.info("delete meal, id: " + id + " by user: " + userId);
    Meal meal = getById(id, userId);
    if (meal == null) {
      return false;
    }
    return repository.remove(id, meal);
  }
  
  @Override
  public Collection<Meal> getAll() {
    log.info("get All Meals, sorted by date/time descending");
    return repository.values().stream()
        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
        .collect(Collectors.toList());
  }
  
  @Override
  public Meal getById(int id, int userId) {
    log.info("get meal, id: " + id + " by user: " + userId);
    if (userId != repository.get(id).getUserId()) {
      return null;
    }
    return repository.get(id);
  }
}
