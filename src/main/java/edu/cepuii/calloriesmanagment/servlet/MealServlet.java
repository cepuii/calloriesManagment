package edu.cepuii.calloriesmanagment.servlet;

import edu.cepuii.calloriesmanagment.dao.InMemory;
import edu.cepuii.calloriesmanagment.dao.RepositoryMeal;
import edu.cepuii.calloriesmanagment.model.Meal;
import edu.cepuii.calloriesmanagment.model.MealTO;
import edu.cepuii.calloriesmanagment.util.MealUtil;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
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
  
  RepositoryMeal repository = new InMemory();
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String forward = "";
    String action = req.getParameter("action");
    if (action == null) {
      forward = "meals.jsp";
      List<MealTO> meals = MealUtil.checkExcess(repository.getAllMeals(),
          InMemory.CALORIES_PER_DAY);
      req.setAttribute("meals", meals);
      
    } else if (action.equalsIgnoreCase("update")) {
      forward = "addMeal.jsp";
      int id = Integer.parseInt(req.getParameter("id"));
      Meal meal = repository.getMealById(id);
      req.setAttribute("meal", meal);
      
    } else if (action.equalsIgnoreCase("delete")) {
      int id = Integer.parseInt(req.getParameter("id"));
      repository.delete(id);
      resp.sendRedirect("meals");
      return;
      
    } else if (action.equalsIgnoreCase("add")) {
      forward = "addMeal.jsp";
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
      repository.update(meal);
    } else {
      repository.add(meal);
    }
    resp.sendRedirect("meals");
  }
}
