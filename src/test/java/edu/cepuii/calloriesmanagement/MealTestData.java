package edu.cepuii.calloriesmanagement;

import static edu.cepuii.calloriesmanagement.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

import edu.cepuii.calloriesmanagement.model.Meal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MealTestData {
  
  public static final int FIND_MEAL = START_SEQ + 9;
  public static final int NOT_FOUND_FIND_MEAL = START_SEQ - 3;
  
  public static final Meal MEAL = new Meal(START_SEQ + 9, LocalDateTime.of(2020, 12, 19, 5, 0, 0),
      "User Afternoon snack", 108);
  
  private static Stream<Meal> getMeals() {
    return Stream.of(
        new Meal(START_SEQ + 5, LocalDateTime.of(2020, 12, 19, 19, 0, 0), "Admin Lunch", 828),
        new Meal(START_SEQ + 12, LocalDateTime.of(2020, 12, 20, 8, 0, 0), "User Breakfast", 1637),
        new Meal(START_SEQ + 3, LocalDateTime.of(2020, 12, 19, 10, 0, 0), "Admin Breakfast", 416),
        new Meal(START_SEQ + 7, LocalDateTime.of(2020, 12, 20, 12, 0, 0), "Admin Dinner", 828),
        new Meal(START_SEQ + 13, LocalDateTime.of(2020, 12, 20, 12, 0, 0), "User Dinner", 637),
        new Meal(START_SEQ + 6, LocalDateTime.of(2020, 12, 20, 6, 0, 0), "Admin Breakfast", 828),
        new Meal(START_SEQ + 4, LocalDateTime.of(2020, 12, 19, 16, 0, 0), "Admin Dinner", 628),
        new Meal(START_SEQ + 8, LocalDateTime.of(2020, 12, 20, 19, 0, 0), "Admin Lunch", 828),
        new Meal(START_SEQ + 9, LocalDateTime.of(2020, 12, 19, 5, 0, 0), "User Afternoon snack",
            108),
        new Meal(START_SEQ + 11, LocalDateTime.of(2020, 12, 19, 10, 0, 0), "User Breakfast", 637),
        new Meal(START_SEQ + 10, LocalDateTime.of(2020, 12, 19, 19, 0, 0), "User Lunch", 1007),
        new Meal(START_SEQ + 14, LocalDateTime.of(2020, 12, 20, 19, 0, 0), "User Lunch", 637),
        new Meal(START_SEQ + 15, LocalDateTime.of(2020, 12, 21, 19, 0, 0), "User Lunch", 637)
    );
  }
  
  public static Collection<Meal> getUserMeals() {
    return getMeals()
        .filter(meal -> meal.getDescription().startsWith("User"))
        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
        .collect(Collectors.toList());
  }
  
  public static Collection<Meal> getUserMealsWithFilter(LocalDate start, LocalDate end) {
    return getUserMeals().stream()
        .filter(meal -> meal.getDateTime().compareTo(start.atStartOfDay()) >= 0
            && meal.getDateTime().compareTo(end.plusDays(1).atStartOfDay()) < 0)
        .collect(Collectors.toList());
  }
  
  public static void assertMatch(Meal actual, Meal expected) {
    assertThat(actual).usingRecursiveComparison()
        .isEqualTo(expected);
  }
  
  public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
    assertMatch(actual, Arrays.asList(expected));
  }
  
  public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }
  
  public static Meal getUpdated() {
    Meal updated = MEAL;
    updated.setCalories(801);
    updated.setDescription("Updated");
    updated.setDateTime(LocalDateTime.of(1010, 1, 5, 1, 1, 1));
    return updated;
  }
  
  public static Meal getNew() {
    return new Meal(null, LocalDateTime.of(2000, 1, 1, 1, 1, 1), "new ", 777);
  }
  
}
