package com.jags.web.servlet;

import com.jags.dao.AddressDAO;
import com.jags.dao.impl.AddressDAOImpl;
import com.jags.dao.utils.DBUtils;
import com.jags.model.Address;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by JParvathaneni on 1/28/16.
 */
public class EditAddressServlet extends HttpServlet {

    AddressDAO addressDAO = new AddressDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int addressId = Integer.parseInt(req.getParameter("id"));
        Address addressToEdit = null;
        try {
            addressToEdit = addressDAO.findMatchingAddress(DBUtils.createClosableConnection(), addressId).orElse(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(addressToEdit == null){
            throw new RuntimeException("Address not found. Are you trying to change the URL?");
        }
        req.setAttribute("addressToEdit", addressToEdit);
        req.getRequestDispatcher("/adduseraddress/adduseraddress.jsp").forward(req, resp);
    }
}
