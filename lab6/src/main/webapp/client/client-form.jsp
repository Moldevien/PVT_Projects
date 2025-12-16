<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.lab6.model.Client" %>
<%
    Client client = (Client) request.getAttribute("client");
    boolean edit = client != null;
%>
<html><head><title><%= edit ? "Редагувати" : "Додати" %> клієнта</title></head><body>

<a href="list">Назад</a>

<h1><%= edit ? "Редагувати" : "Додати" %> клієнта</h1>

<form action="<%= edit ? "update" : "insert" %>" method="post">
    <% if (edit) { %>
    <input type="hidden" name="id" value="<%= client.getId() %>">
    <% } %>

    <label>Ім'я:</label>
    <input name="name" value="<%= edit ? client.getName() : "" %>" required><br>

    <label>Телефон:</label>
    <input name="phone" value="<%= edit ? client.getPhone() : "" %>" required><br>

    <label>Email:</label>
    <input name="email" value="<%= edit ? client.getEmail() : "" %>" required><br>
    
    <button type="submit"><%= edit ? "Оновити" : "Додати" %></button>
</form>

</body></html>
