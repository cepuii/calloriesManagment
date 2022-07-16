package edu.cepuii.calloriesmanagment.util;

import edu.cepuii.calloriesmanagment.model.UserMeal;
import edu.cepuii.calloriesmanagment.model.UserMealWithExcess;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cepuii on 16.07.2022
 */
public class MealUtil {
  
  public static List<UserMealWithExcess> checkExcess(List<UserMeal> meals, int perDays) {
    Map<LocalDate, Long> collect = meals.stream()
        .collect(Collectors.groupingBy(UserMeal::getDate,
            Collectors.summingLong(UserMeal::getCalories)));
    List<UserMealWithExcess> result = new ArrayList<>();
    for (UserMeal meal : meals) {
      boolean excess = collect.get(meal.getDate()) > perDays;
      result.add(new UserMealWithExcess(meal, excess));
    }
    return result;
  }
}
