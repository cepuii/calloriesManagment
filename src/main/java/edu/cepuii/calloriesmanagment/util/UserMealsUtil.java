package edu.cepuii.calloriesmanagment.util;

import edu.cepuii.calloriesmanagment.model.Meal;
import edu.cepuii.calloriesmanagment.model.MealTO;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author cepuii on 13.07.2022
 */
public class UserMealsUtil {
  
  public static void main(String[] args) {
    List<Meal> meals = Arrays.asList(
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение",
            100),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );
  
    List<MealTO> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0),
        LocalTime.of(12, 0), 2000);
    mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
  }
  
  public static List<MealTO> filteredByCycles(List<Meal> meals, LocalTime startTime,
      LocalTime endTime, int caloriesPerDay) {
    List<MealTO> mealsList = MealUtil.checkExcess(meals, caloriesPerDay);
    return mealsList.stream()
        .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime,
            endTime))
        .collect(Collectors.toList());
  }
  
  public static List<MealTO> filteredByStreams(List<Meal> meals,
      LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
    List<MealTO> mealsList = MealUtil.checkExcess(meals, caloriesPerDay);
    return mealsList.stream()
        .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime,
            endTime))
        .collect(Collectors.toList());
  }
  
  
}