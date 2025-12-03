<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.lab6.model.Product" %>
<% Object[] r = (Object[]) request.getAttribute("result"); %>
<html><head><title>Найпопулярніший товар</title></head>
<body>
<a href="../index.jsp">Меню</a>
<h1>Найпопулярніший товар</h1>
<% if (r != null) { %>
<p><strong><%= ((Product)r[0]).getName() %></strong> — продано: <%= r[1] %> раз(и)</p>
<% } else { %>
<p>Дані відсутні</p>
<% } %>
</body>
</html>
