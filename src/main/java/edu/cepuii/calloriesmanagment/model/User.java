package edu.cepuii.calloriesmanagment.model;

import edu.cepuii.calloriesmanagment.util.MealUtil;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author cepuii on 19.07.2022
 */
public class User extends AbstractNamedEntity {
  
  private String email;
  private String password;
  private boolean enabled = true;
  private Date registered = new Date();
  private Set<Role> roles;
  private int caloriesPerDay = MealUtil.DEFAULT_CALORIES_PER_DAY;
  
  public User(Integer id, String name, String email, String password, Role role, Role... roles) {
    this(id, name, email, password, true, new Date(),
        EnumSet.of(role, roles), MealUtil.DEFAULT_CALORIES_PER_DAY);
  }
  
  public User(Integer id, String name, String email, String password, boolean enabled,
      Date registered,
      Set<Role> roles, int caloriesPerDay) {
    super(id, name);
    this.email = email;
    this.password = password;
    this.enabled = enabled;
    this.registered = registered;
    this.roles = roles;
    this.caloriesPerDay = caloriesPerDay;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public boolean isEnabled() {
    return enabled;
  }
  
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
  
  public Date getRegistered() {
    return registered;
  }
  
  public void setRegistered(Date registered) {
    this.registered = registered;
  }
  
  public Set<Role> getRoles() {
    return roles;
  }
  
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
  
  public int getCaloriesPerDay() {
    return caloriesPerDay;
  }
  
  public void setCaloriesPerDay(int caloriesPerDay) {
    this.caloriesPerDay = caloriesPerDay;
  }
  
  @Override
  public String toString() {
    return "User (" +
        "id=" + id +
        ", email=" + email +
        ", name=" + name +
        ", enabled=" + enabled +
        ", roles=" + roles +
        ", caloriesPerDay=" + caloriesPerDay +
        ')';
  }
}