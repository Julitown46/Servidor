<%@ page import="org.iesbelen.model.Usuario" %>
<%@ page import="java.util.Optional" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inicio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        body {
            padding: 0;
            background-color: lightcyan;
            font-family: "Bookman Old Style";
        }
        main {
            display: flex;
            align-items: center;
            justify-content: center;
        }
        main div {
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        main p {
            margin-top: 45%;
            font-size: 20px;
        }
    </style>
</head>
<body>

<%
    Optional<Usuario> usu = Optional.ofNullable((Usuario) session.getAttribute("usuario-logado"));
    if (usu.isPresent()){
        %>
<h3><%= usu.get().getUsuario()%></h3>
<form action="${pageContext.request.contextPath}/tienda/usuarios/" method="post">
    <input type="hidden" name="__method__" value="logout"/>
    <input type="submit" value="no"/>
</form>
<%
    } else {
        %>
<div><a class="btn btn-secondary btn-lg" href="<%=application.getContextPath()%>/tienda/usuarios/login">LOGIN</a></div>
<%
    }
%>

    <div class="d-grid gap-2" id="contenedor">
        <a class="btn btn-primary btn-lg" href="<%=application.getContextPath()%>/tienda/fabricantes">FABRICANTES</a>
        <a class="btn btn-success btn-lg" href="<%=application.getContextPath()%>/tienda/productos">PRODUCTOS</a>
        <a class="btn btn-secondary btn-lg" href="<%=application.getContextPath()%>/tienda/usuarios">USUARIOS</a>
    </div>

    <%@include file="boostrap.jspf"%>
</body>
</html>