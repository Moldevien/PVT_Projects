<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%
    Object[] single = (Object[]) request.getAttribute("single");
    String title = (String) request.getAttribute("title");
%>
<html><head><title><%= title %></title></head>
<body>
<a href="../index.jsp">Меню</a>
<h1><%= title %></h1>
<% if (single != null) { %>
<p><strong><%= single[0] %></strong> — <%= single[1] %> блокнот(ів)</p>
<% } else { %>
<p>Дані відсутні</p>
<% } %>
</body>
</html>
