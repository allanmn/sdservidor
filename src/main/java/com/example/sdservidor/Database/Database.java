package com.example.sdservidor.Database;

import com.example.sdservidor.Authentication.JwtService;
import com.example.sdservidor.DAO.UserDAO;
import com.example.sdservidor.Models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:src/main/resources/com/example/sdservidor/database/database.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexão com o SQLite estabelecida.");

            up(conn);

            createFirstUser();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao SQLite: " + e.getMessage());
        }
        return conn;
    }

    public static void createFirstUser() {
        User user = new User("Admin", "admin@admin.com", JwtService.hashPassword("E7D80FFEEFA212B7C5C55700E4F7193E"), "admin");
        new UserDAO().addUserIfNotExistByEmail(user);
    }

    public static void up(Connection connection) {
        createUsers(connection);
    }

    public static void createUsers(Connection connection) {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS usuarios (\n"
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "nome TEXT,\n"
                    + "email TEXT UNIQUE,\n"
                    + "tipo TEXT UNIQUE,\n"
                    + "senha TEXT\n"
                    + ");";
            Statement statement = connection.createStatement();
            // Execute the SQL statement to create the table
            statement.execute(sql);
            System.out.println("Table 'usuarios' created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void disconnect(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão com o SQLite fechada.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o SQLite: " + e.getMessage());
            }
        }
    }
}