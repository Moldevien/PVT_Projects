<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html><head><title>Блокноти — Меню</title></head>
<body>
<h1>Меню</h1>
<ul>
    <li><a href="list">Показати всі блокноти</a></li>
    <li><a href="new">Додати блокнот</a></li>
    <li><a href="report/countries">Країни та кількість</a></li>
    <li><a href="report/manufacturers">Виробники та кількість</a></li>
    <li><a href="report/country-max">Країна з макс. кількістю</a></li>
    <li><a href="report/country-min">Країна з мін. кількістю</a></li>
    <li><a href="report/manufacturer-max">Виробник з макс. кількістю</a></li>
    <li><a href="report/manufacturer-min">Виробник з мін. кількістю</a></li>
</ul>

<h3>Фільтри</h3>
<h4>Фільтр по обкладинках</h4>
<form action="filter/cover-type" method="get">
    <select name="coverType" required>
        <option value="тверда">тверда</option>
        <option value="м’яка">м’яка</option>
    </select>
    <button type="submit">Фільтрувати</button>
</form>

<h4>Фільтр по країні</h4>
<form action="filter/by-country" method="get">
    <input type="text" name="country" placeholder="Країна" required>
    <button type="submit">Фільтрувати</button>
</form>

<h4>Фільтр по стилю сторінки</h4>
<form action="filter/page-style" method="get">
    <select name="pageStyle" required>
        <option value="клітинка">клітинка</option>
        <option value="лінійка">лінійка</option>
        <option value="порожня">порожня</option>
    </select>
    <button type="submit">Фільтрувати</button>
</form>

<h4>Фільтр за кількістю сторінок</h4>
<form action="filter/pages" method="get">
    <input type="number" name="minPages" placeholder="min" required>
    <input type="number" name="maxPages" placeholder="max" required>
    <button type="submit">Фільтрувати</button>
</form>

<h4>Фільтр за тиражем</h4>
<form action="filter/circulation" method="get">
    <input type="number" name="minCirculation" placeholder="min" required>
    <input type="number" name="maxCirculation" placeholder="max" required>
    <button type="submit">Фільтрувати</button>
</form>

</body>
</html>
