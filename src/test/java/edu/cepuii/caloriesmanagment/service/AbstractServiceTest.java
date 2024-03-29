package edu.cepuii.caloriesmanagment.service;

import static org.slf4j.LoggerFactory.getLogger;

import edu.cepuii.caloriesmanagment.ActiveDbProfileResolver;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public abstract class AbstractServiceTest {
  
  private static final Logger log = getLogger("result");
  
  private static final StringBuilder results = new StringBuilder();
  
  @Rule
  public final Stopwatch stopwatch = new Stopwatch() {
    @Override
    protected void finished(long nanos, Description description) {
      String result = String.format("\n%-25s %7d", description.getMethodName(),
          TimeUnit.NANOSECONDS.toMillis(nanos));
      results.append(result);
      log.info(result + " ms\n");
    }
  };
  
  @AfterClass
  public static void printResult() {
    log.info("""
        ----------------------
        Test          Duration, ms
        """ + results + "\n----------------------");
  }
}
