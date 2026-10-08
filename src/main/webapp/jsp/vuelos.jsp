<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.etitc.aeropuerto.dao.VueloDAO" %>
<%@ page import="com.etitc.aeropuerto.models.Vuelo" %>
<%@ page import="java.util.List" %>

<%
    if (session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("../index.jsp?error=2");
        return;
    }

    VueloDAO vueloDAO = new VueloDAO();
    List<Vuelo> vuelos = vueloDAO.obtenerTodos();
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Vuelos - Sistema de Aeropuerto</title>
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
                <li><a href="vuelos.jsp" class="active">✈️ Vuelos</a></li>
                <li><a href="tiquetes.jsp">🎫 Tiquetes</a></li>
                <li><a href="personal.jsp">👥 Personal</a></li>
            </ul>
        </nav>

        <!-- Main Content -->
        <main class="main-content">
            <div class="content-header">
                <h2>Gestión de Vuelos</h2>
                <a href="crear_vuelo.jsp" class="btn btn-success">+ Nuevo Vuelo</a>
            </div>

            <div class="section">
                <% if (vuelos.isEmpty()) { %>
                    <p class="text-muted">No hay vuelos registrados</p>
                <% } else { %>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Origen</th>
                                <th>Destino</th>
                                <th>Aerolinea</th>
                                <th>Salida</th>
                                <th>Llegada</th>
                                <th>Asientos</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Vuelo vuelo : vuelos) { %>
                                <tr>
                                    <td><strong><%= vuelo.getCodigo() %></strong></td>
                                    <td><%= vuelo.getOrigen() %></td>
                                    <td><%= vuelo.getDestino() %></td>
                                    <td><%= vuelo.getAerolinea() %></td>
                                    <td><%= vuelo.getHoraSalidaFormato() %></td>
                                    <td><%= vuelo.getHoraLlegadaFormato() %></td>
                                    <td><%= vuelo.getPasajeros() %> / <%= vuelo.getCapacidad() %></td>
                                    <td><span class="badge badge-success"><%= vuelo.getEstado() %></span></td>
                                    <td>
                                        <a href="editar_vuelo.jsp?id=<%= vuelo.getId() %>" class="btn btn-small btn-info">Editar</a>
                                        <a href="eliminar_vuelo.jsp?id=<%= vuelo.getId() %>" class="btn btn-small btn-danger" onclick="return confirm('¿Está seguro?')">Eliminar</a>
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
