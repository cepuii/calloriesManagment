package edu.cepuii.calloriesmanagement.service;

import edu.cepuii.calloriesmanagement.repository.MealRepository;
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
  
  public MealRepository getRepository() {
    return repository;
  }
}
