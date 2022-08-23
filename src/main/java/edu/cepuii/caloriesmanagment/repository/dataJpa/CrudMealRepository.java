package edu.cepuii.caloriesmanagment.repository.dataJpa;

import edu.cepuii.caloriesmanagment.model.Meal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
  
  @Transactional
  @Modifying
  int deleteByIdAndUser_Id(int id, int userId);
  
  Optional<Meal> findByIdAndUser_Id(int id, int userId);
  
  List<Meal> findAllByUser_IdOrderByDateTimeDesc(int userId);
  
  
  List<Meal> findAllByUser_IdAndAndDateTimeAfterAndAndDateTimeBeforeOrderByDateTimeDesc(int userId,
      LocalDateTime start, LocalDateTime end);
}
