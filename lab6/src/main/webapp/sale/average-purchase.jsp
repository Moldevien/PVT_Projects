<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<% Double avg = (Double) request.getAttribute("avg"); %>
<html><head><title>Середня сума покупки</title></head><body>

<a href="../index.jsp">Меню</a>

<h1>Середня сума покупки</h1>

<p><%= avg != null ? avg : "Дані відсутні" %></p>

</body></html>
