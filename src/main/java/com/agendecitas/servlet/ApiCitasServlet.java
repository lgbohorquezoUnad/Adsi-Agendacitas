package com.agendecitas.servlet;

import com.agendecitas.model.Cita;
import com.agendecitas.repository.CitaRepository;
import com.agendecitas.util.JsonUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/citas")
public class ApiCitasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonUtil.responder(response, HttpServletResponse.SC_OK, JsonUtil.listaCitas(CitaRepository.listar()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String servicio = request.getParameter("servicio");
        String fecha = request.getParameter("fecha");
        String mensaje = request.getParameter("mensaje");

        if (esVacio(nombre) || esVacio(correo) || esVacio(telefono) || esVacio(servicio) || esVacio(fecha)) {
            JsonUtil.responder(
                    response,
                    HttpServletResponse.SC_BAD_REQUEST,
                    JsonUtil.mensaje("error", "Faltan campos obligatorios para registrar la cita.")
            );
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
        JsonUtil.responder(response, HttpServletResponse.SC_CREATED, JsonUtil.cita(cita));
    }

    private boolean esVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
