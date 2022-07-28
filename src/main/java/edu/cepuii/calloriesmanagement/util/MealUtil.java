package edu.cepuii.calloriesmanagement.util;

import edu.cepuii.calloriesmanagement.model.Meal;
import edu.cepuii.calloriesmanagement.to.MealTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cepuii on 16.07.2022
 */
public class MealUtil {
  
  public static final int DEFAULT_CALORIES_PER_DAY = 2000;
  private static final List<Meal> meals;
  
  static {
    meals = Arrays.asList(
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение",
            100),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );
  }
  
  public static List<Meal> getMeals() {
    return meals;
  }
  
  public static Collection<MealTO> getTos(Collection<Meal> meals, int perDays) {
    Map<LocalDate, Long> collect = meals.stream()
        .collect(Collectors.groupingBy(Meal::getDate,
            Collectors.summingLong(Meal::getCalories)));
    Collection<MealTO> result = new ArrayList<>();
    for (Meal meal : meals) {
      boolean excess = collect.get(meal.getDate()) > perDays;
      result.add(new MealTO(meal, excess));
    }
    return result;
  }
  
  public static Collection<MealTO> getFilteredTos(Collection<Meal> mealsDateFiltered,
      int authUserCaloriesPerDay, LocalTime startTime, LocalTime endTime) {
    List<Meal> meals = mealsDateFiltered.stream()
        .filter(meal -> Util.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
        .collect(Collectors.toList());
    return getTos(meals, authUserCaloriesPerDay);
  }
  
}
