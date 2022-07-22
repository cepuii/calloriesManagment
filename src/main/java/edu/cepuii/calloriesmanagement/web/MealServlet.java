package edu.cepuii.calloriesmanagement.web;

import edu.cepuii.calloriesmanagement.model.Meal;
import edu.cepuii.calloriesmanagement.model.MealTO;
import edu.cepuii.calloriesmanagement.repository.MealRepository;
import edu.cepuii.calloriesmanagement.repository.inmemory.InMemoryMealRepository;
import edu.cepuii.calloriesmanagement.util.MealUtil;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cepuii on 16.07.2022
 */
@WebServlet(urlPatterns = "/meals")
public class MealServlet extends HttpServlet {
  
  MealRepository repository = new InMemoryMealRepository();
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String forward = "";
    String action = req.getParameter("action");
    switch (action == null ? "all" : action) {
      case "update": {
        forward = "addMeal.jsp";
        int id = Integer.parseInt(req.getParameter("id"));
        Meal meal = repository.getMealById(id);
        req.setAttribute("meal", meal);
        break;
      }
      case "delete": {
        int id = Integer.parseInt(req.getParameter("id"));
        repository.delete(id);
        resp.sendRedirect("meals");
        return;
      }
      case "add": {
        forward = "addMeal.jsp";
        break;
      }
      case "all":
      default:
        forward = "meals.jsp";
        Collection<MealTO> meals = MealUtil.checkExcess(repository.getAllMeals(),
            InMemoryMealRepository.CALORIES_PER_DAY);
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
    meal.setCallories(Integer.parseInt(req.getParameter("calories")));
    
    String reqParameter = req.getParameter("id");
    if (reqParameter != null && !reqParameter.isEmpty()) {
      int id = Integer.parseInt(reqParameter);
      meal.setId(id);
    }
    repository.save(meal);
    resp.sendRedirect("meals");
  }
}
