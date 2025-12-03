<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.lab5.model.Notebook" %>
<%
    Notebook notebook = (Notebook) request.getAttribute("notebook");
    boolean edit = (notebook != null);
%>
<html><head><title><%= (edit ? "Редагувати" : "Додати") %> блокнот</title></head>
<body>
<a href="index.jsp">Меню</a>
<h1><%= (edit ? "Редагувати" : "Додати") %> блокнот</h1>

<form action="<%= (edit ? "update" : "insert") %>" method="post">
    <% if (edit) { %>
    <input type="hidden" name="id" value="<%= notebook.getId() %>"/>
    <% } %>

    <label>Виробник:</label>
    <input type="text" name="manufacturer" value="<%= (edit ? notebook.getManufacturer() : "") %>" required><br>

    <label>Назва/шифр:</label>
    <input type="text" name="notebookName" value="<%= (edit ? notebook.getNotebookName() : "") %>" required><br>

    <label>Сторінок:</label>
    <input type="number" name="pages" value="<%= (edit ? notebook.getPages() : "") %>" required><br>

    <label>Тип обкладинки:</label>
    <select name="coverType" required>
        <option value="тверда" <%= (edit && "тверда".equals(notebook.getCoverType()) ? "selected" : "") %>>тверда</option>
        <option value="м’яка" <%= (edit && "м’яка".equals(notebook.getCoverType()) ? "selected" : "") %>>м’яка</option>
    </select><br>

    <label>Країна:</label>
    <input type="text" name="country" value="<%= (edit ? notebook.getCountry() : "") %>" required><br>

    <label>Тираж:</label>
    <input type="number" name="circulation" value="<%= (edit ? notebook.getCirculation() : "") %>" required><br>

    <label>Вигляд сторінки:</label>
    <select name="pageStyle" required>
        <option value="клітинка" <%= (edit && "клітинка".equals(notebook.getPageStyle()) ? "selected" : "") %>>клітинка</option>
        <option value="лінійка" <%= (edit && "лінійка".equals(notebook.getPageStyle()) ? "selected" : "") %>>лінійка</option>
        <option value="порожня" <%= (edit && "порожня".equals(notebook.getPageStyle()) ? "selected" : "") %>>порожня</option>
    </select>
    <br><br>

    <button type="submit"><%= (edit ? "Оновити" : "Додати") %></button>
</form>
</body>
</html>