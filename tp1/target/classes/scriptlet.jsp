<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<html>
<head><title>Scriptlet</title></head>
<body>
    <%
        Date now = new Date();
        String host = request.getRemoteHost();
        String serverIp = request.getLocalAddr();
    %>
    <p>Heure actuelle : <%= now %></p>
    <p>request.getRemoteHost() : <%= host %></p>
    <p>Adresse IP du serveur (getLocalAddr) : <%= serverIp %></p>
</body>
</html>