<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Платежная система(Beta)</title>
    </head>
    <body>
        <%@page import="javax.naming.*, payment_system.*, payment_info.*"%>
    <%@ page errorPage="error.jsp"%>
    <%
    PaymentPack pk=(PaymentPack)session.getAttribute("pack");
    out.println("<h2>Приветствую, "+session.getAttribute("user") +"!</h2>");
    out.println("<h2>Средств: "+String.format(new Locale("ru"), "%(.2f", pk.getCount())+"</h2>");
    %>
    <form action="addcount.jsp" method="POST">
        <input type="submit" value="Пополнить счет" />
    </form>
    <form action="payact.jsp" method="POST">
        <input type="submit" value="Выполнить перевод" />
    </form>
    <form action="history.jsp" method="POST">
        <input type="submit" value="История операций" />
    </form>
    <form action="doexit.jsp" method="POST">
        <input type="submit" value="Выход" />
    </form>
    
    </body>
</html>