package com.gtrack;

import com.gtrack.view.FormLogin;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    public static void main(String[] args) {
        // Define o look and feel do sistema operacional (opcional)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace(); // Loga o erro, mas não interrompe
        }

        // Inicia a interface gráfica de forma thread-safe
        SwingUtilities.invokeLater(() -> {
            new FormLogin().setVisible(true);
        });
    }
}