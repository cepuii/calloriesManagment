package edu.cepuii.calloriesmanagment.model;

import java.time.LocalDateTime;

/**
 * @author cepuii on 13.07.2022
 */
public class MealTO {
  
  private final LocalDateTime dateTime;
  
  private final String description;
  
  private final int calories;
  private final boolean excess;
  
  public MealTO(LocalDateTime dateTime, String description, int callories, boolean excess) {
    this.dateTime = dateTime;
    this.description = description;
    this.calories = callories;
    this.excess = excess;
  }
  
  public MealTO(Meal meal, boolean excess) {
    this.dateTime = meal.getDateTime();
    this.description = meal.getDescription();
    this.calories = meal.getCalories();
    this.excess = excess;
  }
  
  public LocalDateTime getDateTime() {
    return dateTime;
  }
  
  public String getDescription() {
    return description;
  }
  
  public int getCalories() {
    return calories;
  }
  
  public boolean isExcess() {
    return excess;
  }
  
  @Override
  public String toString() {
    return "{ date time: " + getDateTime() + ", description: " + getDescription() + ", calorie: "
        + getCalories() + ", excess: " + excess + "}";
  }
}
