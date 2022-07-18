package edu.cepuii.calloriesmanagment.dao;

import edu.cepuii.calloriesmanagment.model.Meal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cepuii on 16.07.2022
 */
public class InMemory implements RepositoryMeal {
  
  public static final int CALORIES_PER_DAY = 2000;
  private static final List<Meal> meals;
  
  static {
    meals = new ArrayList<>(Arrays.asList(
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение",
            100),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    ));
  }
  
  @Override
  public void add(Meal meal) {
    Meal newMeal = new Meal(meal.getDateTime(), meal.getDescription(), meal.getCalories());
    meals.add(newMeal);
  }
  
  @Override
  public void delete(int id) {
    meals.removeIf(meal -> meal.getId() == id);
  }
  
  @Override
  public void update(Meal meal) {
    Meal currMeal = getMealById(meal.getId());
    if (meal.getDateTime() != null) {
      currMeal.setDateTime(meal.getDateTime());
    }
    String mealDescription = meal.getDescription();
    if (mealDescription != null && !mealDescription.isEmpty()) {
      currMeal.setDescription(mealDescription);
    }
    if (meal.getCalories() != 0) {
      currMeal.setCallories(meal.getCalories());
    }
  }
  
  @Override
  public List<Meal> getAllMeals() {
    return meals;
  }
  
  @Override
  public Meal getMealById(int id) {
    return meals.stream().filter(meal -> meal.getId() == id).findFirst().get();
  }
}
