package edu.cepuii.calloriesmanagment.dao;

import edu.cepuii.calloriesmanagment.model.Meal;
import edu.cepuii.calloriesmanagment.util.MealUtil;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cepuii on 16.07.2022
 */
public class InMemory implements RepositoryMeal {
  
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
      repository.put(meal.getId(), meal);
      return meal;
    }
    return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
  }
  
  @Override
  public boolean delete(int id) {
    return repository.remove(id, getMealById(id));
  }
  
  @Override
  public Collection<Meal> getAllMeals() {
    return repository.values();
  }
  
  @Override
  public Meal getMealById(int id) {
    return repository.get(id);
  }
}
