package edu.cepuii.caloriesmanagment;

import static edu.cepuii.caloriesmanagment.Profiles.REPOSITORY_IMPLEMENTATION;

import edu.cepuii.caloriesmanagment.web.meal.MealRestController;
import java.util.Arrays;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author cepuii on 22.07.2022
 */

public class SpringMain {
  
  public static void main(String[] args) {
    try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
    
      appCtx.getEnvironment()
          .setActiveProfiles(Profiles.getActiveDbProfile(), REPOSITORY_IMPLEMENTATION);
      appCtx.load("spring/spring-app.xml", "spring/inmemory.xml");
      appCtx.refresh();
      System.out.println(
          "Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
    
      MealRestController controller = appCtx.getBean(MealRestController.class);
      controller.getAll().forEach(System.out::println);
    }
  }
  
}
