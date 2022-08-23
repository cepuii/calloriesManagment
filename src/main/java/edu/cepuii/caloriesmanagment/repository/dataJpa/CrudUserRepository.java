package edu.cepuii.caloriesmanagment.repository.dataJpa;

import edu.cepuii.caloriesmanagment.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
  
  @Transactional
  @Modifying
  @Query("DELETE FROM User u WHERE u.id=:id")
  int delete(@Param("id") int id);
  
  Optional<User> findUserByEmail(String email);
}
