<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
 <title>Платежная система(Beta)</title>
</head>
<body>
<form action="doreg.jsp" method="POST">
    <p>Логин: <input type="text" name="login" value="" /></p>
    <p>Пароль: <input type="password" name="password" value="" /></p>
    <p>Начальная сумма: <input type="number" name="count" value="0.00" /></p>
<input type="submit" value="Регистрация" />
</form>
</body>
</html>