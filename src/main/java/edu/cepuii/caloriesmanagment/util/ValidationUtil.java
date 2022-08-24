package edu.cepuii.caloriesmanagment.util;

import edu.cepuii.caloriesmanagment.model.AbstractBaseEntity;
import edu.cepuii.caloriesmanagment.util.exception.NotFoundException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;

/**
 * @author cepuii on 20.07.2022
 */
public final class ValidationUtil {
  
  private ValidationUtil() {
  }
  public static <T> T checkNotFoundWithId(T object, int id) {
    checkNotFoundWithId(object != null, id);
    return object;
  }
  public static void checkNotFoundWithId(boolean found, int id) {
    checkNotFound(found, "id=" + id);
  }
  public static <T> T checkNotFound(T object, String msg) {
    checkNotFound(object != null, msg);
    return object;
  }
  public static void checkNotFound(boolean found, String msg) {
    if (!found) {
      throw new NotFoundException("Not found entity with" + msg);
    }
  }
  public static void checkNew(AbstractBaseEntity entity) {
    if (!entity.isNew()) {
      throw new IllegalArgumentException(entity + "must be new (id==null)");
    }
  }
  
  public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
    if (entity.isNew()) {
      entity.setId(id);
    } else if (entity.getId() != id) {
      throw new IllegalArgumentException(entity + "must be with id=" + id);
    }
  }
  
  //  https://stackoverflow.com/a/65442410/548473
  @NonNull
  public static Throwable getRootCause(@NonNull Throwable t) {
    Throwable rootCause = NestedExceptionUtils.getRootCause(t);
    return rootCause != null ? rootCause : t;
  }
}
