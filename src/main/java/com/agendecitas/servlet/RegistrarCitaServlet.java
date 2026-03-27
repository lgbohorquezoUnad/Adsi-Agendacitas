package com.agendecitas.servlet;

import com.agendecitas.model.Cita;
import com.agendecitas.repository.CitaRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/registrar-cita")
public class RegistrarCitaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String servicio = request.getParameter("servicio");
        String fecha = request.getParameter("fecha");
        String mensaje = request.getParameter("mensaje");

        HttpSession session = request.getSession();
        if (esVacio(nombre) || esVacio(correo) || esVacio(telefono) || esVacio(servicio) || esVacio(fecha)) {
            session.setAttribute("mensajeError", "Todos los campos obligatorios deben estar completos.");
            response.sendRedirect(request.getContextPath() + "/inicio");
            return;
        }

        Cita cita = new Cita(
                CitaRepository.siguienteId(),
                nombre.trim(),
                correo.trim(),
                telefono.trim(),
                servicio.trim(),
                fecha.trim(),
                mensaje == null ? "" : mensaje.trim()
        );

        CitaRepository.guardar(cita);
        session.setAttribute("mensajeExito", "La cita de " + cita.getNombre() + " fue registrada correctamente.");
        response.sendRedirect(request.getContextPath() + "/inicio");
    }

    private boolean esVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
