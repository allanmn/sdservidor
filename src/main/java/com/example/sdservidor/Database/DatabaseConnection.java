package com.example.sdservidor.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:sd.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexão com o SQLite estabelecida.");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao SQLite: " + e.getMessage());
        }
        return conn;
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
