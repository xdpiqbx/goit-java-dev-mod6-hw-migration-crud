package com.xdpiqbx.db.services;

import com.xdpiqbx.db.DataModels.Client;

import java.sql.*;
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
        //setNameSt
    }
    public void deleteById(long id){
        //deleteByIdSt
    }
    public List<Client> listAll(){
        //getAllSt
        return null;
    }
}
