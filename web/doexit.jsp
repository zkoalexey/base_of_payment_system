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
   /*<%!
    PaymentSessionRemote ejbRef;
    %>*/
    <%
    ejbRef =(PaymentSessionRemote)session.getAttribute("ejbRef");
    PaymentPack pk=(PaymentPack)session.getAttribute("pack");
    ejbRef.closeAuth(pk.getToken());
    response.sendRedirect("index.jsp");
    
    %>

    </body>
</html>
