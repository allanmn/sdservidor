package com.example.sdservidor.Controllers;

import com.example.sdservidor.Authentication.JwtService;
import com.example.sdservidor.DAO.UserDAO;
import com.example.sdservidor.Helpers.HelperService;
import com.example.sdservidor.Main;
import com.example.sdservidor.Models.ConnectModalResult;
import com.example.sdservidor.Models.User;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InitialController {

    public Label message;

    private Main main;

    public void initialize() {
        initializeFirstUser();

        openList();

        openDialog();
    }

    public void openDialog() {
        Platform.runLater(() -> {
            ConnectModalResult result = Main.openConnectDialog();

            if (result != null && !Objects.equals(result.getPort(), "")) {
                String port = result.getPort();

                main.startServer(port);
            } else {
                openDialog();
            }
        });
    }

    public void openList() {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("list-sessions.fxml"));

            try {
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setTitle("Listar Sessões");
                stage.setScene(scene);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                HelperService.showErrorMessage(e.getMessage());
            }
    }

    private void initializeFirstUser() {
        try {
            User user = new User("Admin", "admin@admin.com", JwtService.hashPassword("0192023A7BBD73250516F069DF18B500"), "admin");
            new UserDAO().addUserIfNotExistByEmail(user);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
}