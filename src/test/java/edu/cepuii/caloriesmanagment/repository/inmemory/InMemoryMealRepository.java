package edu.cepuii.caloriesmanagment.repository.inmemory;

import static edu.cepuii.caloriesmanagment.UserTestData.ADMIN_ID;
import static edu.cepuii.caloriesmanagment.UserTestData.USER_ID;

import edu.cepuii.caloriesmanagment.model.Meal;
import edu.cepuii.caloriesmanagment.repository.MealRepository;
import edu.cepuii.caloriesmanagment.util.FilterUtil;
import edu.cepuii.caloriesmanagment.util.MealUtil;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
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
  private final Map<Integer, InMemoryBaseRepository<Meal>> usersMealsMap = new ConcurrentHashMap<>();
  
  
  {
    MealUtil.getMealList().forEach(meal -> save(meal, USER_ID));
    save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510), ADMIN_ID);
    save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500), ADMIN_ID);
    
  }
  
  @Override
  public Meal save(Meal meal, int userId) {
    InMemoryBaseRepository<Meal> meals = usersMealsMap.computeIfAbsent(userId,
        uId -> new InMemoryBaseRepository<>());
    return meals.save(meal);
  }
  
  @Override
  public boolean delete(int id, int userId) {
    log.info("delete meal, id: " + id + " by user: " + userId);
    InMemoryBaseRepository<Meal> meals = usersMealsMap.get(userId);
    return meals != null && meals.delete(id);
  }
  
  @Override
  public Meal get(int id, int userId) {
    log.info("get meal, id: " + id + " by user: " + userId);
    InMemoryBaseRepository<Meal> meals = usersMealsMap.get(userId);
    return meals == null ? null : meals.get(id);
  }
  
  public Collection<Meal> getBetweenHalfOpen(LocalDateTime start, LocalDateTime end, int userId) {
    return filterByPredicate(userId,
        meal -> FilterUtil.isBetweenHalfOpen(meal.getDateTime(), start, end));
  }
  
  @Override
  public Collection<Meal> getAll(int userId) {
    return filterByPredicate(userId, meal -> true);
  }
  
  public Collection<Meal> filterByPredicate(int userId, Predicate<Meal> filter) {
    log.info("get All Meals, sorted by date/time descending");
    InMemoryBaseRepository<Meal> meals = usersMealsMap.get(userId);
    return meals == null ? Collections.emptyList() :
        meals.getCollection().stream()
            .filter(filter)
            .sorted(Comparator.comparing(Meal::getDateTime).reversed())
            .collect(Collectors.toList());
  }
  
  
}
