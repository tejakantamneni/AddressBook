<%--
  Created by IntelliJ IDEA.
  User: JParvathaneni
  Date: 1/21/16
  Time: 8:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Greeting</title>
</head>
<body>
<%
    String name = request.getParameter("greetName");
%>
<h1 style="color:firebrick">hello, <%=name%></h1>
</body>
</html>
