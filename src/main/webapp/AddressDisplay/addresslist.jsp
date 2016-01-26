<%--
  Created by IntelliJ IDEA.
  User: JParvathaneni
  Date: 1/25/16
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Addresses</title>
</head>
<body>
<form action="<%=config.getServletContext().getContextPath()%>\addressList" method="post">
    <div style="text-align: center">

        <h2>List of Addresses</h2>

        <table style="width:75%" align="center" border="2">
            <thead>
            <tr>
                <th>Sl. No.</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Address Line 1</th>
                <th>Address Line 2</th>
                <th>City</th>
                <th>State</th>
                <th>Zip</th>
                <th>Phone Number</th>
                <th>Email Address</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>

    </div>
</form>
</body>
</html>
