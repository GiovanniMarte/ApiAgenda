package com.example.ApiAgenda.servlet;

import com.example.ApiAgenda.service.PersonaService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "ServletPersona", value = "/persona/*")
public class ServletPersona extends HttpServlet {
    private final PersonaService service;
    private int status;

    public ServletPersona() {
        this.service = new PersonaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = getDniFromUrl(request);

        String jsonResponse = service.toJSon(dni);
        sendJson(response, jsonResponse, HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bodyRequest = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        boolean result = service.addPersona(bodyRequest);
        status = HttpServletResponse.SC_OK;

        if (!result) {
            status = HttpServletResponse.SC_BAD_REQUEST;
            bodyRequest = null;
        }

        sendJson(response, bodyRequest, status);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = getDniFromUrl(request);

        boolean result = service.deletePersona(dni);
        String res = "{ success: \"" + dni + "\" }";
        status = HttpServletResponse.SC_OK;

        if (!result) {
            status = HttpServletResponse.SC_BAD_REQUEST;
            res = null ;
        }

        sendJson(response, res, status);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bodyRequest = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String dni = getDniFromUrl(request);

        String result = service.updatePersona(bodyRequest, dni);
        status = HttpServletResponse.SC_OK;

        if (result == null) status = HttpServletResponse.SC_BAD_REQUEST;

        sendJson(response, result, status);
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

    private String getDniFromUrl(HttpServletRequest request) {
        String url = request.getPathInfo();
        String[] splits = url.split("/");
        return splits[1];
    }
}
