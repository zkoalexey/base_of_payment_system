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
    PaymentPack pk=ejbRef.authAccount(request.getParameter("login"),request.getParameter("password"));
    session.setAttribute("pack", pk);
    if(pk.getStatus()==PaymentStatus.allGood)
    {
        session.setAttribute("user", request.getParameter("login"));
        session.setAttribute("ejbRef", ejbRef);
        response.sendRedirect("homepage.jsp");
    }
    else response.sendRedirect("error.jsp");
    
    %>
    </body>
</html>
