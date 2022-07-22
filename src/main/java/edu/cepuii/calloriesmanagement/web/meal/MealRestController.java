package edu.cepuii.calloriesmanagement.web.meal;

import edu.cepuii.calloriesmanagement.service.MealService;
import org.springframework.stereotype.Controller;

/**
 * @author cepuii on 20.07.2022
 */
@Controller
public class MealRestController {
  
  private final MealService service;
  
  public MealRestController(MealService service) {
    this.service = service;
  }
}
