package edu.cepuii.calloriesmanagment.model;

import java.time.LocalDateTime;

/**
 * @author cepuii on 13.07.2022
 */
public class UserMealWithExcess extends UserMeal {
  
  private final boolean excess;
  
  public UserMealWithExcess(LocalDateTime dateTime, String description, int callories,
      boolean excess) {
    super(dateTime, description, callories);
    this.excess = excess;
  }
  
  public UserMealWithExcess(UserMeal meal, boolean excess) {
    super(meal.getDateTime(), meal.getDescription(), meal.getCalories());
    this.excess = excess;
  }
  
  @Override
  public String toString() {
    return super.toString().replace("}", "") + ", excess: " + excess + "}";
  }
}
