package com.example.sdservidor;

import com.example.sdservidor.Controllers.ConnectDialogController;
import com.example.sdservidor.Controllers.InitialController;
import com.example.sdservidor.Helpers.HelperService;
import com.example.sdservidor.Models.ConnectModalResult;
import com.example.sdservidor.Socket.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    Server server = null;

    private static final Map<String, String> loggedInUsers = new HashMap<>();
    Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("initial.fxml"));

        mainStage = stage;

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        InitialController controller = fxmlLoader.getController();

        controller.setMain(this);

        mainStage.setTitle("CONECTANDO...");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void startServer(String port) {
        try {
            server = new Server(Integer.parseInt(port));

            mainStage.hide();

            server.start();
        } catch (IOException ex) {
            HelperService.showErrorMessage(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public static ConnectModalResult openConnectDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("connect_dialog.fxml"));
            loader.setController(new ConnectDialogController());
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("CONECTANDO AO SERVIDOR...");
            dialog.getDialogPane().setContent(loader.load());
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

            // Wait for the dialog to be closed
            dialog.showAndWait();

            // Retrieve the input values from the controller
            ConnectDialogController controller = loader.getController();
            String port = controller.getPort();

            return new ConnectModalResult(port);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void storeLoggedInUser(String username, String jwt) {
        loggedInUsers.put(jwt, username);
    }

    public static void removeLoggedInUser(String jwt) {
        loggedInUsers.remove(jwt);
    }
}