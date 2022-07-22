package edu.cepuii.calloriesmanagement.repository;

import edu.cepuii.calloriesmanagement.model.Meal;
import java.util.Collection;

/**
 * @author cepuii on 18.07.2022
 */
public interface MealRepository {
  
  Meal save(Meal meal);
  
  boolean delete(int id);
  
  Collection<Meal> getAllMeals();
  
  Meal getMealById(int id);
  
}
