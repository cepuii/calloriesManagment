package edu.cepuii.calloriesmanagment.web;

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
@WebServlet(urlPatterns = "/users")
public class UserServlet extends HttpServlet {
  
  private static final Logger log = LoggerFactory.getLogger(UserServlet.class);
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    log.debug("redirect to users");
//    req.getRequestDispatcher("users.jsp").forward(req,resp);
    resp.sendRedirect("users.jsp");
  }
}
