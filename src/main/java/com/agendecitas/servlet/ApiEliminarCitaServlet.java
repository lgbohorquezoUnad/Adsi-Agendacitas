package com.agendecitas.servlet;

import com.agendecitas.repository.CitaRepository;
import com.agendecitas.util.JsonUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/citas/eliminar")
public class ApiEliminarCitaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idTexto = request.getParameter("id");

        if (idTexto == null || idTexto.isBlank()) {
            JsonUtil.responder(
                    response,
                    HttpServletResponse.SC_BAD_REQUEST,
                    JsonUtil.mensaje("error", "Debe enviar el id de la cita.")
            );
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            boolean eliminada = CitaRepository.eliminar(id);
            if (!eliminada) {
                JsonUtil.responder(
                        response,
                        HttpServletResponse.SC_NOT_FOUND,
                        JsonUtil.mensaje("error", "No existe una cita con el id enviado.")
                );
                return;
            }

            JsonUtil.responder(
                    response,
                    HttpServletResponse.SC_OK,
                    JsonUtil.mensaje("mensaje", "Cita eliminada correctamente.")
            );
        } catch (NumberFormatException e) {
            JsonUtil.responder(
                    response,
                    HttpServletResponse.SC_BAD_REQUEST,
                    JsonUtil.mensaje("error", "El id debe ser numerico.")
            );
        }
    }
}
