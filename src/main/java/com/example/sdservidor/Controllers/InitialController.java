package com.example.sdservidor.Controllers;

import com.example.sdservidor.*;
import com.example.sdservidor.Helpers.HelperService;
import com.example.sdservidor.Models.ConnectModalResult;
import com.example.sdservidor.Socket.Server;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class InitialController {

    public Label message;

    private Main main;

    public void initialize() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        pause.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                openDialog();
            }
        });

        pause.play();
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

    public void setMain(Main main) {
        this.main = main;
    }
}