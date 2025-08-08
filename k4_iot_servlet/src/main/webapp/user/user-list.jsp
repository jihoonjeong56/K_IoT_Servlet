<%@ page import="java.util.List" %>
<%@ page import="org.example.k4_iot_servlet.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25. 8. 8.
  Time: 오전 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List(user-list.jsp)</title>
</head>
<body>
<h2>User List</h2>
<table border="1">
    <tr>
        <%-- table row: 행 지정--%>
        <th>ID</th>
        <th>NAME</th>
        <th>EMAIL</th>
        <th>COUNTRY</th>
        <th>ACTIONS</th>
    </tr>
    <%
        //request 객체의 listUser 라는 이름으로 전달된 사용자 목록을 받아옴
        // >> HttpServletRequest 타입입을 형 변환
        List<User> listUser = (List<User>) request.getAttribute("listUser");
        if (listUser != null) {
            for (User user : listUser) {
    %>
    <tr>
        <td><%= user.getId()%></td>
        <td><%= user.getName()%></td>
        <td><%= user.getEmail()%></td>
        <td><%= user.getCountry()%></td>

        <td>
            <a href="edit?id=<%=user.getId()%>">Edit</a>
            <a href="delete?id=<%=user.getId()%>">Delete</a>
        </td>
    </tr>
    <%
            }
        }else {


    %>
    <tr>
        <td colspan="5">No User found.</td>
    </tr>
    <%
        } //else 문 종료
    %>
    <br/>
    <a href="new">Add new user</a>

</table>
</body>
</html>
