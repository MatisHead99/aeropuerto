<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.etitc.aeropuerto.dao.VueloDAO" %>
<%@ page import="com.etitc.aeropuerto.models.Vuelo" %>
<%@ page import="java.util.List" %>

<%
    if (session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("../index.jsp?error=2");
        return;
    }

    String nombreUsuario = (String) session.getAttribute("nombreUsuario");
    String puesto = (String) session.getAttribute("puesto");

    VueloDAO vueloDAO = new VueloDAO();
    List<Vuelo> vuelos = vueloDAO.obtenerActivos();

    int totalVuelos = vuelos.size();
    int totalPasajeros = 0;
    for (Vuelo v : vuelos) {
        totalPasajeros += v.getPasajeros();
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Sistema de Gestión de Aeropuerto</title>
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
                <span>Bienvenido, <strong><%= nombreUsuario %></strong></span>
                <span class="badge"><%= puesto %></span>
                <a href="logout.jsp" class="btn btn-small btn-danger">Salir</a>
            </div>
        </header>

        <!-- Sidebar Navigation -->
        <nav class="sidebar">
            <ul class="nav-menu">
                <li><a href="dashboard.jsp" class="active">📊 Dashboard</a></li>
                <li><a href="vuelos.jsp">✈️ Vuelos</a></li>
                <li><a href="tiquetes.jsp">🎫 Tiquetes</a></li>
                <li><a href="personal.jsp">👥 Personal</a></li>
            </ul>
        </nav>

        <!-- Main Content -->
        <main class="main-content">
            <div class="dashboard-header">
                <h2>Dashboard</h2>
                <p>Resumen general del sistema</p>
            </div>

            <!-- Stats Cards -->
            <div class="stats-grid">
                <div class="stat-card">
                    <div class="stat-icon">✈️</div>
                    <div class="stat-info">
                        <h3>Vuelos Activos</h3>
                        <p class="stat-number"><%= totalVuelos %></p>
                    </div>
                </div>

                <div class="stat-card">
                    <div class="stat-icon">👥</div>
                    <div class="stat-info">
                        <h3>Pasajeros Totales</h3>
                        <p class="stat-number"><%= totalPasajeros %></p>
                    </div>
                </div>

                <div class="stat-card">
                    <div class="stat-icon">💰</div>
                    <div class="stat-info">
                        <h3>Ingresos Estimados</h3>
                        <p class="stat-number">$150,000</p>
                    </div>
                </div>

                <div class="stat-card">
                    <div class="stat-icon">🔔</div>
                    <div class="stat-info">
                        <h3>Alertas</h3>
                        <p class="stat-number">2</p>
                    </div>
                </div>
            </div>

            <!-- Próximos Vuelos -->
            <div class="section">
                <h3>Próximos Vuelos</h3>
                <% if (vuelos.isEmpty()) { %>
                    <p class="text-muted">No hay vuelos activos en este momento</p>
                <% } else { %>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Origen</th>
                                <th>Destino</th>
                                <th>Salida</th>
                                <th>Aerolinea</th>
                                <th>Capacidad</th>
                                <th>Estado</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Vuelo vuelo : vuelos) { %>
                                <tr>
                                    <td><strong><%= vuelo.getCodigo() %></strong></td>
                                    <td><%= vuelo.getOrigen() %></td>
                                    <td><%= vuelo.getDestino() %></td>
                                    <td><%= vuelo.getHoraSalidaFormato() %></td>
                                    <td><%= vuelo.getAerolinea() %></td>
                                    <td><%= vuelo.getPasajeros() %> / <%= vuelo.getCapacidad() %></td>
                                    <td><span class="badge badge-success"><%= vuelo.getEstado() %></span></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } %>
            </div>

            <!-- Quick Actions -->
            <div class="section">
                <h3>Acciones Rápidas</h3>
                <div class="action-buttons">
                    <a href="vuelos.jsp" class="btn btn-primary">Gestionar Vuelos</a>
                    <a href="tiquetes.jsp" class="btn btn-primary">Vender Tiquetes</a>
                    <a href="personal.jsp" class="btn btn-primary">Gestionar Personal</a>
                </div>
            </div>
        </main>
    </div>
</body>
</html>
