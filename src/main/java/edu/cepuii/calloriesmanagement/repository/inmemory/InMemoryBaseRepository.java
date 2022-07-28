package edu.cepuii.calloriesmanagement.repository.inmemory;

import edu.cepuii.calloriesmanagement.model.AbstractBaseEntity;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryBaseRepository<T extends AbstractBaseEntity> {
  
  private static final AtomicInteger counter = new AtomicInteger(0);
  
  private final Map<Integer, T> map = new ConcurrentHashMap<>();
  
  public T save(T entity) {
    if (entity.isNew()) {
      entity.setId(counter.getAndIncrement());
      map.put(entity.getId(), entity);
      return entity;
    }
    return map.computeIfPresent(entity.getId(), (id, oldEntity) -> entity);
  }
  
  public boolean delete(int id) {
    return map.remove(id) != null;
  }
  
  public T get(int id) {
    return map.get(id);
  }
  
  public Collection<T> getCollection() {
    return map.values();
  }
}
