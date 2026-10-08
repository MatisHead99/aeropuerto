<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Sistema de Gestión de Aeropuerto</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <div class="login-header">
                <h1>✈️ Aeropuerto</h1>
                <p>Sistema de Gestión de Vuelos</p>
            </div>

            <%
                String error = request.getParameter("error");
                if (error != null && !error.isEmpty()) {
            %>
                <div class="alert alert-error">
                    <%
                        if ("1".equals(error)) {
                            out.println("Usuario o contraseña incorrectos");
                        } else if ("2".equals(error)) {
                            out.println("Debe iniciar sesión para acceder");
                        }
                    %>
                </div>
            <% } %>

            <form method="POST" action="jsp/procesar_login.jsp">
                <div class="form-group">
                    <label for="usuario">Usuario:</label>
                    <input type="text" id="usuario" name="usuario" required autofocus>
                </div>

                <div class="form-group">
                    <label for="contrasena">Contraseña:</label>
                    <input type="password" id="contrasena" name="contrasena" required>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Iniciar Sesión</button>
            </form>

            <div class="login-footer">
                <p><small>Usuarios de prueba:</small></p>
                <p><small>Usuario: <strong>admin</strong> | Contraseña: <strong>admin123</strong></small></p>
                <p><small>Usuario: <strong>carlos</strong> | Contraseña: <strong>carlos123</strong></small></p>
            </div>
        </div>
    </div>
</body>
</html>
