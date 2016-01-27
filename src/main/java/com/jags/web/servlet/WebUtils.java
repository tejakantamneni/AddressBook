package com.jags.web.servlet;

import com.jags.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by JParvathaneni on 1/26/16.
 */
public class WebUtils {

    public static User getLoggedInUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute(LoginServlet.LOGGED_IN_USER) == null){
            return null;
        }else{
            return (User) session.getAttribute(LoginServlet.LOGGED_IN_USER);
        }
    }
}
