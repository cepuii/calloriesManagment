package edu.cepuii.calloriesmanagement.repository;

import edu.cepuii.calloriesmanagement.model.Meal;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author cepuii on 18.07.2022
 */
public interface MealRepository {
  
  Meal save(Meal meal, int userId);
  
  boolean delete(int id, int userId);
  
  Meal get(int id, int userId);
  
  Collection<Meal> getAll(int userId);
  
  Collection<Meal> getBetweenHalfOpen(LocalDateTime start, LocalDateTime end, int userId);
  
}
