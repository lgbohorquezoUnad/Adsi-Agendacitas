<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agendecitas - Inicio</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<header>
    <div class="container">
        <h1>Firma Legal Integral & Asociados</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/inicio">Inicio</a>
            <a href="${pageContext.request.contextPath}/login">Administrador</a>
        </nav>
    </div>
</header>

<main>
    <section class="hero">
        <div class="container">
            <h2>Asignacion sencilla de citas</h2>
            <p>Proyecto web con HTML, JSP y Servlets para registrar solicitudes de cita.</p>
        </div>
    </section>

    <section class="section">
        <div class="container">
            <h2>Servicios disponibles</h2>
            <div class="cards">
                <article class="card">
                    <h3>Abogados</h3>
                    <p>Asesoria juridica para casos civiles, penales y familiares.</p>
                </article>
                <article class="card">
                    <h3>Detectives</h3>
                    <p>Investigacion privada para personas y empresas.</p>
                </article>
                <article class="card">
                    <h3>Poligrafistas</h3>
                    <p>Apoyo en procesos de verificacion y seleccion.</p>
                </article>
                <article class="card">
                    <h3>Academia</h3>
                    <p>Cursos y capacitaciones para estudiantes y profesionales.</p>
                </article>
            </div>
        </div>
    </section>

    <section class="section form-section">
        <div class="container">
            <h2>Formulario de cita</h2>

            <% String mensajeExito = (String) request.getAttribute("mensajeExito"); %>
            <% String mensajeError = (String) request.getAttribute("mensajeError"); %>

            <% if (mensajeExito != null) { %>
                <div class="message success"><%= mensajeExito %></div>
            <% } %>

            <% if (mensajeError != null) { %>
                <div class="message error"><%= mensajeError %></div>
            <% } %>

            <form action="${pageContext.request.contextPath}/registrar-cita" method="post" class="form-box">
                <label for="nombre">Nombre completo</label>
                <input type="text" id="nombre" name="nombre" required>

                <label for="correo">Correo electronico</label>
                <input type="email" id="correo" name="correo" required>

                <label for="telefono">Telefono</label>
                <input type="text" id="telefono" name="telefono" required>

                <label for="servicio">Servicio</label>
                <select id="servicio" name="servicio" required>
                    <option value="">Seleccione una opcion</option>
                    <option value="Abogados">Abogados</option>
                    <option value="Detectives">Detectives</option>
                    <option value="Poligrafistas">Poligrafistas</option>
                    <option value="Academia">Academia</option>
                </select>

                <label for="fecha">Fecha deseada</label>
                <input type="date" id="fecha" name="fecha" required>

                <label for="mensaje">Mensaje adicional</label>
                <textarea id="mensaje" name="mensaje" rows="4"></textarea>

                <button type="submit" class="btn">Enviar cita</button>
            </form>
        </div>
    </section>
</main>

<footer>
    <div class="container">
        <p>Proyecto academico ADSI - modulo web con Servlets y JSP.</p>
    </div>
</footer>
</body>
</html>
