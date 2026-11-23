<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="person" type="lab.mvc.model.Person" scope="request" />
<html>
<head><title>Young</title></head>
<body>
    <h2>Page pour les personnes nées en 1990 ou après</h2>
    <table border="1" cellpadding="5">
        <tr><th>Prénom</th><td><jsp:getProperty name="person" property="firstName" /></td></tr>
        <tr><th>Nom</th><td><jsp:getProperty name="person" property="lastName" /></td></tr>
        <tr><th>Date de naissance</th><td><jsp:getProperty name="person" property="birthDay" /></td></tr>
        <tr><th>Sexe</th><td><jsp:getProperty name="person" property="sex" /></td></tr>
    </table>
    <p><a href="index.html">Retour</a></p>
</body>
</html>