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
    <%!
    PaymentSessionRemote ejbRef;
    %>
    <%
    InitialContext ic = new InitialContext();
    ejbRef =
   (PaymentSessionRemote)ic.lookup("payment_system.PaymentSessionRemote");
    float temp=Float.parseFloat(request.getParameter("count"));
    PaymentStatus pk=ejbRef.addAccount(temp,request.getParameter("login"),request.getParameter("password"));
    PaymentPack pk2=new PaymentPack(pk);
    session.setAttribute("pack", pk2);
    if(pk==PaymentStatus.allGood)
    {
        response.sendRedirect("index.jsp");
    }
    else response.sendRedirect("error.jsp");
    
    %>
    </body>
</html>
