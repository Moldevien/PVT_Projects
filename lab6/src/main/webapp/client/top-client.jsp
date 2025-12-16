<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.lab6.model.Client"%>
<% Object[] clients = (Object[]) request.getAttribute("popular"); %>

<html><head><title>Найуспішніший покупець</title></head><body>

<a href="../index.jsp">Меню</a>

<h1>Найуспішніший продавець</h1>

<% if (clients != null) { %>
<p><strong><%= ((Client) clients[0]).getName() %></strong> — сума продажів: <%= clients[1] %></p>
<% } else { %>
<p>Дані відсутні</p>
<% } %>

</body></html>
