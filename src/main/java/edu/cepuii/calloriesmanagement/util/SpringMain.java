package edu.cepuii.calloriesmanagement.util;

import edu.cepuii.calloriesmanagement.web.meal.MealRestController;
import java.util.Arrays;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cepuii on 22.07.2022
 */
public class SpringMain {
  
  public static void main(String[] args) {
    try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext(
        "spring/spring-app.xml")) {
      System.out.println(
          "Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
  
      MealRestController controller = appCtx.getBean(MealRestController.class);
      controller.getAll().forEach(System.out::println);
    }
  }
  
}
