package com.agendecitas.servlet;

import com.agendecitas.repository.CitaRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/eliminar-cita")
public class EliminarCitaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String idTexto = request.getParameter("id");
        if (idTexto != null && !idTexto.isBlank()) {
            try {
                int id = Integer.parseInt(idTexto);
                CitaRepository.eliminar(id);
            } catch (NumberFormatException ignored) {
                session.setAttribute("mensajeError", "El identificador de la cita no es valido.");
            }
        }

        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}
