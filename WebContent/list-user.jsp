<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, com.yudis.inventory.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory App</title>
    </head>
  
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>FooBar University</h2>
            </div>
        </div>
        
        <div id="container">
            <div id="content">
                <table>
                    <tr>
                        <th>Username</th>
                        <th>Fullname</th>
                        <th>Email</th>
                    </tr>
                    <c:forEach var="user" items="${USER_LIST}">
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.fullname}</td>
                            <td>${user.email}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
