package edu.cepuii.caloriesmanagment.util;

import org.springframework.lang.Nullable;

public final class FilterUtil {
  
  private FilterUtil() {
  }
  
  public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value,
      @Nullable T start, @Nullable T end) {
    return (start == null || value.compareTo(start) >= 0) &&
        (end == null || value.compareTo(end) < 0);
  }
  
}
