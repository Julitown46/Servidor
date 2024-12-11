<%@ page import="org.iesbelen.dto.DepartamentoDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Julian
  Date: 05/12/2024
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Departamento</title>
    <meta charset="UTF-8">
    <style>
        .clearfix::after {
            content: "";
            display: block;
            clear: both;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/empresa/departamento/" method="get">
    <label for="presupuestoMenor">Prespuesto Menor: </label>
    <input type="number" name="presupuestoMenor" id="presupuestoMenor" value="0">

    <label for="presupuestoMayor">Prespuesto Mayor: </label>
    <input type="number" name="presupuestoMayor" id="presupuestoMayor" value="0">

    <button type="submit">Ordenar</button>
</form>

<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
    <div class="clearfix">
        <div style="float: left; width: 50%">
            <h1>Departamento</h1>
        </div>
        <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
            <div style="position: absolute; left: 39%; top : 39%;">

                <form action="${pageContext.request.contextPath}/empresa/departamento/crear" method="get">
                    <input type="submit" value="Crear">
                </form>
            </div>

        </div>
    </div>
    <div class="clearfix">
        <hr/>
    </div>
    <div class="clearfix">
        <div style="float: left;width: 10%">Código</div>
        <div style="float: left;width: 20%">Nombre</div>
        <div style="float: left;width: 10%">Presupuesto</div>
        <div style="float: left;width: 10%">Gastos</div>
        <div style="float: left;width: 10%">NEmp</div>
        <div style="float: none;width: auto;overflow: hidden;">Actions</div>
    </div>
    <div class="clearfix">
        <hr/>
    </div>
    <%
        if (request.getAttribute("listaDepartamento") != null) {
            List<DepartamentoDTO> listaDepartamentoDTO = (List<DepartamentoDTO>)request.getAttribute("listaDepartamento");

            for (DepartamentoDTO departamento : listaDepartamentoDTO) {
    %>

    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 10%"><%= departamento.getCodigo()%></div>
        <div style="float: left;width: 20%"><%= departamento.getNombre()%></div>
        <div style="float: left;width: 10%"><%= departamento.getPresupuesto()%></div>
        <div style="float: left;width: 10%"><%= departamento.getGastos()%></div>
        <div style="float: left;width: 10%"><%= departamento.getCantEmpleados()%></div>
        <div style="float: none;width: auto;overflow: hidden;">
            <form action="" style="display: inline;">
                <input type="button" value="Ver Detalle" />
            </form>
            <form action="" style="display: inline;">
                <input type="button" value="Editar" />
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
    No hay registros de departamento
    <% } %>
</div>
</body>
</html>
