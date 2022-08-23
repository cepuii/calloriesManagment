package edu.cepuii.caloriesmanagment;

import static edu.cepuii.caloriesmanagment.Profiles.ACTIVE_DB;
import static edu.cepuii.caloriesmanagment.Profiles.REPOSITORY_IMPLEMENTATION;

import edu.cepuii.caloriesmanagment.web.meal.MealRestController;
import java.util.Arrays;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author cepuii on 22.07.2022
 */
@ActiveProfiles({ACTIVE_DB, REPOSITORY_IMPLEMENTATION})
public class SpringMain {
  
  public static void main(String[] args) {
    try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext(
        "spring/spring-app.xml", "spring/inmemory.xml")) {
      System.out.println(
          "Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
  
      MealRestController controller = appCtx.getBean(MealRestController.class);
      controller.getAll().forEach(System.out::println);
    }
  }
  
}
