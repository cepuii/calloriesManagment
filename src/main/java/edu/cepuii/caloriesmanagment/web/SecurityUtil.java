package edu.cepuii.caloriesmanagment.web;

import edu.cepuii.caloriesmanagment.model.AbstractBaseEntity;
import edu.cepuii.caloriesmanagment.util.MealUtil;

/**
 * @author cepuii on 19.07.2022
 */
public final class SecurityUtil {
  
  private static int userId = AbstractBaseEntity.START_SEQ;
  
  private SecurityUtil() {
  }
  
  public static int authUserId() {
    return userId;
  }
  
  public static int authUserCaloriesPerDay() {
    return MealUtil.DEFAULT_CALORIES_PER_DAY;
  }
  
  public static void setAuthUserId(int userId) {
    SecurityUtil.userId = userId;
  }
}
