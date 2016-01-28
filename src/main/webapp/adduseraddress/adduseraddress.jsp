<%@ page import="com.jags.model.Address" %><%--
  Created by IntelliJ IDEA.
  User: JParvathaneni
  Date: 1/27/16
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add User Address</title>
</head>
<body>
<form action="<%=config.getServletContext().getContextPath()%>\AddUserAddress" method="post">
    <div style="text-align: center">
    <h2> Add an Address </h2>

    <table align="center">
        <tr>
            <td>First Name:</td>
            <td><input type="text" name="firstname"/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input type="text" name="lastname"/></td>
        </tr>
        <tr>
            <td>Address Line 1:</td>
            <td><input type="text" name="line1"/></td>
        </tr>
        <tr>
            <td>Address Line 2:</td>
            <td><input type="text" name="line2"/></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input type="text" name="city"/></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><input type="text" name="state"/></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><input type="text" name="zip"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>Phone Number:</td>
            <td><input type="text" name="phonenumber"/></td>
        </tr>
        <tr>
            <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <input type="submit" name="AddUserAddress"/>
            </td>
    </table>
    </div>
</form>
</body>
</html>

