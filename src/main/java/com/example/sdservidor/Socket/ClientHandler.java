package com.example.sdservidor.Socket;

import com.example.sdservidor.Actions.ActionsHandler;
import com.example.sdservidor.DAO.SessionDAO;
import com.example.sdservidor.Models.JwtSession;
import com.example.sdservidor.Senders.BaseSender;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final ObjectMapper objectMapper;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void run() {
        try {
            // Crie leitores e escritores para comunicação com o cliente
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            String message = null;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received message from client: " + message);

                // Parse JSON message
                JsonNode jsonNode = objectMapper.readTree(message);
                String action = jsonNode.get("action").asText();

                // Send action and data to ActionHandler
                BaseSender response = ActionsHandler.handleAction(action, message, clientSocket.getRemoteSocketAddress().toString().split(Pattern.quote(":"))[0].replace("/",""));

                System.out.println();

                // Send the response back to the client
                System.out.println("Sending back to client: " + response.toJson());
                writer.println(response.toJson());
            }
        } catch (IOException e) {
            SessionDAO dao = new SessionDAO();

            JwtSession session = dao.getSessionByIp(clientSocket.getRemoteSocketAddress().toString().split(Pattern.quote(":"))[0].replace("/",""));

            dao.delete(session);

            System.out.println("Erro 1");
            e.printStackTrace();
            try {
                System.out.println("Socket closed!");
                clientSocket.close();
            } catch (IOException ex) {
                System.out.println("Erro 2");
                e.printStackTrace();
            }
        }
    }
}