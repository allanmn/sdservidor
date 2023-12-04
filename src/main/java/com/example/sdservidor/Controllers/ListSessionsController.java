package com.example.sdservidor.Controllers;

import com.example.sdservidor.DAO.SessionDAO;
import com.example.sdservidor.Models.JwtSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ListSessionsController {
    @FXML
    private TableView<JwtSession> sessionTableView;

    @FXML
    public void initialize() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getData();
            }
        }, 0, 5000);
    }

    public void getData() {
        ObservableList<JwtSession> sessions = FXCollections.observableArrayList();

        this.sessionTableView.setItems(sessions);

        List<JwtSession> sessionList = new SessionDAO().getAllSessions();

        assert sessionList != null;
        for (JwtSession point : sessionList) {
            this.sessionTableView.getItems().add(point);
        }
    }
}