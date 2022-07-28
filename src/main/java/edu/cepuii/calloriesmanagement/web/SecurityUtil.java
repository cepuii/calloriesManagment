package edu.cepuii.calloriesmanagement.web;

import edu.cepuii.calloriesmanagement.util.MealUtil;

/**
 * @author cepuii on 19.07.2022
 */
public class SecurityUtil {
  
  private static int userId = 1;
  
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
