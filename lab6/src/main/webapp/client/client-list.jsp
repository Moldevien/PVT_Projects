<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@ page import="com.lab6.model.Client" %>
<%
    List<Client> clients = (List<Client>) request.getAttribute("clients");
    String title = (String) request.getAttribute("title");
%>
<html><head><title><%= title %></title></head>
<body>
<a href="../index.jsp">Меню</a> | <a href="new">Додати клієнта</a>
<h1>Клієнти</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Ім'я</th>
        <th>Телефон</th>
        <th>Email</th>
        <th>Дії</th>
    </tr>
    <% for (Client client: clients) { %>
    <tr>
        <td><%= client.getId() %></td>
        <td><%= client.getName() %></td>
        <td><%= client.getPhone() %></td>
        <td><%= client.getEmail() %></td>
        <td>
            <a href="edit?id=<%= client.getId() %>">Редагувати</a> |
            <a href="delete?id=<%= client.getId() %>" onclick="return confirm('Видалити?')">Видалити</a>
        </td>
    </tr>
    <% } %>
</table>
</body></html>
