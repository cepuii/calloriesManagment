package edu.cepuii.caloriesmanagment.repository.dataJpa;

import edu.cepuii.caloriesmanagment.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

}
