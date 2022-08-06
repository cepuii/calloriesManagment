package edu.cepuii.calloriesmanagement.repository.jdbc;

import edu.cepuii.calloriesmanagement.model.Meal;
import edu.cepuii.calloriesmanagement.repository.MealRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMealRepository implements MealRepository {
  
  @Override
  public Meal save(Meal meal, int userId) {
    return null;
  }
  
  @Override
  public boolean delete(int id, int userId) {
    return false;
  }
  
  @Override
  public Meal get(int id, int userId) {
    return null;
  }
  
  @Override
  public Collection<Meal> getAll(int userId) {
    return null;
  }
  
  @Override
  public Collection<Meal> getBetweenHalfOpen(LocalDateTime start, LocalDateTime end, int userId) {
    return null;
  }
}
