<%@ page import="java.util.List" %>
<%@ page import="com.agendecitas.model.Cita" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de citas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<header>
    <div class="container">
        <h1>Panel de administracion</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/inicio">Inicio</a>
            <a href="${pageContext.request.contextPath}/logout">Cerrar sesion</a>
        </nav>
    </div>
</header>

<main class="section">
    <div class="container">
        <h2>Citas registradas</h2>
        <p>Credenciales de prueba: usuario <strong>admin</strong> y clave <strong>1234</strong>.</p>

        <table class="table">
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Telefono</th>
                <th>Servicio</th>
                <th>Fecha</th>
                <th>Mensaje</th>
                <th>Accion</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Cita> citas = (List<Cita>) request.getAttribute("citas");
                if (citas == null || citas.isEmpty()) {
            %>
            <tr>
                <td colspan="7">No hay citas registradas por ahora.</td>
            </tr>
            <%
                } else {
                    for (Cita cita : citas) {
            %>
            <tr>
                <td><%= cita.getNombre() %></td>
                <td><%= cita.getCorreo() %></td>
                <td><%= cita.getTelefono() %></td>
                <td><%= cita.getServicio() %></td>
                <td><%= cita.getFecha() %></td>
                <td><%= cita.getMensaje().isEmpty() ? "Sin mensaje" : cita.getMensaje() %></td>
                <td>
                    <form action="${pageContext.request.contextPath}/eliminar-cita" method="post">
                        <input type="hidden" name="id" value="<%= cita.getId() %>">
                        <button type="submit" class="btn danger">Eliminar</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>
