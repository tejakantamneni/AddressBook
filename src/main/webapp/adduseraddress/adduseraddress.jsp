<%@ page import="com.jags.model.Address" %>
<%@ page import="java.util.List" %><%--
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

<%
    Address addressToEdit = (Address) pageContext.findAttribute("addressToEdit");
    List<String> errorMessages = (List<String>) pageContext.findAttribute("errorMessages");
    if(errorMessages != null && !errorMessages.isEmpty()){
        out.println("<ul style=\"color:red\">");
        for(String msg : errorMessages){
%>
            <li><%=msg%></li>
<%
        }
        out.println("</ul>  ");
    }
%>

<form action="<%=config.getServletContext().getContextPath()%>\AddUserAddress" method="post">
    <div style="text-align: center">
    <h2> <%=addressToEdit == null ? "Add address" : "Edit " + addressToEdit.getDisplayName()%> </h2>
        <input type="hidden" value="<%=addressToEdit != null ? addressToEdit.getAddressId() : "-1"%>" name="addressId">

    <table align="center">
        <tr>
            <td>First Name:</td>
            <td><input type="text" name="firstname" value="<%=addressToEdit != null ? addressToEdit.getFirstName() : ""%>"/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input type="text" name="lastname" value="<%=addressToEdit != null ? addressToEdit.getLastName() : ""%>" /></td>
        </tr>
        <tr>
            <td>Address Line 1:</td>
            <td><input type="text" name="line1" value="<%=addressToEdit != null ? addressToEdit.getLine1() : ""%>" /></td>
        </tr>
        <tr>
            <td>Address Line 2:</td>
            <td><input type="text" name="line2" value="<%=addressToEdit != null ? addressToEdit.getLine2() : ""%>" /></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input type="text" name="city" value="<%=addressToEdit != null ? addressToEdit.getCity() : ""%>"/></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><input type="text" name="state" value="<%=addressToEdit != null ? addressToEdit.getState() : ""%>"/></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><input type="text" name="zip" value="<%=addressToEdit != null ? addressToEdit.getZip() : ""%>"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="<%=addressToEdit != null ? addressToEdit.getEmail() : ""%>"/></td>
        </tr>
        <tr>
            <td>Phone Number:</td>
            <td><input type="text" name="phonenumber" value="<%=addressToEdit != null ? addressToEdit.getPhoneNumber() : ""%>"/></td>
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

