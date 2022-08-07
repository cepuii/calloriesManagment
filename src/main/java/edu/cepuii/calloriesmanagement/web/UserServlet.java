package edu.cepuii.calloriesmanagement.web;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cepuii on 16.07.2022
 */
@WebServlet(name = "users", urlPatterns = "/usersServlet")
public class UserServlet extends HttpServlet {
  
  private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    LOG.debug("redirect to users");
//    req.getRequestDispatcher("users.jsp").forward(req,resp);
    resp.sendRedirect("users.jsp");
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int userId = Integer.parseInt(req.getParameter("userId"));
    SecurityUtil.setAuthUserId(userId);
    resp.sendRedirect("mealsServlet");
  }
}
