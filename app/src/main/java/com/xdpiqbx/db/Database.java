package com.xdpiqbx.db;

import com.xdpiqbx.common.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final Database INSTANCE = new Database();
    private Connection connection;
    private Database() {
        try{
            connection = DriverManager.getConnection(Helper.env("DB_URL"));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static Database getInstance(){
        return INSTANCE;
    }

    public int executeUpdate(String sql){
        try(Statement st = connection.createStatement()) {
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Connection getConnection(){
        return connection;
    }
    public void close(){
        try{
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
