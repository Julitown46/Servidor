<%@ page import="org.iesbelen.model.Empleado" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: Julian
  Date: 05/12/2024
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Empleado</title>
</head>
<body>
<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
    <form action="${pageContext.request.contextPath}/empresa/empleado/editar/" method="post" >
        <input type="hidden" name="__method__" value="put" />
        <div class="clearfix">
            <div style="float: left; width: 50%">
                <h1>Editar Empleados</h1>
            </div>
            <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

                <div style="position: absolute; left: 39%; top : 39%;">
                    <input type="submit" value="Guardar" />
                </div>

            </div>
        </div>

        <div class="clearfix">
            <hr/>
        </div>

            <% 	Optional<Empleado> optEmp = (Optional<Empleado>)request.getAttribute("empleado");
            if (optEmp.isPresent()) {
        %>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Código</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="codigo" value="<%= optEmp.get().getCodigo() %>" readonly="readonly"/>
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>NIF</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="nif" value="<%= optEmp.get().getNif() %>"/>
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Nombre</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="nombre" value="<%= optEmp.get().getNombre() %>"/>
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Apellido1</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="apellido1" value="<%= optEmp.get().getApellido1() %>"/>
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Apellido2</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="apellido2" value="<%= optEmp.get().getApellido2() %>"/>
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Codigo_Departamento</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="codigo_departamento" value="<%= optEmp.get().getCodigoDepartamento() %>" readonly="readonly"/>
            </div>
        </div>

</div>

<% 	} else { %>

request.sendRedirect("empleado/");

<% 	} %>
</form>
</div>
</body>
</html>
