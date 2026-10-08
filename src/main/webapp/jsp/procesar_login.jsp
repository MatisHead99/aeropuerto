<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.etitc.aeropuerto.dao.PersonalDAO" %>
<%@ page import="com.etitc.aeropuerto.models.Personal" %>

<%
    String usuario = request.getParameter("usuario");
    String contrasena = request.getParameter("contrasena");

    if (usuario == null || usuario.trim().isEmpty() || contrasena == null || contrasena.trim().isEmpty()) {
        response.sendRedirect("../index.jsp?error=1");
        return;
    }

    PersonalDAO dao = new PersonalDAO();
    Personal personal = dao.autenticar(usuario, contrasena);

    if (personal != null) {
        session.setAttribute("usuarioLogueado", personal);
        session.setAttribute("personalId", personal.getId());
        session.setAttribute("nombreUsuario", personal.getNombre());
        session.setAttribute("puesto", personal.getPuesto());

        response.sendRedirect("dashboard.jsp");
    } else {
        response.sendRedirect("../index.jsp?error=1");
    }
%>
