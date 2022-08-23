package edu.cepuii.caloriesmanagment.repository.inmemory;

import static edu.cepuii.caloriesmanagment.UserTestData.ADMIN;
import static edu.cepuii.caloriesmanagment.UserTestData.GUEST;
import static edu.cepuii.caloriesmanagment.UserTestData.GUEST_ID;
import static edu.cepuii.caloriesmanagment.UserTestData.USER;

import edu.cepuii.caloriesmanagment.model.User;
import edu.cepuii.caloriesmanagment.repository.UserRepository;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author cepuii on 22.07.2022
 */
@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {
  
  private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
  
  public void init() {
    map.clear();
    put(USER);
    put(ADMIN);
    put(GUEST);
    counter.getAndSet(GUEST_ID + 1);
  }
  
  @Override
  public User getByEmail(String email) {
    log.info("get by mail " + email);
    return getCollection().stream()
        .filter(user -> user.getEmail().equals(email))
        .findFirst()
        .orElse(null);
  }
  
  @Override
  public Collection<User> getAll() {
    log.info("get all");
    return getCollection().stream()
        .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
        .collect(Collectors.toList());
  }
}
