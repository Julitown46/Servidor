<%--
  Created by IntelliJ IDEA.
  User: Julian
  Date: 28/11/2024
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
  <form action="${pageContext.request.contextPath}/tienda/usuarios/login/" method="post">
<div style="margin-top: 6px;" class="clearfix">
  <div style="float: left;width: 50%">
    Usuario:
  </div>
  <div style="float: none;width: auto;overflow: hidden;">
    <input name="usuario" />
  </div>
  <div style="float: left;width: 50%">
    Contrasena:
  </div>
  <div style="float: none;width: auto;overflow: hidden;">
    <input name="contrasena" />
  </div>
</div>
    <input type="hidden" name="__method__" value="login"/>
    <input type="submit" value="login"/>

  </form>
</div>
</body>
</html>
