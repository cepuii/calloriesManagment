package edu.cepuii.calloriesmanagement.web;

import edu.cepuii.calloriesmanagement.model.Meal;
import edu.cepuii.calloriesmanagement.to.MealTO;
import edu.cepuii.calloriesmanagement.web.meal.MealRestController;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cepuii on 16.07.2022
 */
@WebServlet(name = "meals", urlPatterns = "/mealsServlet")
public class MealServlet extends HttpServlet {
  
  MealRestController restController;
  
  @Override
  public void init() throws ServletException {
    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
        "spring/spring-app.xml");
    restController = context.getBean(MealRestController.class);
    
  }
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String forward;
    String action = req.getParameter("action");
    switch (action == null ? "all" : action) {
      case "update": {
        forward = "addMeal.jsp";
        int id = Integer.parseInt(req.getParameter("id"));
        Meal meal = restController.get(id);
        req.setAttribute("meal", meal);
        break;
      }
      case "delete": {
        int id = Integer.parseInt(req.getParameter("id"));
        restController.delete(id);
        resp.sendRedirect("mealsServlet");
        return;
      }
      case "add": {
        forward = "addMeal.jsp";
        break;
      }
      case "all":
      default:
        forward = "meals.jsp";
        Collection<MealTO> meals = restController.getAll();
        req.setAttribute("meals", meals);
    }
    
    req.getRequestDispatcher(forward).forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    req.setCharacterEncoding("UTF-8");
    Meal meal = new Meal();
    meal.setDateTime(LocalDateTime.parse(req.getParameter("date")));
    meal.setDescription(req.getParameter("description"));
    meal.setCalories(Integer.parseInt(req.getParameter("calories")));
    
    String reqParameter = req.getParameter("id");
    if (reqParameter != null && !reqParameter.isEmpty()) {
      int id = Integer.parseInt(reqParameter);
      meal.setId(id);
    }
    restController.update(meal, SecurityUtil.authUserId());
    resp.sendRedirect("mealsServlet");
  }
}
