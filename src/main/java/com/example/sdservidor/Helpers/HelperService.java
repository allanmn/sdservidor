package com.example.sdservidor.Helpers;

import com.example.sdservidor.Authentication.JwtService;

import javax.swing.JOptionPane;

public class HelperService {
    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static long getUserIdFromToken(String token) {
        return JwtService.getUserIdFromJwt(token);
    }
}
