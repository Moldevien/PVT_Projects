<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.lab6.model.Seller" %>
<%
    Seller seller = (Seller) request.getAttribute("seller");
    boolean edit = seller != null;
%>
<html><head><title><%= edit ? "Редагувати" : "Додати" %> продавця</title></head>
<body>
<a href="list">Назад</a>
<h1><%= edit ? "Редагувати" : "Додати" %> продавця</h1>
<form action="<%= edit ? "update" : "insert" %>" method="post">
    <% if (edit) { %>
    <input type="hidden" name="id" value="<%= seller.getId() %>">
    <% } %>

    <label>Ім'я: </label>
    <input name="name" value="<%= edit ? seller.getName() : "" %>" required>
    <br>

    <label>Телефон:</label>
    <input name="phone" value="<%= edit ? seller.getPhone() : "" %>">
    <br>

    <label>Email:</label>
    <input name="email" value="<%= edit ? seller.getEmail() : "" %>">
    <br>
    
    <button type="submit"><%= edit ? "Оновити" : "Додати" %></button>
</form>
</body>
</html>
