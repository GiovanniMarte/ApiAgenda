package com.example.ApiAgenda.dao;

import com.example.ApiAgenda.connection.DBConnection;
import com.example.ApiAgenda.model.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonaDAO {

    public PersonaDAO() { }

    public boolean add(Persona persona) {
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO personas VALUES(?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, persona.getDni());
            statement.setString(2, persona.getNombre());
            statement.setLong(3, persona.getTelefono());

            statement.executeUpdate();

            connection.close();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean delete(String dni) {
        Connection connection = DBConnection.getConnection();
        String sql = "DELETE FROM personas WHERE dni=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, dni);

            int rowsDeleted = statement.executeUpdate();

            connection.close();

            return rowsDeleted != 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean update(String dni, String nombre, Long telefono) {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE personas SET nombre=?, telefono=? WHERE dni=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, nombre);
            statement.setLong(2, telefono);
            statement.setString(3, dni);

            int rowsUpdated = statement.executeUpdate();

            connection.close();

            return rowsUpdated != 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Persona get(String dni) {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM personas WHERE dni=?";
        Persona persona = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, dni);

            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                persona = new Persona(resultado.getString(1), resultado.getString(2), resultado.getLong(3));
            }

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return persona;
    }

    public ArrayList<Persona> get() {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM personas";
        ArrayList<Persona> personas = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                Persona persona = new Persona(resultado.getString(1), resultado.getString(2), resultado.getLong(3));
                personas.add(persona);
            }

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personas;
    }
}
