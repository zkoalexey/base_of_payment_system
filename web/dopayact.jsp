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
    float temp=Float.parseFloat(request.getParameter("count"));
    String receiver=request.getParameter("receiver");
    PaymentPack pk2=ejbRef.payAction(pk.getToken(),receiver,temp);
    pk2.setToken(pk.getToken());
    session.setAttribute("pack", pk2);
    if(pk2.getStatus()==PaymentStatus.allGood)
    {
        response.sendRedirect("homepage.jsp");
    }
    else response.sendRedirect("error.jsp");
    
    %>

    </body>
</html>
