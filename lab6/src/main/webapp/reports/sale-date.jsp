<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.lab6.model.Sale"%>
<%
    List<Sale> sales = (List<Sale>) request.getAttribute("sales");
    String title = (String) request.getAttribute("title");
%>
<html><head><title><%= title %></title></head>
<a href="../index.jsp">Меню</a> | <a href="new">Нова угода</a>
<h1>Угоди</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Продавець</th>
        <th>Покупець</th>
        <th>Товар</th>
        <th>Дата</th>
        <th>Дії</th>
    </tr>
    <% for (Sale s : sales) { %>
    <tr>
        <td><%= s.getId() %></td>
        <td><%= s.getSeller().getName() %></td>
        <td><%= s.getClient().getName() %></td>
        <td><%= s.getProduct().getName() %></td>
        <td><%= s.getSaleDate() %></td>
        <td>
            <a href="edit?id=<%= s.getId() %>">Редагувати</a> |
            <a href="delete?id=<%= s.getId() %>" onclick="return confirm('Видалити?')">Видалити</a>
        </td>
    </tr>
    <% } %>
</table>
</body></html>
