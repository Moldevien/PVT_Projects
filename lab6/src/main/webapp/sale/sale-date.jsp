<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.lab6.model.Sale"%>
<%
    List<Sale> sales = (List<Sale>) request.getAttribute("sales");
    String title = (String) request.getAttribute("title");
%>
<html><head><title><%= title %></title></head><body>

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
    <% for (Sale sale : sales) { %>
    <tr>
        <td><%= sale.getId() %></td>
        <td><%= sale.getSeller().getName() %></td>
        <td><%= sale.getClient().getName() %></td>
        <td><%= sale.getProduct().getName() %></td>
        <td><%= sale.getSaleDate() %></td>
        <td>
            <a href="edit?id=<%= sale.getId() %>">Редагувати</a> |
            <a href="delete?id=<%= sale.getId() %>" onclick="return confirm('Видалити?')">Видалити</a>
        </td>
    </tr>
    <% } %>
</table>

</body></html>
