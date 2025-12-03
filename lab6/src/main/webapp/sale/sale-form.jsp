<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.lab6.model.*"%>
<%
    Sale sale = (Sale) request.getAttribute("sale");
    List<Seller> sellers = (List<Seller>) request.getAttribute("sellers");
    List<Client> clients = (List<Client>) request.getAttribute("clients");
    List<Product> products = (List<Product>) request.getAttribute("products");
    boolean edit = sale != null;
%>
<html><head><title><%= edit ? "Редагувати" : "Нова" %> угода</title></head><body>
<a href="list">Назад</a>
<h1><%= edit ? "Редагувати" : "Нова" %> угода</h1>
<form action="<%= edit ? "update" : "insert" %>" method="post">
    <% if (edit) { %><input type="hidden" name="id" value="<%= sale.getId() %>"><% } %>

    Продавець:
    <select name="sellerId">
        <% for (Seller s : sellers) { %>
        <option value="<%= s.getId() %>" <%= edit && sale.getSeller().getId()==s.getId() ? "selected" : "" %>><%= s.getName() %></option>
        <% } %>
    </select><br>

    Покупець:
    <select name="clientId">
        <% for (Client b : clients) { %>
        <option value="<%= b.getId() %>" <%= edit && sale.getClient().getId()==b.getId() ? "selected" : "" %>><%= b.getName() %></option>
        <% } %>
    </select><br>

    Товар:
    <select name="productId">
        <% for (Product p : products) { %>
        <option value="<%= p.getId() %>" <%= edit && sale.getProduct().getId()==p.getId() ? "selected" : "" %>><%= p.getName() %> - <%= p.getPrice() %></option>
        <% } %>
    </select><br>

    Дата: <input type="date" name="saleDate" value="<%= edit ? sale.getSaleDate() : "" %>" required><br>

    <button type="submit"><%= edit ? "Оновити" : "Додати" %></button>
</form>
</body></html>
