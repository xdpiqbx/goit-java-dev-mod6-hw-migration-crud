package com.xdpiqbx.db.services;

import com.xdpiqbx.common.Helper;
import com.xdpiqbx.db.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitService {
    public static void main(String[] args) {
        initDatabase();
    }
    public static void initDb(Database db, String pathToFile){
        try {
            Connection connection = db.getConnection();
            String sql = String.join("\n", Files.readAllLines(Paths.get(pathToFile)));
            connection.prepareStatement(sql).executeUpdate();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void initDatabase(){
        String initDbFile = Helper.env("SQL_FILES_PATH") + "init_db.sql";
        initDb(Database.getInstance(), initDbFile);
    }
}
