package com.jags.web.servlet;

import com.jags.dao.AddressDAO;
import com.jags.dao.impl.AddressDAOImpl;
import com.jags.dao.utils.DBUtils;
import com.jags.model.Address;
import com.jags.model.User;
import com.jags.web.validator.AddressValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by JParvathaneni on 1/27/16.
 */
public class AddUserAddressServlet extends HttpServlet {

    private static final Log LOG = LogFactory.getLog(AddUserAddressServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        User loggedInUser = WebUtils.getLoggedInUser(req);

        Address address = new Address();
        AddressValidator addressValidator = new AddressValidator();
        AddressDAO addressDAO = new AddressDAOImpl();

        if(loggedInUser != null){

            address.setUserId(loggedInUser.getUserID());
            address.setAddressId(Integer.parseInt(req.getParameter("addressId")));
            address.setFirstName(req.getParameter("firstname"));
            address.setLastName(req.getParameter("lastname"));
            address.setLine1(req.getParameter("line1"));
            address.setLine2(req.getParameter("line2"));
            address.setCity(req.getParameter("city"));
            address.setState(req.getParameter("state"));
            address.setZip(req.getParameter("zip"));
            address.setEmail(req.getParameter("email"));
            address.setPhoneNumber(req.getParameter("phonenumber"));

            List<String> errors = addressValidator.validate(address);
            if(errors != null && !errors.isEmpty() ){
                //show errors
                req.setAttribute("errorMessages", errors);
                req.getRequestDispatcher("/adduseraddress/adduseraddress.jsp").forward(req, resp);
                return;
            }

            try {
                if(address.getAddressId() <= 0 ){
                    addressDAO.createAddress(DBUtils.createClosableConnection(), address);
                }else{
                    addressDAO.replaceEditTerm(DBUtils.createClosableConnection(), address);
                }
            } catch (SQLException e) {
                LOG.debug("Unable to add address", e);
            }
            LOG.debug("Redirecting to list of addresses");
            resp.sendRedirect(req.getContextPath() + "/GetUserAddress");
        }else{
            req.setAttribute("errorMessage", "Please login to add an address");
            req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
        }
    }

}
