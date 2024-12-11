<%@ page import="org.iesbelen.model.Empleado" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Julian
  Date: 05/12/2024
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Empleado</title>
</head>
<body>
<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
    <div class="clearfix">
        <div style="float: left; width: 50%">
            <h1>Empleados</h1>
        </div>
        <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

            <div style="position: absolute; left: 39%; top : 39%;">

                <form action="">
                    <input type="button" value="Crear">
                </form>
            </div>

        </div>
    </div>
    <div class="clearfix">
        <hr/>
    </div>
    <div class="clearfix">
        <div style="float: left;width: 10%">Código</div>
        <div style="float: left;width: 10%">NIF</div>
        <div style="float: left;width: 10%">Apellido1</div>
        <div style="float: left;width: 10%">Apellido2</div>
        <div style="float: left;width: 25%">Codigo_departamento</div>
        <div style="float: left;width: 35%;overflow: hidden;">Acción</div>
    </div>
    <div class="clearfix">
        <hr/>
    </div>
    <br>
    <%
        if (request.getAttribute("listaEmpleado") != null) {
            List<Empleado> listaEmpleado = (List<Empleado>)request.getAttribute("listaEmpleado");

            for (Empleado empleado : listaEmpleado) {
    %>

    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 10%"><%= empleado.getCodigo()%></div>
        <div style="float: left;width: 10%"><%= empleado.getNif()%></div>
        <div style="float: left;width: 10%"><%= empleado.getNombre()%></div>
        <div style="float: left;width: 10%"><%= empleado.getApellido1()%></div>
        <div style="float: left;width: 10%"><%= empleado.getApellido2()%></div>
        <div style="float: left;width: 25%"><%= empleado.getCodigoDepartamento()%></div>
        <div style="float: none;width: auto;overflow: hidden;">
            <form action="" style="display: inline;">
                <input type="button" value="Ver Detalle" />
            </form>
            <form action="${pageContext.request.contextPath}/empresa/empleado/editar/<%= empleado.getCodigo()%>" style="display: inline;">
                <input type="submit" value="Editar" />
            </form>
            <form action="" style="display: inline;">
                <input type="button" value="Eliminar" />
            </form>
        </div>
    </div>
    <%
        }
    } else {
    %>
    No hay registros de empleado
    <% } %>
</div>
</body>
</html>
