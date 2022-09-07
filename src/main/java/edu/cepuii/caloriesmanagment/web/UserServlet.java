package edu.cepuii.caloriesmanagment.web;

import edu.cepuii.caloriesmanagment.web.user.AdminRestController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author cepuii on 16.07.2022
 */
@WebServlet(name = "users", urlPatterns = "/usersServlet")
public class UserServlet extends HttpServlet {
  
  private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);
  
  private AdminRestController adminController;
  
  @Override
  public void init() throws ServletException {
    WebApplicationContext springContex = WebApplicationContextUtils.getRequiredWebApplicationContext(
        getServletContext());
    adminController = springContex.getBean(AdminRestController.class);
  }
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    LOG.debug("get all");
    req.setAttribute("users", adminController.getAll());
    req.getRequestDispatcher("users.jsp").forward(req, resp);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int userId = Integer.parseInt(req.getParameter("userId"));
    SecurityUtil.setAuthUserId(userId);
    resp.sendRedirect("mealsServlet");
  }
}
