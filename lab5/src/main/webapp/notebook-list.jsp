<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lab5.model.Notebook" %>
<%
    List<Notebook> list = (List<Notebook>) request.getAttribute("listNotebook");
    String title = (String) request.getAttribute("title");
%>
<html><head><title><%= title %></title></head>
<body>
<a href="index.jsp">Меню</a> | <a href="new">Додати новий</a>
<h1><%= title %></h1>
<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Виробник</th>
        <th>Назва</th>
        <th>Сторінок</th>
        <th>Обкладинка</th>
        <th>Країна</th>
        <th>Тираж</th>
        <th>Стиль</th>
        <th>Дії</th>
    </tr>
    <% if (list != null) {
        for (Notebook notebook : list) { %>
    <tr>
        <td><%= notebook.getId() %></td>
        <td><%= notebook.getManufacturer() %></td>
        <td><%= notebook.getNotebookName() %></td>
        <td><%= notebook.getPages() %></td>
        <td><%= notebook.getCoverType() %></td>
        <td><%= notebook.getCountry() %></td>
        <td><%= notebook.getCirculation() %></td>
        <td><%= notebook.getPageStyle() %></td>
        <td>
            <a href="edit?id=<%= notebook.getId() %>">Редагувати</a> |
            <a href="delete?id=<%= notebook.getId() %>" onclick="return confirm('Видалити?')">Видалити</a>
        </td>
    </tr>
    <%  }
    } %>
</table>
</body>
</html>