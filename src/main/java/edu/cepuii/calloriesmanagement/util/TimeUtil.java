package edu.cepuii.calloriesmanagement.util;

import java.time.LocalTime;

/**
 * @author cepuii on 16.07.2022
 */

public class TimeUtil {
  
  public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
    return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
  }
}

