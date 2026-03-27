package com.agendecitas.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        if ("admin".equals(usuario) && "1234".equals(clave)) {
            HttpSession session = request.getSession();
            session.setAttribute("adminLogueado", true);
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/login?error=1");
    }
}
