package edu.cepuii.caloriesmanagment;

public class Profiles {
  
  public static final String JDBC = "jdbc";
  public static final String JPA = "jpa";
  
  public static final String REPOSITORY_IMPLEMENTATION = JPA;
  
  public static final String POSTGRES = "postgres";
  public static final String HSQL = "hsqldb";
  
  public static final String ACTIVE_DB = POSTGRES;
  
  private Profiles() {
  }
}
