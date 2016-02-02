package com.jags.web.servlet;

import com.jags.dao.AddressDAO;
import com.jags.dao.impl.AddressDAOImpl;
import com.jags.dao.utils.DBUtils;
import com.jags.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by JParvathaneni on 1/28/16.
 */
public class DeleteUserAddressServlet extends HttpServlet {

    AddressDAO addressDAO = new AddressDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loggedInUser = WebUtils.getLoggedInUser(req);

        if (loggedInUser != null) {
            String[] result = req.getParameterValues("selAddressList");
            if (result.length == 1){
                String addressId = result[0];
                try {
                    addressDAO.deleteAddress(DBUtils.createClosableConnection(), String.valueOf(Integer.parseInt(addressId)));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resp.sendRedirect(req.getContextPath() + "/GetUserAddress");
            }else {
                for (int i = 0; i <= result.length-1; i++) {
                    String addressId = result[i];
                    try {
                        addressDAO.deleteAddress(DBUtils.createClosableConnection(), String.valueOf(Integer.parseInt(addressId)));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                resp.sendRedirect(req.getContextPath() + "/GetUserAddress");
            }
        } else {
            req.setAttribute("errorMessage", "Please login to add an address");
            req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
        }
    }
}