<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.lab6.model.Seller"%>
<% Object[] sellers = (Object[]) request.getAttribute("popular"); %>
<html><head><title>Найуспішніший продавець</title></head><body>

<a href="../index.jsp">Меню</a>

<h1>Найуспішніший продавець</h1>

<% if (sellers != null) { %>
<p><strong><%= ((Seller) sellers[0]).getName() %></strong> — сума продажів: <%= sellers[1] %></p>
<% } else { %>
<p>Дані відсутні</p>
<% } %>

</body></html>
