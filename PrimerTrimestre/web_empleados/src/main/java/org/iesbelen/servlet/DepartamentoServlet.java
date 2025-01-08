package org.iesbelen.servlet;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.iesbelen.model.Departamento;
import org.iesbelen.dto.DepartamentoDTO;
import org.iesbelen.dao.DepartamentoDAO;
import org.iesbelen.dao.DepartamentoDAOImpl;

@WebServlet(name = "departamentoServlet", value = "/empresa/departamento/*")
public class DepartamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher;
        String pathInfo = request.getPathInfo();

        // LO COMENTADO DA ERROR PERO ES LO DE ORDENAR

        if (pathInfo == null || "/".equals(pathInfo)) {
            DepartamentoDAO deptDAO = new DepartamentoDAOImpl();
            //String presupuestoMayor = request.getParameter("presupuestoMayor");
            //String presupuestoMenor = request.getParameter("presupuestoMenor");
            List<DepartamentoDTO> listaDepartamentoDTO;

            //if (!Objects.equals(presupuestoMayor, "0") && !Objects.equals(presupuestoMenor, "0")) {
           //     int numeroMenor = Integer.parseInt(presupuestoMenor);
           //     int numeroMayor = Integer.parseInt(presupuestoMayor);
          //      listaDepartamentoDTO = deptDAO.getOrdenadoDTO(numeroMenor, numeroMayor);
          //  } else {
                listaDepartamentoDTO = deptDAO.getAllDTO();
          //  }

            request.setAttribute("listaDepartamento", listaDepartamentoDTO);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamento/departamento.jsp");
        } else {
            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamento/crear-departamento.jsp");

            } else {
                System.out.println("Opción GET no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamento/departamento.jsp");

            }
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String __method__ = request.getParameter("__method__");

        if (__method__ == null) {
            DepartamentoDAO deptDAO = new DepartamentoDAOImpl();

            String nombre = request.getParameter("nombre");
            double presupuesto = Double.parseDouble(request.getParameter("presupuesto"));
            double gasto = Double.parseDouble(request.getParameter("gasto"));
            int codigo_departamento = Integer.parseInt(request.getParameter("codigo_departamento"));

            Departamento nuevoDept = new Departamento();
            nuevoDept.setNombre(nombre);
            nuevoDept.setPresupuesto(presupuesto);
            nuevoDept.setGastos(gasto);

            deptDAO.create(nuevoDept);
        } else {
            System.out.println("Opción POST no soportada.");
        }

        response.sendRedirect(request.getContextPath() + "/empresa/departamento");
    }

}
