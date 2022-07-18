package edu.cepuii.calloriesmanagment.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cepuii on 13.07.2022
 */
public class Meal {
  
  private final static AtomicInteger generateId = new AtomicInteger(1);
  private int id;
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
    this.id = generateId.getAndIncrement();
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
  
  @Override
  public String toString() {
    return "{ date time: " + getDateTime() + ", description: " + getDescription() + ", calorie: "
        + getCalories() + "}";
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
}
