package edu.cepuii.calloriesmanagement.model;

import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

/**
 * @author cepuii on 19.07.2022
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity {
  
  public static final int START_SEQ = 100000;
  @Id
  @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
  private Integer id;
  
  protected AbstractBaseEntity() {
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
