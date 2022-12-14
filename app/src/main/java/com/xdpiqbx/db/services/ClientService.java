package com.xdpiqbx.db.services;

import com.xdpiqbx.db.DataModels.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            "INSERT INTO client (name) VALUES (?)"
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
        return -1;
    }
    public String getById(long id){
        return null;
    }
    public void setName(long id, String name){

    }
    public void deleteById(long id){

    }
    public List<Client> listAll(){
        return null;
    }
}
