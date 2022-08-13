package edu.cepuii.caloriesmanagment.repository.dataJpa;

import edu.cepuii.caloriesmanagment.model.Meal;
import edu.cepuii.caloriesmanagment.repository.MealRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public class DataJpaMealRepository implements MealRepository {
  
  private final CrudUserRepository userRepository;
  
  public DataJpaMealRepository(CrudUserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
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
