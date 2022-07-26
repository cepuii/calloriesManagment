package edu.cepuii.calloriesmanagement.service;

import edu.cepuii.calloriesmanagement.model.Meal;
import edu.cepuii.calloriesmanagement.repository.MealRepository;
import edu.cepuii.calloriesmanagement.util.exception.NotFoundException;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @author cepuii on 19.07.2022
 */
@Service
public class MealService {
  
  private final MealRepository repository;
  
  public MealService(MealRepository repository) {
    this.repository = repository;
  }
  
  public Meal save(Meal meal, int userId) {
    Meal newMeal = repository.save(meal, userId);
    if (newMeal == null) {
      throw new NotFoundException("Meal not found");
    }
    return newMeal;
  }
  
  public boolean delete(int id, int userId) {
    boolean result = repository.delete(id, userId);
    if (!result) {
      throw new NotFoundException("Not found meal");
    }
    return true;
  }
  
  public Collection<Meal> getAllMeals(int userId) {
    return repository.getAll(userId);
  }
  
  public Meal getById(int id, int userId) {
    Meal meal = repository.getById(id, userId);
    if (meal == null) {
      throw new NotFoundException("Meal not found");
    }
    return meal;
  }
  
}
