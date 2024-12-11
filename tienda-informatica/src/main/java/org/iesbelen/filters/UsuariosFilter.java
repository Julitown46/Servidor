package org.iesbelen.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.model.Usuario;

import java.io.IOException;

@WebFilter(
        urlPatterns = {"/tienda/fabricantes/*", "/tienda/productos/*", "/tienda/usuarios/*"},
        initParams = @WebInitParam(name = "acceso-concedido-a-rol", value = "Admin")
)

public class UsuariosFilter  extends HttpFilter implements Filter {

    private String rolAcceso;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.rolAcceso = filterConfig.getInitParameter("acceso-concedido-a-rol");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest =(HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;

        HttpSession session = httpRequest.getSession();

        String url = httpRequest.getRequestURL().toString();

        Usuario usuario = null;

        if (session != null
                && (usuario = (Usuario)session.getAttribute("usuario-logado") )!= null
                && "Admin".equals(usuario.getRol())) {

            filterChain.doFilter(httpRequest, httpResponse);

            return;
        } else if (url.endsWith("/fabricantes/crear")
                || url.contains("/fabricantes/editar")
                || url.contains("/fabricantes/borrar")
                || url.contains("/productos/crear")
                || url.contains("/productos/editar")
                || url.contains("/productos/borrar")
                || url.endsWith("/productos")
                || url.contains("/usuarios/crear")
                || url.contains("/usuarios/editar")
                || url.contains("/usuarios/borrar")
                || url.endsWith("/usuarios")) {

            httpResponse.sendRedirect(httpRequest.getContextPath() + "/tienda/usuarios/login");
            return;
        } else {
            filterChain.doFilter(httpRequest, httpResponse);
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
