package edu.cepuii.calloriesmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author cepuii on 13.07.2022
 */
public class Meal extends AbstractBaseEntity {
  
  private LocalDateTime dateTime;
  private String description;
  private int calories;
  private int userId;
  
  public Meal() {
    super(null);
  }
  
  public Meal(Integer id, LocalDateTime dateTime, String description, int calories, int userId) {
    super(id);
    this.dateTime = dateTime;
    this.description = description;
    this.calories = calories;
    this.userId = userId;
  }
  
  public Meal(LocalDateTime dateTime, String description, int calories, int userId) {
    this(null, dateTime, description, calories, userId);
  }
  
  public int getUserId() {
    return userId;
  }
  
  public void setUserId(int userId) {
    this.userId = userId;
  }
  
  public void setCalories(int calories) {
    this.calories = calories;
  }
  
  public LocalDateTime getDateTime() {
    return dateTime;
  }
  
  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }
  
  public LocalDate getDate() {
    return dateTime.toLocalDate();
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public int getCalories() {
    return calories;
  }
  
  public boolean isNew() {
    return id == null;
  }
  
  @Override
  public String toString() {
    return super.toString() + ", date time: " + getDateTime() + ", description: "
        + getDescription() + ", calories: "
        + getCalories() + "}";
  }
}
