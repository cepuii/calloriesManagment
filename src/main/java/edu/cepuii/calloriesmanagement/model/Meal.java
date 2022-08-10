package edu.cepuii.calloriesmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

/**
 * @author cepuii on 13.07.2022
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Meal.DELETE", query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId"),
    @NamedQuery(name = "Meal.GET_ALL_SORT", query = "SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC "),
    @NamedQuery(name = "Meal.GET_BY_FILTER", query = "SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime>=:start AND m.dateTime<:end ORDER BY m.dateTime DESC")
})
@Table(name = "meals", uniqueConstraints = {
    @UniqueConstraint(name = "user_id_date_time", columnNames = {"user_id", "date_time"})})
public class Meal extends AbstractBaseEntity {
  
  public static final String DELETE = "Meal.DELETE";
  public static final String GET_ALL_SORT = "Meal.GET_ALL_SORT";
  public static final String GET_BY_FILTER = "Meal.GET_BY_FILTER";
  
  @Column(name = "date_time", nullable = false, unique = true)
  @NotNull
  private LocalDateTime dateTime;
  
  @Column(name = "description", nullable = false)
  @NotBlank
  @Size(min = 2, max = 128)
  private String description;
  
  @Column(name = "calories", nullable = false)
  @Range(min = 10, max = 500)
  private int calories;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @NotNull
  private User user;
  
  public Meal() {
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
    return super.getId() == null;
  }
  
  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
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
