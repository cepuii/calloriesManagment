package edu.cepuii.calloriesmanagment.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author cepuii on 13.07.2022
 */
public class Meal {
  
  private final LocalDateTime dateTime;
  
  private final String description;
  
  private final int callories;
  
  public Meal(LocalDateTime dateTime, String description, int callories) {
    this.dateTime = dateTime;
    this.description = description;
    this.callories = callories;
  }
  
  public LocalDateTime getDateTime() {
    return dateTime;
  }
  
  public LocalDate getDate() {
    return dateTime.toLocalDate();
  }
  
  public String getDescription() {
    return description;
  }
  
  public int getCalories() {
    return callories;
  }
  
  @Override
  public String toString() {
    return "{ date time: " + getDateTime() + ", description: " + getDescription() + ", calorie: "
        + getCalories() + "}";
  }
}
