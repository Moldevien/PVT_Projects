<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.lab6.model.Client"%>
<% Object[] r = (Object[]) request.getAttribute("result"); %>
<html><head><title>Найуспішніший покупець</title></head>
<body>
<a href="../index.jsp">Меню</a>
<h1>Найуспішніший продавець</h1>
<% if (r != null) { %>
<p><strong><%= ((Client) r[0]).getName() %></strong> — сума продажів: <%= r[1] %></p>
<% } else { %>
<p>Дані відсутні</p>
<% } %>
</body>
</html>
