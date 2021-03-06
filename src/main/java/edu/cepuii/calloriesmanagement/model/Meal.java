package edu.cepuii.calloriesmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author cepuii on 13.07.2022
 */
public class Meal extends AbstractBaseEntity {
  
  private LocalDateTime dateTime;
  private String description;
  private int calories;
  
  public Meal() {
    super(null);
  }
  
  public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
    super(id);
    this.dateTime = dateTime;
    this.description = description;
    this.calories = calories;
  }
  
  public Meal(LocalDateTime dateTime, String description, int calories) {
    this(null, dateTime, description, calories);
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
  
  public LocalTime getTime() {
    return dateTime.toLocalTime();
  }
}
