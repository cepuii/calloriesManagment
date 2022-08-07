package edu.cepuii.calloriesmanagement.model;

import edu.cepuii.calloriesmanagement.util.MealUtil;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import org.springframework.util.CollectionUtils;

/**
 * @author cepuii on 19.07.2022
 */
public class User extends AbstractNamedEntity {
  
  private String email;
  private String password;
  private boolean enabled = true;
  private Date registered = new Date();
  private Collection<Role> roles;
  private int caloriesPerDay = MealUtil.DEFAULT_CALORIES_PER_DAY;
  
  public User() {
  }
  
  public User(User u) {
    this(u.getId(), u.getName(), u.email, u.password, u.enabled, u.registered, u.roles,
        u.caloriesPerDay);
  }
  
  public User(Integer id, String name, String email, String password, Role... roles) {
    this(id, name, email, password, true, new Date(),
        Arrays.asList(roles), MealUtil.DEFAULT_CALORIES_PER_DAY);
  }
  
  public User(Integer id, String name, String email, String password, boolean enabled,
      Date registered,
      Collection<Role> roles, int caloriesPerDay) {
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
  
  public Collection<Role> getRoles() {
    return roles;
  }
  
  public void setRoles(Collection<Role> roles) {
    this.roles =
        CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
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
        "id=" + this.getId() +
        ", email=" + email +
        ", name=" + getName() +
        ", enabled=" + enabled +
        ", roles=" + roles +
        ", caloriesPerDay=" + caloriesPerDay +
        ')';
  }
}