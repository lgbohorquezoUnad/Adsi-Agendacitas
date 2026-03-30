package com.agendecitas.util;

import com.agendecitas.model.Cita;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JsonUtil {

    private JsonUtil() {
    }

    public static void responder(HttpServletResponse response, int estado, String json) throws IOException {
        response.setStatus(estado);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter salida = response.getWriter();
        salida.write(json);
        salida.flush();
    }

    public static String mensaje(String clave, String valor) {
        return "{\"" + escapar(clave) + "\":\"" + escapar(valor) + "\"}";
    }

    public static String cita(Cita cita) {
        return "{"
                + "\"id\":" + cita.getId() + ","
                + "\"nombre\":\"" + escapar(cita.getNombre()) + "\","
                + "\"correo\":\"" + escapar(cita.getCorreo()) + "\","
                + "\"telefono\":\"" + escapar(cita.getTelefono()) + "\","
                + "\"servicio\":\"" + escapar(cita.getServicio()) + "\","
                + "\"fecha\":\"" + escapar(cita.getFecha()) + "\","
                + "\"mensaje\":\"" + escapar(cita.getMensaje()) + "\""
                + "}";
    }

    public static String listaCitas(List<Cita> citas) {
        StringBuilder json = new StringBuilder();
        json.append("[");

        for (int i = 0; i < citas.size(); i++) {
            json.append(cita(citas.get(i)));
            if (i < citas.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");
        return json.toString();
    }

    private static String escapar(String texto) {
        if (texto == null) {
            return "";
        }

        return texto
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "");
    }
}
