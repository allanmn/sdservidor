package com.example.sdservidor.Helpers;

import javax.swing.JOptionPane;

public class HelperService {
    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
