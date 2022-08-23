package edu.cepuii.caloriesmanagment.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author cepuii on 19.07.2022
 */
@MappedSuperclass
public class AbstractNamedEntity extends AbstractBaseEntity {
  
  @NotBlank
  @Size(min = 2, max = 128)
  @Column(name = "name", nullable = false)
  private String name;
  
  protected AbstractNamedEntity() {
  }
  
  
  protected AbstractNamedEntity(Integer id, String name) {
    super(id);
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public String toString() {
    return super.toString() + '(' + name + ')';
  }
}
