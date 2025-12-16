<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.lab6.model.Product" %>
<% Object[] products = (Object[]) request.getAttribute("popular"); %>
<html><head><title>Найпопулярніший товар</title></head><body>

<a href="../index.jsp">Меню</a>

<h1>Найпопулярніший товар</h1>

<% if (products != null) { %>
<p><strong><%= ((Product)products[0]).getName() %></strong> — продано: <%= products[1] %> раз(и)</p>
<% } else { %>
<p>Дані відсутні</p>
<% } %>

</body></html>
