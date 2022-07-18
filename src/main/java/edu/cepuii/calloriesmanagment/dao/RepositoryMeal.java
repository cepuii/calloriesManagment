package edu.cepuii.calloriesmanagment.dao;

import edu.cepuii.calloriesmanagment.model.Meal;
import java.util.List;

/**
 * @author cepuii on 18.07.2022
 */
public interface RepositoryMeal {
  
  void add(Meal meal);
  
  void delete(int id);
  
  void update(Meal meal);
  
  List<Meal> getAllMeals();
  
  Meal getMealById(int id);
  
}
