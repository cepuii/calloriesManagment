package edu.cepuii.calloriesmanagment.model;

/**
 * @author cepuii on 19.07.2022
 */
public class AbstractNamedEntity extends AbstractBaseEntity {
  
  protected String name;
  
  public AbstractNamedEntity(Integer id, String name) {
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
