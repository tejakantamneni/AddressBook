package com.jags.web.servlet;

import com.jags.console.DBUtils;
import com.jags.dao.AddressDAO;
import com.jags.dao.impl.AddressDAOImpl;
import com.jags.model.Address;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by JParvathaneni on 1/21/16.
 */
public class HelloServlet implements Servlet {

    private static final Log LOG = LogFactory.getLog(HelloServlet.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        LOG.debug("in init method");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        PrintWriter writer = servletResponse.getWriter();
        writer.println("<h1>hello!!</h1>");
        AddressDAO addressDAO = new AddressDAOImpl();
        try {
            List<Address> allAddressList = addressDAO.getAllAddressList(DBUtils.createClosableConnection());
            for (Address address : allAddressList) {
                writer.println(address);
                writer.println("<br/>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        LOG.debug("in destroy method");
    }
}
