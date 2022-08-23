package edu.cepuii.caloriesmanagment.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

public final class DateTimeUtil {
  
  private static final LocalDateTime MIN_DATE = LocalDateTime.of(1, 1, 1, 0, 0);
  private static final LocalDateTime MAX_DATE = LocalDateTime.of(3001, 1, 1, 0, 0);
  
  private DateTimeUtil() {
  }
  
  public static LocalDateTime atStartOfDayOrMin(LocalDate startDate) {
    return startDate != null ? startDate.atStartOfDay() : MIN_DATE;
  }
  
  public static LocalDateTime atStartOfNextDayOrMax(LocalDate endDate) {
    return endDate != null ? endDate.plusDays(1).atStartOfDay() : MAX_DATE;
  }
  
  public static @Nullable LocalDate parseLocalDate(@Nullable String date) {
    return StringUtils.hasLength(date) ? LocalDate.parse(date) : null;
  }
  
  public static @Nullable LocalTime parseLocalTime(@Nullable String date) {
    return StringUtils.hasLength(date) ? LocalTime.parse(date) : null;
  }
}
