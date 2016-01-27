package com.jags.web.servlet;

import com.jags.console.DBUtils;
import com.jags.dao.AddressDAO;
import com.jags.dao.impl.AddressDAOImpl;
import com.jags.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by JParvathaneni on 1/26/16.
 */
public class GetUserAddress extends HttpServlet {
    AddressDAO addressDAO = new AddressDAOImpl();

    private static final Log LOG = LogFactory.getLog(GetUserAddress.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loggedInUser = WebUtils.getLoggedInUser(req);
        if (loggedInUser != null) {
            try {
                addressDAO.getAllAddressList(DBUtils.createClosableConnection(), loggedInUser.getUserID());
            } catch (SQLException e) {
                LOG.error("Unable to load address list for user : " + loggedInUser.getUsername());
            }
        } else {
            req.setAttribute("errorMessage", "Please login to access your addresses.");
            req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
        }
    }
}