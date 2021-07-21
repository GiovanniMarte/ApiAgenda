package com.example.ApiAgenda.servlet;

import com.example.ApiAgenda.service.PersonaService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "ServletPersonas", value = "/personas/*")
public class ServletPersonas extends HttpServlet {
    private final PersonaService service;

    public ServletPersonas() {
        this.service = new PersonaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonResponse = service.listToJson();
        sendJson(response, jsonResponse, HttpServletResponse.SC_OK);
    }

    private void sendJson(HttpServletResponse response, String obj, int status) {
        response.setContentType("application/json");
        response.setStatus(status);

        try {
            PrintWriter out = response.getWriter();
            out.println(obj);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}