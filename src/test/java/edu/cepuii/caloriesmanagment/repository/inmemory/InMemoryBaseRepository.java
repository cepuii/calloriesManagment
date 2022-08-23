package edu.cepuii.caloriesmanagment.repository.inmemory;

import static edu.cepuii.caloriesmanagment.model.AbstractBaseEntity.START_SEQ;

import edu.cepuii.caloriesmanagment.model.AbstractBaseEntity;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryBaseRepository<T extends AbstractBaseEntity> {
  
  static final AtomicInteger counter = new AtomicInteger(START_SEQ);
  
  final Map<Integer, T> map = new ConcurrentHashMap<>();
  
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
  
  void put(T entity) {
    map.put(entity.getId(), entity);
  }
  
  public Collection<T> getCollection() {
    return map.values();
  }
}
