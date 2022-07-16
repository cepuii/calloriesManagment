package edu.cepuii.calloriesmanagment.util;

import edu.cepuii.calloriesmanagment.model.Meal;
import edu.cepuii.calloriesmanagment.model.MealTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cepuii on 16.07.2022
 */
public class MealUtil {
  
  public static List<MealTO> checkExcess(List<Meal> meals, int perDays) {
    Map<LocalDate, Long> collect = meals.stream()
        .collect(Collectors.groupingBy(Meal::getDate,
            Collectors.summingLong(Meal::getCalories)));
    List<MealTO> result = new ArrayList<>();
    for (Meal meal : meals) {
      boolean excess = collect.get(meal.getDate()) > perDays;
      result.add(new MealTO(meal, excess));
    }
    return result;
  }
}
