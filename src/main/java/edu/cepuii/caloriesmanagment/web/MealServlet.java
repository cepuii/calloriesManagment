package edu.cepuii.caloriesmanagment.web;

import edu.cepuii.caloriesmanagment.model.Meal;
import edu.cepuii.caloriesmanagment.to.MealTO;
import edu.cepuii.caloriesmanagment.util.DateTimeUtil;
import edu.cepuii.caloriesmanagment.web.meal.MealRestController;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author cepuii on 16.07.2022
 */
@WebServlet(name = "meals", urlPatterns = "/mealsServlet")
public class MealServlet extends HttpServlet {
  private MealRestController mealController;
  
  @Override
  public void init() {
    WebApplicationContext springContex = WebApplicationContextUtils.getRequiredWebApplicationContext(
        getServletContext());
    mealController = springContex.getBean(MealRestController.class);
  }
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String action = req.getParameter("action");
    switch (action == null ? "all" : action) {
      case "delete" -> {
        int id = Integer.parseInt(req.getParameter("id"));
        mealController.delete(id);
        resp.sendRedirect("mealsServlet");
      }
      case "update", "add" -> {
        final Meal meal = "add".equals(action) ?
            new Meal(LocalDateTime.now(), "", 500) :
            mealController.get(getId(req));
        req.setAttribute("meal", meal);
        req.getRequestDispatcher("/addMeal.jsp").forward(req, resp);
      }
      case "filter" -> {
        LocalDate startDate = DateTimeUtil.parseLocalDate(req.getParameter("startDate"));
        LocalDate endDate = DateTimeUtil.parseLocalDate(req.getParameter("endDate"));
        LocalTime startTime = DateTimeUtil.parseLocalTime(req.getParameter("startTime"));
        LocalTime endTime = DateTimeUtil.parseLocalTime(req.getParameter("endTime"));
        Collection<MealTO> between = mealController.getBetween(startDate, startTime, endDate,
            endTime);
        req.setAttribute("meals", between);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
      }
      default -> {
        req.setAttribute("meals", mealController.getAll());
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
      }
    }
  }
  
  private int getId(HttpServletRequest req) {
    return Integer.parseInt(req.getParameter("id"));
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    req.setCharacterEncoding("UTF-8");
    Meal meal = new Meal(
        LocalDateTime.parse(req.getParameter("date")),
        req.getParameter("description"),
        Integer.parseInt(req.getParameter("calories")));
    if (StringUtils.hasLength(req.getParameter("id"))) {
      mealController.update(meal, getId(req));
    } else {
      mealController.create(meal);
    }
    resp.sendRedirect("mealsServlet");
  }
}
