<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.etitc.aeropuerto.dao.PersonalDAO" %>
<%@ page import="com.etitc.aeropuerto.models.Personal" %>
<%@ page import="java.util.List" %>

<%
    if (session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("../index.jsp?error=2");
        return;
    }

    PersonalDAO personalDAO = new PersonalDAO();
    List<Personal> personal = personalDAO.obtenerTodos();
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Personal - Sistema de Aeropuerto</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <div class="container">
        <!-- Header -->
        <header class="header">
            <div class="header-brand">
                <h1>✈️ Sistema de Aeropuerto</h1>
            </div>
            <div class="header-user">
                <span>Bienvenido, <strong><%= session.getAttribute("nombreUsuario") %></strong></span>
                <a href="logout.jsp" class="btn btn-small btn-danger">Salir</a>
            </div>
        </header>

        <!-- Sidebar -->
        <nav class="sidebar">
            <ul class="nav-menu">
                <li><a href="dashboard.jsp">📊 Dashboard</a></li>
                <li><a href="vuelos.jsp">✈️ Vuelos</a></li>
                <li><a href="tiquetes.jsp">🎫 Tiquetes</a></li>
                <li><a href="personal.jsp" class="active">👥 Personal</a></li>
            </ul>
        </nav>

        <!-- Main Content -->
        <main class="main-content">
            <div class="content-header">
                <h2>Gestión de Personal</h2>
                <a href="crear_personal.jsp" class="btn btn-success">+ Agregar Personal</a>
            </div>

            <div class="section">
                <% if (personal.isEmpty()) { %>
                    <p class="text-muted">No hay personal registrado</p>
                <% } else { %>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Cédula</th>
                                <th>Puesto</th>
                                <th>Email</th>
                                <th>Teléfono</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Personal p : personal) { %>
                                <tr>
                                    <td><strong><%= p.getNombre() %></strong></td>
                                    <td><%= p.getCedula() %></td>
                                    <td><%= p.getPuesto() %></td>
                                    <td><%= p.getEmail() %></td>
                                    <td><%= p.getTelefono() %></td>
                                    <td><span class="badge <%= p.estaActivo() ? "badge-success" : "badge-warning" %>"><%= p.getEstado() %></span></td>
                                    <td>
                                        <a href="editar_personal.jsp?id=<%= p.getId() %>" class="btn btn-small btn-info">Editar</a>
                                        <a href="eliminar_personal.jsp?id=<%= p.getId() %>" class="btn btn-small btn-danger" onclick="return confirm('¿Está seguro?')">Eliminar</a>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } %>
            </div>
        </main>
    </div>
</body>
</html>
