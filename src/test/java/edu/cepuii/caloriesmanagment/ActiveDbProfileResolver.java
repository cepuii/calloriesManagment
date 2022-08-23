package edu.cepuii.caloriesmanagment;

import org.springframework.test.context.ActiveProfilesResolver;

public class ActiveDbProfileResolver implements ActiveProfilesResolver {
  
  @Override
  public String[] resolve(Class<?> testClass) {
    return new String[]{Profiles.getActiveDbProfile()};
  }
}
