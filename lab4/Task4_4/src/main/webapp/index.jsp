<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head><title>Task4_4</title></head>
<body>
<h2>Введіть три числа</h2>

<form action="Task4_4" method="post">
    <label>Число 1:</label>
    <input type="number" name="a" required><br><br>

    <label>Число 2:</label>
    <input type="number" name="b" required><br><br>

    <label>Число 3:</label>
    <input type="number" name="c" required><br><br>

    <h3>Оберіть дію:</h3>
    <input type="radio" name="operation" value="max" checked> Максимум<br>
    <input type="radio" name="operation" value="min"> Мінімум<br>
    <input type="radio" name="operation" value="avg"> Середнє арифметичне<br><br>

    <button type="submit">Обчислити</button>
</form>
</body>
</html>