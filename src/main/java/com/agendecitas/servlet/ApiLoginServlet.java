package com.agendecitas.servlet;

import com.agendecitas.util.JsonUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/login")
public class ApiLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        if ("admin".equals(usuario) && "1234".equals(clave)) {
            JsonUtil.responder(
                    response,
                    HttpServletResponse.SC_OK,
                    "{\"mensaje\":\"Inicio de sesion correcto.\",\"usuario\":\"" + usuario + "\"}"
            );
            return;
        }

        JsonUtil.responder(
                response,
                HttpServletResponse.SC_UNAUTHORIZED,
                JsonUtil.mensaje("error", "Credenciales incorrectas.")
        );
    }
}
