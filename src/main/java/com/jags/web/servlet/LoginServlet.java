package com.jags.web.servlet;

import com.jags.console.DBUtils;
import com.jags.dao.UserDAO;
import com.jags.dao.impl.UserDAOImpl;
import com.jags.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by JParvathaneni on 1/24/16.
 */
public class LoginServlet extends HttpServlet {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String username = req.getParameter("j_username");
        String password = req.getParameter("j_password");
        //check against db and validate
        User authenticatedUser = null;
        try {
            authenticatedUser = userDAO.authenticate(DBUtils.createClosableConnection(), username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(authenticatedUser != null){//replace against db check
            resp.sendRedirect(req.getContextPath() + "/list/addresslist.jsp");
        }else{
            req.setAttribute("errorMessage", "Invalid username/password.");
            req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
        }
    }
}
