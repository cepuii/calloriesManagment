package edu.cepuii.calloriesmanagement.model;

import java.util.Objects;

/**
 * @author cepuii on 19.07.2022
 */
public abstract class AbstractBaseEntity {
  
  public static final int START_SEQ = 100000;
  
  protected Integer id;
  
  public AbstractBaseEntity() {
  }
  
  public AbstractBaseEntity(Integer id) {
    this.id = id;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public boolean isNew() {
    return id == null;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbstractBaseEntity that = (AbstractBaseEntity) o;
    return Objects.equals(id, that.id);
  }
  
  @Override
  public int hashCode() {
    return id == null ? 0 : id;
  }
  
  @Override
  public String toString() {
    return getClass().getSimpleName() + ":" + id;
  }
}
