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
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    Server server = null;

    Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        disableHibernateConsoleLogging();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("initial.fxml"));

        mainStage = stage;

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        InitialController controller = fxmlLoader.getController();

        controller.setMain(this);

        mainStage.setTitle("CONECTANDO...");
        mainStage.setScene(scene);
        mainStage.show();
    }

    private static void disableHibernateConsoleLogging() {
        Logger hibernateLogger = Logger.getLogger("org.hibernate");
        hibernateLogger.setLevel(Level.SEVERE); // Set the desired logging level, e.g., Level.SEVERE

        // Disable console output for Hibernate
        for (Handler handler : hibernateLogger.getParent().getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                handler.setLevel(Level.SEVERE); // Set the desired console logging level
            }
        }
    }

    public void startServer(String port) {
            server = new Server(Integer.parseInt(port));

            mainStage.hide();

            new Thread(() -> {
                try {
                    server.start();
                } catch (IOException e) {
                    HelperService.showErrorMessage(e.getMessage());
                }
            }).start();
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
}