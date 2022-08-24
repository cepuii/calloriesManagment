package edu.cepuii.caloriesmanagment.service;

import edu.cepuii.caloriesmanagment.model.Meal;
import edu.cepuii.caloriesmanagment.repository.MealRepository;
import edu.cepuii.caloriesmanagment.util.DateTimeUtil;
import edu.cepuii.caloriesmanagment.util.ValidationUtil;
import edu.cepuii.caloriesmanagment.util.exception.NotFoundException;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.lang.Nullable;
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
  
  public Collection<Meal> getAll(int userId) {
    return repository.getAll(userId);
  }
  
  public Meal getById(int id, int userId) {
    Meal meal = repository.get(id, userId);
    if (meal == null) {
      throw new NotFoundException("Meal not found");
    }
    return meal;
  }
  
  public Collection<Meal> getBetweenInclusive(@Nullable LocalDate startDate,
      @Nullable LocalDate endDate, int userId) {
    return repository.getBetweenHalfOpen(DateTimeUtil.atStartOfDayOrMin(startDate),
        DateTimeUtil.atStartOfNextDayOrMax(endDate), userId);
  }
  
  public Meal getWithUser(int id, int userId) {
    return ValidationUtil.checkNotFoundWithId(repository.getWithUser(id, userId), id);
  }
}
