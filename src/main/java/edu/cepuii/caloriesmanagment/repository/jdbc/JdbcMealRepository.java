package edu.cepuii.caloriesmanagment.repository.jdbc;

import edu.cepuii.caloriesmanagment.model.Meal;
import edu.cepuii.caloriesmanagment.repository.MealRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMealRepository implements MealRepository {
  
  private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(
      Meal.class);
  private final JdbcTemplate jdbcTemplate;
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final SimpleJdbcInsert insertMeal;
  
  public JdbcMealRepository(JdbcTemplate jdbcTemplate,
      NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.insertMeal = new SimpleJdbcInsert(jdbcTemplate).withTableName("meals")
        .usingGeneratedKeyColumns("id");
  }
  
  @Override
  public Meal save(Meal meal, int userId) {
    SqlParameterSource map = new MapSqlParameterSource()
        .addValue("id", meal.getId())
        .addValue("dateTime", meal.getDateTime())
        .addValue("description", meal.getDescription())
        .addValue("calories", meal.getCalories())
        .addValue("userId", userId);
    if (meal.isNew()) {
      Number newId = insertMeal.executeAndReturnKey(map);
      meal.setId(newId.intValue());
    } else if (namedParameterJdbcTemplate.update(
        "UPDATE meals SET date_time=:dateTime, description=:description, calories=:calories WHERE id=:id AND user_id=:userId",
        map) == 0) {
      return null;
    }
    return meal;
  }
  
  @Override
  public boolean delete(int id, int userId) {
    return jdbcTemplate.update("DELETE FROM meals WHERE id=? AND user_id=?", id, userId) != 0;
  }
  
  @Override
  public Meal get(int id, int userId) {
    return jdbcTemplate.queryForObject("SELECT * FROM meals WHERE id=? AND user_id=?", ROW_MAPPER,
        id, userId);
  }
  
  @Override
  public Collection<Meal> getAll(int userId) {
    return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=? ORDER BY date_time DESC ",
        ROW_MAPPER, userId);
  }
  
  @Override
  public Collection<Meal> getBetweenHalfOpen(LocalDateTime start, LocalDateTime end, int userId) {
    return jdbcTemplate.query(
        "SELECT * FROM meals WHERE user_id = ? AND date_time >= ? AND date_time < ? ORDER BY date_time DESC",
        ROW_MAPPER, userId, start, end);
  
  }
}
