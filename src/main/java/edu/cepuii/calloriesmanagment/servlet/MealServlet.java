package edu.cepuii.calloriesmanagment.servlet;

import edu.cepuii.calloriesmanagment.dao.InMemory;
import edu.cepuii.calloriesmanagment.model.MealTO;
import edu.cepuii.calloriesmanagment.util.MealUtil;
import java.io.IOException;
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
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<MealTO> meals = MealUtil.checkExcess(InMemory.getMeals(), InMemory.CALORIES_PER_DAY);
    req.setAttribute("meals", meals);
    req.getRequestDispatcher("meals.jsp").forward(req, resp);
  }
}
