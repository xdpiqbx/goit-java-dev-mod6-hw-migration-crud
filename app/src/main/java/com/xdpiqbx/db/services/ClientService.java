package com.xdpiqbx.db.services;

import com.xdpiqbx.db.DataModels.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private PreparedStatement createSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement setNameSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement getAllSt;
    public ClientService(Connection connection){
        try {
            this.createSt = connection.prepareStatement(
            "INSERT INTO client (name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS
            );
            this.getByIdSt = connection.prepareStatement(
            "SELECT name FROM client WHERE id = ?"
            );
            this.setNameSt = connection.prepareStatement(
            "UPDATE client SET name = ? WHERE id = ?"
            );
            this.deleteByIdSt = connection.prepareStatement(
            "DELETE FROM client WHERE id = ?"
            );
            this.getAllSt = connection.prepareStatement(
            "SELECT id, name FROM client"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public long create(String name){
        try {
            isValidName(name);
            createSt.setString(1, name);
            int affectedRows = createSt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating client failed, no rows affected.");
            }
            try (ResultSet generatedKeys = createSt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating client failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String getById(long id){
        try{
            getByIdSt.setLong(1, id);
            try(ResultSet rs = getByIdSt.executeQuery()){
                if(!rs.next()){
                    throw new SQLException("Selecting client failed, ID: ["+id+"] isn't existing.");
                }
                return rs.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setName(long id, String name){
        try {
            isValidName(name);
            setNameSt.setString(1, name);
            setNameSt.setLong(2, id);
            setNameSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteById(long id){
        try {
            deleteByIdSt.setLong(1, id);
            deleteByIdSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Client> listAll(){
        try(ResultSet rs = getAllSt.executeQuery()){
            List<Client> clientsList = new ArrayList<>();
            while(rs.next()){
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                clientsList.add(client);
            }
            return clientsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void isValidName(String name) throws IllegalArgumentException{
        // CHECK (CHAR_LENGTH(name) > 1 AND CHAR_LENGTH(name) < 1000)
        int nameLength = name.length();
        boolean dbCheckName = nameLength > 1 && nameLength < 1000;
        if(!dbCheckName){
            throw new IllegalArgumentException("Invalid name length");
        }
    }
}