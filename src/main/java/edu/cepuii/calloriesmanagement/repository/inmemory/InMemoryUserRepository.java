package edu.cepuii.calloriesmanagement.repository.inmemory;

import edu.cepuii.calloriesmanagement.model.User;
import edu.cepuii.calloriesmanagement.repository.UserRepository;
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
  
  static final int USER_ID = 1;
  static final int ADMIN_ID = 2;
  
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
