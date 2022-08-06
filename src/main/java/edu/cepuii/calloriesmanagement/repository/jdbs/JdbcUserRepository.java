package edu.cepuii.calloriesmanagement.repository.jdbs;

import edu.cepuii.calloriesmanagement.model.User;
import edu.cepuii.calloriesmanagement.repository.UserRepository;
import java.util.Collection;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcUserRepository implements UserRepository {
  
  private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(
      User.class);
  private final JdbcTemplate jdbcTemplate;
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final SimpleJdbcInsert insertUser;
  
  public JdbcUserRepository(JdbcTemplate jdbcTemplate,
      NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.insertUser = new SimpleJdbcInsert(jdbcTemplate).withTableName("users")
        .usingGeneratedKeyColumns("id");
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }
  
  @Override
  public User save(User user) {
    SqlParameterSource map = new BeanPropertySqlParameterSource(user);
//        new MapSqlParameterSource().addValue("id", user.getId())
//        .addValue("name", user.getName()).addValue("email", user.getEmail())
//        .addValue("password", user.getPassword()).addValue("registered", user.getRegistered())
//        .addValue("enabled", user.isEnabled()).addValue("caloriesPerDay", user.getCaloriesPerDay());
    if (user.isNew()) {
      Number newKey = insertUser.executeAndReturnKey(map);
      user.setId(newKey.intValue());
    } else if (namedParameterJdbcTemplate.update("UPDATE users SET name=:name, email=:email, "
        + "password=:password, registered=:registered, enabled=:enabled, "
        + "calories_per_day=:caloriesPerDay WHERE id=:id", map) == 0) {
      return null;
    }
    return user;
  }
  
  @Override
  public boolean delete(int id) {
    return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
  }
  
  @Override
  public User get(int id) {
    return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
  }
  
  @Override
  public User getByEmail(String email) {
    return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
  }
  
  @Override
  public Collection<User> getAll() {
    return jdbcTemplate.query("SELECT * FROM users ORDER BY name, email", ROW_MAPPER);
  }
}
