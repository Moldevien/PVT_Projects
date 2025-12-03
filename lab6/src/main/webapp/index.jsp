<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html><head><title>Продажі — Меню</title></head>
<body>
<h1>Меню</h1>
<ul>
    <li><a href="seller/list">Продавці</a></li>
    <li><a href="client/list">Покупці</a></li>
    <li><a href="product/list">Товари</a></li>
    <li><a href="sale/list">Угоди</a></li>
</ul>

<h3>Звіти</h3>

<h4>Угоди за дату</h4>
<form action="reports/sales-by-date" method="get">
    <input type="date" name="date" required>
    <button type="submit">Сформувати</button>
</form>
<h4>Угоди за діапазон</h4>
<form action="reports/sales-between" method="get">
    <input type="date" name="from" required>
    <input type="date" name="to" required>
    <button type="submit">Сформувати</button>
</form>
<ul>
    <!--<li><a href="reports/sales-by-date">Угоди за дату</a></li>
    <li><a href="reports/sales-between">Угоди за діапазон</a></li>-->
    <li><a href="reports/top-seller">Найуспішніший продавець</a></li>
    <li><a href="reports/top-client">Найуспішніший покупець</a></li>
    <li><a href="reports/avg">Середня сума покупки</a></li>
    <li><a href="reports/popular-product">Найпопулярніший товар</a></li>
</ul>
</body></html>