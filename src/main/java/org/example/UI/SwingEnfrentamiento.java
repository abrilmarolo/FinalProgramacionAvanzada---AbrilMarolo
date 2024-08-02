package org.example.UI;

import org.example.DAO.EntrenadorDAO;
import org.example.DAO.EntrenadorDAOImpl;
import org.example.Model.Entrenador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingEnfrentamiento extends JFrame{
    JButton boton;
    JTextField textField, textField1;
    JLabel label, label1;
    public SwingEnfrentamiento() {
        this.setTitle("Enfrentamiento");
        this.setSize(480, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setVisible(true);

        textField = new JTextField(15);
        textField.setBounds(130, 45, 200, 25);
        label = new JLabel("ID del entrenador uno");
        label.setBounds(167, 20, 127, 25);
        textField1 = new JTextField(15);
        textField1.setBounds(130, 110, 200, 25);
        label1 = new JLabel("ID del entrenador dos");
        label1.setBounds(167, 85, 127, 25);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 500, 200);
        panel.setLayout(null);
        panel.add(label);
        panel.add(textField);
        panel.add(label1);
        panel.add(textField1);

        boton = new JButton("Pelear");
        boton.setBounds(170, 160, 120, 30);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendData();
                dispose();
            }
        });
        panel.add(boton);
        add(panel);
    }

    private void sendData() {
        int entrenadorId = Integer.parseInt(textField.getText());
        int otroEntrenadorId = Integer.parseInt(textField1.getText());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SwingResultado resultado = new SwingResultado(entrenadorId, otroEntrenadorId);
                resultado.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingEnfrentamiento form = new SwingEnfrentamiento();
        form.setVisible(true);
    }
}
