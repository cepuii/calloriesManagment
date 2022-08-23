package edu.cepuii.caloriesmanagment;

import org.springframework.util.ClassUtils;

public class Profiles {
  
  public static final String JDBC = "jdbc";
  public static final String JPA = "jpa";
  public static final String DATAJPA = "dataJpa";
  
  public static final String REPOSITORY_IMPLEMENTATION = DATAJPA;
  
  public static final String POSTGRES = "postgres";
  public static final String HSQL = "hsqldb";
  
  public static final String ACTIVE_DB = POSTGRES;
  
  private Profiles() {
  }
  
  public static String getActiveDbProfile() {
    if (ClassUtils.isPresent("org.postgresql.Driver", null)) {
      return POSTGRES;
    } else if (ClassUtils.isPresent("org.hsqldb.jdbcDriver", null)) {
      return HSQL;
    } else {
      throw new IllegalStateException("Could not find DB driver");
    }
  }
}
