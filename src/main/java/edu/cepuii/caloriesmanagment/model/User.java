package edu.cepuii.caloriesmanagment.model;

import static edu.cepuii.caloriesmanagment.util.MealUtil.DEFAULT_CALORIES_PER_DAY;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.CollectionUtils;

/**
 * @author cepuii on 19.07.2022
 */
@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "User.DELETE", query = "DELETE FROM User u WHERE u.id=:id"),
    @NamedQuery(name = "User.BY_EMAIL", query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
    @NamedQuery(name = "User.ALL_SORTED", query = "SELECT u FROM User u ORDER BY u.name, u.email")
})
public class User extends AbstractNamedEntity {
  
  public static final String DELETE = "User.DELETE";
  public static final String BY_EMAIL = "User.BY_EMAIL";
  public static final String ALL_SORTED = "User.ALL_SORTED";
  @Column(name = "email", nullable = false, unique = true)
  @Email
  @NotBlank
  @Size(max = 128)
  private String email;
  @Column(name = "password", nullable = false)
  @NotBlank
  @Size(min = 5, max = 128)
  private String password;
  @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
  private boolean enabled = true;
  @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
  @NotNull
  private Date registered = new Date();
  @Enumerated(EnumType.STRING)
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_roles")})
  @Column(name = "role")
  @ElementCollection(fetch = FetchType.EAGER)
  @BatchSize(size = 200)
  @JoinColumn(name = "user_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Collection<Role> roles;
  @Column(name = "calories_per_day", nullable = false, columnDefinition = "int default 2000")
  @Range(min = 10, max = 10000)
  private int caloriesPerDay = DEFAULT_CALORIES_PER_DAY;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//, cascade = CascadeType.REMOVE, orphanRemoval = true)
  @OrderBy("dateTime DESC")
  @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
  private List<Meal> meals;
  
  public User() {
  }
  
  public User(User u) {
    this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRegistered(),
        u.getRoles(),
        u.getCaloriesPerDay());
  }
  
  public User(Integer id, String name, String email, String password, Role... roles) {
    this(id, name, email, password, true, new Date(),
        Arrays.asList(roles), DEFAULT_CALORIES_PER_DAY);
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
  
  public List<Meal> getMeals() {
    return meals;
  }
  
  @Override
  public String toString() {
    return "User (" +
        "id=" + this.getId() +
        ", email=" + getEmail() +
        ", name=" + getName() +
        ", enabled=" + isEnabled() +
        ", roles=" + getRoles() +
        ", caloriesPerDay=" + getCaloriesPerDay() +
        ')';
  }
}