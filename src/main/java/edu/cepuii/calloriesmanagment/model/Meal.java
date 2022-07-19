package edu.cepuii.calloriesmanagment.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author cepuii on 13.07.2022
 */
public class Meal {
  
  private Integer id;
  private LocalDateTime dateTime;
  private String description;
  private int callories;
  
  public Meal() {
  }
  
  public Meal(int id, LocalDateTime dateTime, String description, int callories) {
    this.id = id;
    this.dateTime = dateTime;
    this.description = description;
    this.callories = callories;
  }
  
  public Meal(LocalDateTime dateTime, String description, int callories) {
    this.id = null;
    this.dateTime = dateTime;
    this.description = description;
    this.callories = callories;
  }
  
  public void setCallories(int callories) {
    this.callories = callories;
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
    return callories;
  }
  
  public boolean isNew() {
    return id == null;
  }
  
  @Override
  public String toString() {
    return "{ id: " + getId() + ", date time: " + getDateTime() + ", description: "
        + getDescription() + ", calorie: "
        + getCalories() + "}";
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
}
