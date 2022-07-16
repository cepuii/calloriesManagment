package edu.cepuii.calloriesmanagment.util;

import edu.cepuii.calloriesmanagment.model.UserMeal;
import edu.cepuii.calloriesmanagment.model.UserMealWithExcess;
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
    List<UserMeal> meals = Arrays.asList(
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение",
            100),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );
    
    List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0),
        LocalTime.of(12, 0), 2000);
    mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
  }
  
  public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime,
      LocalTime endTime, int caloriesPerDay) {
    List<UserMealWithExcess> mealsList = MealUtil.checkExcess(meals, caloriesPerDay);
    return mealsList.stream()
        .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime,
            endTime))
        .collect(Collectors.toList());
  }
  
  public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals,
      LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
    List<UserMealWithExcess> mealsList = MealUtil.checkExcess(meals, caloriesPerDay);
    return mealsList.stream()
        .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime,
            endTime))
        .collect(Collectors.toList());
  }
  
  
}
