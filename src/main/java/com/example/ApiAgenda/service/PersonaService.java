package com.example.ApiAgenda.service;

import com.example.ApiAgenda.dao.PersonaDAO;
import com.example.ApiAgenda.model.Persona;
import com.google.gson.Gson;

public class PersonaService {
    private final PersonaDAO dao;
    private final Gson gson;

    public PersonaService() {
        this.dao = new PersonaDAO();
        this.gson = new Gson();
    }

    public String listToJson() {
        return gson.toJson(dao.get());
    }

    public String toJSon(String dni) {
        return gson.toJson(dao.get(dni));
    }

    public boolean addPersona(String json) {
        try {
            Persona persona = gson.fromJson(json, Persona.class);
            return dao.add(persona);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String updatePersona(String json, String dni) {
        Persona newPersona = new Persona();
        try {
            Persona persona = gson.fromJson(json, Persona.class);
            if (dao.update(dni, persona.getNombre(), persona.getTelefono())) {
                newPersona.setDni(dni);
                newPersona.setNombre(persona.getNombre());
                newPersona.setTelefono(persona.getTelefono());
                return gson.toJson(newPersona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deletePersona(String dni) {
        return dao.delete(dni);
    }
}
