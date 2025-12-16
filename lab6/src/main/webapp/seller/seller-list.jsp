<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.lab6.model.Seller"%>
<%
    List<Seller> sellers = (List<Seller>) request.getAttribute("sellers");
    String title = (String) request.getAttribute("title");
%>
<html><head><title><%= title %></title></head><body>

<a href="../index.jsp">Меню</a> | <a href="new">Додати продавця</a>

<h1>Продавці</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Ім'я</th>
        <th>Телефон</th>
        <th>Email</th>
        <th>Дії</th>
    </tr>
    <% for (Seller seller: sellers) { %>
    <tr>
        <td><%= seller.getId() %></td>
        <td><%= seller.getName() %></td>
        <td><%= seller.getPhone() %></td>
        <td><%= seller.getEmail() %></td>
        <td>
            <a href="edit?id=<%= seller.getId() %>">Редагувати</a> |
            <a href="delete?id=<%= seller.getId() %>" onclick="return confirm('Видалити?')">Видалити</a>
        </td>
    </tr>
    <% } %>
</table>

</body></html>
