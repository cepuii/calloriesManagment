package edu.cepuii.calloriesmanagement.model;

/**
 * @author cepuii on 19.07.2022
 */
public abstract class AbstractBaseEntity {
  
  protected Integer id;
  
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
  public String toString() {
    return getClass().getSimpleName() + ":" + id;
  }
}
