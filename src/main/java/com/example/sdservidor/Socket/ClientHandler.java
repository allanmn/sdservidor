package com.example.sdservidor.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Crie leitores e escritores para comunicação com o cliente
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String message = reader.readLine();
            System.out.println("Received message from client: " + message);

            // Process the message (in this example, simply echoing it back)
            String response = "Server received: " + message;

            // Send the response back to the client
            writer.println(response);

        } catch (IOException e) {
            e.printStackTrace();
            try {
                clientSocket.close();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }
    }
}