<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Платежная система(Beta)</title>
    </head>
    <body>
        <%@page import="javax.naming.*, payment_system.*, payment_info.*,java.util.List"%>
    <%@ page errorPage="error.jsp"%>
    <%!
    PaymentSessionRemote ejbRef;
    OperationsForSell ops;
    %>
    <%
    ejbRef =(PaymentSessionRemote)session.getAttribute("ejbRef");
    PaymentPack pk=(PaymentPack)session.getAttribute("pack");
    ops=ejbRef.getHitory(pk.getToken());
    out.println("<table border=1>");
    out.println("<tr>");
    out.println("<td>Дата/Время</td>");
    out.println("<td>Отправитель</td>");
    out.println("<td>Получатель</td>");
    out.println("<td>Сумма</td>");
    out.println("<td>Цель</td>");
    out.println("</tr>");
    int size=ops.getLst().size();
    for(int i=0;i<size;i++)
    {
        out.println("<tr>");
        out.println("<td>"+ops.getLst().get(i).getDatetime()+"</td>");
        out.println("<td>"+ops.getSellers().get(i)+"</td>");
        out.println("<td>"+ops.getReceivers().get(i)+"</td>");
        out.println("<td>"+ops.getLst().get(i).getCount()+"</td>");
        /*if(ops.getLst().get(i).getIntention().isEmpty())
        out.println("<td></td>");
        else*/ out.println("<td>"+ops.getLst().get(i).getIntention()+"</td>");
        out.println("</tr>");
    }
    out.println("</table>");
    %>
    </body>
</html>
