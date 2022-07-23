package edu.cepuii.calloriesmanagement.repository.inmemory;

import edu.cepuii.calloriesmanagement.model.User;
import edu.cepuii.calloriesmanagement.repository.UserRepository;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author cepuii on 22.07.2022
 */
@Repository
public class InMemoryUserRepository implements UserRepository {
  
  private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
  
  private final static AtomicInteger generateId = new AtomicInteger(1);
  
  private final Map<Integer, User> repository = new ConcurrentHashMap<>();
  
  @Override
  public User save(User user) {
    if (user.isNew()) {
      user.setId(generateId.getAndIncrement());
      log.info("add new user, id: " + user.getId());
      repository.put(user.getId(), user);
      return user;
    }
    log.info("update user, id: " + user.getId());
    return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
  }
  
  @Override
  public boolean delete(int id) {
    log.info("delete user, id: " + id);
    return repository.remove(id, get(id));
  }
  
  @Override
  public User get(int id) {
    log.info("get user, id: " + id);
    return repository.get(id);
  }
  
  @Override
  public User getByEmail(String email) {
    log.info("get by mail " + email);
    return repository.values().stream()
        .filter(user -> user.getEmail().equals(email))
        .findAny()
        .get();
  }
  
  @Override
  public Collection<User> getAll() {
    log.info("get all");
    return repository.values();
  }
}
