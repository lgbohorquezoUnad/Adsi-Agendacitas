<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login administrador</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body class="login-body">
<main class="login-container">
    <div class="login-card">
        <h1>Ingreso administrador</h1>
        <p>Use este formulario para revisar las citas registradas.</p>

        <% if ("1".equals(request.getParameter("error"))) { %>
            <div class="message error">Usuario o clave incorrectos.</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/login" method="post" class="form-box">
            <label for="usuario">Usuario</label>
            <input type="text" id="usuario" name="usuario" required>

            <label for="clave">Clave</label>
            <input type="password" id="clave" name="clave" required>

            <button type="submit" class="btn">Ingresar</button>
        </form>

        <a class="link-button" href="${pageContext.request.contextPath}/inicio">Volver al inicio</a>
    </div>
</main>
</body>
</html>
