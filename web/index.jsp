<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
 <title>Платежная система(Beta)</title>
</head>
<body>
<h1>Войти в систему</h1>
<form action="doauth.jsp" method="POST">
<p>Логин: <input type="text" name="login" value="" /></p>
<p>Пароль: <input type="password" name="password" value="" /></p>
<input type="submit" value="Вход" />
<br><a href="reg.jsp">Нет аккаунта?</a>
</form>
</body>
</html>