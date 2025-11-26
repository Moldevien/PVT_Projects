<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Task4_2</title>
</head>
<body>
<h1>
    <%= "Табличка множення!" %>
</h1>
<br/>
<form action='hello-servlet' method='POST'>
    Введіть число:
    <input type='text' name='number' value=''/>
    <input type='submit' value='Порахувати'/>
</form>
</body>
</html>