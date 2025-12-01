<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%
    List<Object[]> report = (List<Object[]>) request.getAttribute("report");
    String title = (String) request.getAttribute("title");
    String filter = (String) request.getAttribute("filter");
%>
<html>
<head><title><%= title %></title></head>
<body>
<a href="../index.jsp">Меню</a>
<h1><%= title %></h1>
<table border="1">
    <tr>
        <th><%= filter %></th>
        <th>Кількість блокнотів</th>
    </tr>
    <% if (report != null) {
        for (Object[] row : report) { %>
    <tr>
        <td><%= row[0] %></td>
        <td><%= row[1] %></td>
    </tr>
    <%  }
    } %>
</table>
</body>
</html>
