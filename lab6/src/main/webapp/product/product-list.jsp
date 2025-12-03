<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.lab6.model.Product"%>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    String title = (String) request.getAttribute("title");
%>
<html><head><title><%= title %></title></head>
<body>
<a href="../index.jsp">Меню</a> | <a href="new">Додати товар</a>
<h1>Товари</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Назва</th>
        <th>Ціна</th>
    </tr>
    <% for (Product product: products) { %>
    <tr>
        <td><%= product.getId() %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getPrice() %></td>
        <td>
            <a href="edit?id=<%= product.getId() %>">Редагувати</a> |
            <a href="delete?id=<%= product.getId() %>" onclick="return confirm('Видалити?')">Видалити</a>
        </td>
    </tr>
    <% } %>
</table>
</body></html>
