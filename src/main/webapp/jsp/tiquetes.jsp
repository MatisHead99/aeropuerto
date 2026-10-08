<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    if (session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("../index.jsp?error=2");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Tiquetes - Sistema de Aeropuerto</title>
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
                <li><a href="tiquetes.jsp" class="active">🎫 Tiquetes</a></li>
                <li><a href="personal.jsp">👥 Personal</a></li>
            </ul>
        </nav>

        <!-- Main Content -->
        <main class="main-content">
            <div class="content-header">
                <h2>Gestión de Tiquetes</h2>
                <a href="crear_tiquete.jsp" class="btn btn-success">+ Vender Tiquete</a>
            </div>

            <div class="section">
                <p class="text-muted">Módulo de gestión de tiquetes (en desarrollo)</p>
            </div>
        </main>
    </div>
</body>
</html>
