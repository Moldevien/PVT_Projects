<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.lab6.model.Product" %>
<%
    Product product = (Product) request.getAttribute("product");
    boolean edit = product != null;
%>
<html><head><title><%= edit ? "Редагувати" : "Додати" %> товар</title></head>
<body>
<a href="list">Назад</a>
<h1><%= edit ? "Редагувати" : "Додати" %> товар</h1>
<form action="<%= edit ? "update" : "insert" %>" method="post">
    <% if (edit) { %>
    <input type="hidden" name="id" value="<%= product.getId() %>">
    <% } %>

    <label>Назва:</label>
    <input name="name" value="<%= edit ? product.getName() : "" %>" required>
    <br>

    <label>Ціна:</label>
    <input name="price" value="<%= edit ? product.getPrice() : "" %>">
    
    <button type="submit"><%= edit ? "Оновити" : "Додати" %></button>
</form>
</body>
</html>
