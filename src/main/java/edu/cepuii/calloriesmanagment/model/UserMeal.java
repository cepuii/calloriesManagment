package edu.cepuii.calloriesmanagment.model;

import java.time.LocalDateTime;

/**
 * @author cepuii on 13.07.2022
 */
public class UserMeal {
  
  private final LocalDateTime dateTime;
  
  private final String description;
  
  private final int callories;
  
  public UserMeal(LocalDateTime dateTime, String description, int callories) {
    this.dateTime = dateTime;
    this.description = description;
    this.callories = callories;
  }
  
  public LocalDateTime getDateTime() {
    return dateTime;
  }
  
  public String getDescription() {
    return description;
  }
  
  public int getCallories() {
    return callories;
  }
}
