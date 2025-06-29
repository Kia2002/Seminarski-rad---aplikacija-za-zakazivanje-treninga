/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import domain.Trener;
import forms.FrmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Aleksa
 */
public class LoginController {
    private final FrmLogin lf;

    public LoginController(FrmLogin lf) {
        this.lf = lf;
        init();
    }

    private void init() {
        lf.loginAddActionListener(e -> prijava());
    }

    private void prijava() {
        try {
            String username = lf.getTxtUsername().getText().trim();
            String password = String.valueOf(lf.getTxtPassword().getPassword()).trim();

            Communication.getInstance().connection();
            Trener ulogovani = Communication.getInstance().login(username, password);

            if (ulogovani == null) {
                JOptionPane.showMessageDialog(lf, "Uneli ste pogrešne kredencijale!", "Greška", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(lf, "Prijava uspešna!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                
                SwingUtilities.getWindowAncestor(lf).setVisible(false);

                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(lf, "Greška prilikom prijave.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void openForm() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(lf);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

        

