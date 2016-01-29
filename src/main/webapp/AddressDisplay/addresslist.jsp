<%@ page import="com.jags.model.Address" %>
<%@ page import="java.util.List" %><%--
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
<form action="<%=config.getServletContext().getContextPath()%>\DeleteUserAddress" method="post">
    <div style="text-align: center">

        <h2>List of Addresses</h2>
        <%
            List<Address> userAddressList = (List<Address>) request.getAttribute("userAddressList");
            if(userAddressList == null || userAddressList.isEmpty()){
        %>
        <h4>No addresses available.</h4>
        <%
        }else{
        %>
        <table style="width:75%" align="center" border="0">
            <tr align="right">
                <td>
                    <input type="submit" name="btnDelAddress" value="Delete Address"
                           style="border:thick" align="right">
                </td>
                <td>
                    <a href="<%=config.getServletContext().getContextPath()%>/adduseraddress/adduseraddress.jsp"
                       style="padding: 8px; background-color: gray; border-bottom-color: black;border-right-color: black;border: 1px">Add Address</a>
                    <%--
                                        <input type="button" name="btnAddAddress" value="Add Address"
                                               style="border:thick" align="right" onclick="openPage('adduseraddress/adduseraddress.jsp')"/>
                    --%>
                </td>
            </tr>
            <tr>
                <td colspan="2">&nbsp;</td>
            </tr>
        </table>
        <table style="width:75%" align="center" border="2">
            <thead>
            <tr>
                <th>SNo.</th>
                <th>Name</th>
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
            <%
                for(Address address : userAddressList){
            %>
            <tr>
                <td>
                    <input type="CHECKBOX" name="selAddressList" value="<%=address.getAddressId()%>">
                </td>
                <td>
                    <a href="<%=config.getServletContext().getContextPath()%>/EditAddress?id=<%=address.getAddressId()%>"> <%=address.getDisplayName()%></a>
                </td>
                <td>
                    <%=address.getLine1()%>
                </td>
                <td>
                    <%=address.getLine2()%>
                </td>
                <td>
                    <%=address.getCity()%>
                </td>
                <td>
                    <%=address.getState()%>
                </td>
                <td>
                    <%=address.getZip()%>
                </td>
                <td>
                    <%=address.getEmail()%>
                </td>
                <td>
                    <%=address.getPhoneNumber()%>
                </td>
            </tr>
            <%
                    }
                }
            %>

            </tbody>
        </table>

    </div>
</form>
<script type="text/javascript">

    function openPage(pageName){
        window.location.href = pageName;
    }

</script>
</body>
</html>
