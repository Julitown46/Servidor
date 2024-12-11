package org.iesbelen.servlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.DepartamentoDAO;
import org.iesbelen.dao.DepartamentoDAOImpl;
import org.iesbelen.dao.EmpleadoDAO;
import org.iesbelen.dao.EmpleadoDAOImpl;
import org.iesbelen.model.Departamento;
import org.iesbelen.model.Empleado;
import java.io.IOException;

@WebServlet(name = "empleadoServlet", value = "/empresa/empleado/*")
public class EmpleadoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String pathInfo = request.getPathInfo(); //

        if (pathInfo == null || "/".equals(pathInfo)) {
            EmpleadoDAO empDAO = new EmpleadoDAOImpl();

            request.setAttribute("listaEmpleado", empDAO.getAll());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empleado/empleado.jsp");

        } else {
            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 3 && "editar".equals(pathParts[1]) ) {
                EmpleadoDAO empDAO = new EmpleadoDAOImpl();
                DepartamentoDAO deptDAO = new DepartamentoDAOImpl();

                try {
                    request.setAttribute("departamento", deptDAO.getAll());
                    request.setAttribute("empleado",empDAO.find(Integer.parseInt(pathParts[2])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empleado/editar-empleado.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empleado/empleado.jsp");
                }


            } else {

                System.out.println("Opción GET no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empleado/empleado.jsp");
            }

        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String __method__ = request.getParameter("__method__");

        if (__method__ != null && "put".equalsIgnoreCase(__method__)) {
            doPut(request, response);
        } else {
            System.out.println("Opción POST no soportada.");
        }

        response.sendRedirect(request.getContextPath() + "/empresa/empleado");
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nif = request.getParameter("nif");
        String nombre = request.getParameter("nombre");
        String apellido1 = request.getParameter("apellido1");
        String apellido2 = request.getParameter("apellido2");
        Integer codigo_departamento = Integer.parseInt(request.getParameter("codigo_departamento"));
        Empleado nuevoEmp = new Empleado();
        EmpleadoDAO empDAO = new EmpleadoDAOImpl();

        try {
            nuevoEmp.setCodigo(codigo);
            nuevoEmp.setNif(nif);
            nuevoEmp.setNombre(nombre);
            nuevoEmp.setApellido1(apellido1);
            nuevoEmp.setApellido2(apellido2);
            nuevoEmp.setCodigoDepartamento(codigo_departamento);
            empDAO.update(nuevoEmp);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }
}
