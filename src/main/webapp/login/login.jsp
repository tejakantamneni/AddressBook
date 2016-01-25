<%--
  Created by IntelliJ IDEA.
  User: JParvathaneni
  Date: 1/24/16
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="<%=config.getServletContext().getContextPath()%>/Login" method="post">
    <div style="text-align: center">
        <center>
            <h2>Your credentials please</h2>
        <table>
            <tr>
                <td colspan="2" style="color: red">
                    <%=request.getAttribute("errorMessage")%>
                </td>
            </tr>
            <tr>
                <td>Login:</td>
                <td><input type="text" name="j_username"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="j_password"/></td>
            </tr>
            <tr>
                <td colspan="2">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <input type="submit" name="Login"/>
                </td>
            </tr>
        </table>
        </center>
    </div>
</form>
</body>
</html>
