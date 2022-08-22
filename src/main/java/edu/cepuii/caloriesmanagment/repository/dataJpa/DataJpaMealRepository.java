package edu.cepuii.caloriesmanagment.repository.dataJpa;

import edu.cepuii.caloriesmanagment.model.Meal;
import edu.cepuii.caloriesmanagment.repository.MealRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public class DataJpaMealRepository implements MealRepository {
  
  private final CrudMealRepository mealRepository;
  private final CrudUserRepository userRepository;
  
  public DataJpaMealRepository(CrudMealRepository mealRepository,
      CrudUserRepository userRepository) {
    this.mealRepository = mealRepository;
    this.userRepository = userRepository;
  }
  
  @Override
  public Meal save(Meal meal, int userId) {
    if (!meal.isNew() && get(meal.getId(), userId) == null) {
      return null;
    }
    meal.setUser(userRepository.getReferenceById(userId));
    return mealRepository.save(meal);
  }
  
  @Override
  public boolean delete(int id, int userId) {
    return mealRepository.deleteByIdAndUser_Id(id, userId) != 0;
  }
  
  @Override
  public Meal get(int id, int userId) {
    return mealRepository.findByIdAndUser_Id(id, userId).orElse(null);
  }
  
  @Override
  public Collection<Meal> getAll(int userId) {
    return mealRepository.findAllByUser_IdOrderByDateTimeDesc(userId);
  }
  
  @Override
  public Collection<Meal> getBetweenHalfOpen(LocalDateTime start, LocalDateTime end, int userId) {
    return mealRepository.findAllByUser_IdAndAndDateTimeAfterAndAndDateTimeBeforeOrderByDateTimeDesc(
        userId, start, end);
  }
}
