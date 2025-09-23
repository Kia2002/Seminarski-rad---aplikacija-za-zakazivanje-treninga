/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import komunikacija.Komunikacija;
import kordinator.Kordinator;
import domain.Trener;
import forme.LoginForma;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
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
    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        customizeUI();
        addActionListener();
    }
    
    private void customizeUI() {
    Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
    Font fontInput = new Font("Segoe UI", Font.PLAIN, 14);
    Font fontButton = new Font("Segoe UI", Font.BOLD, 14);

    lf.getLblUsername().setFont(fontLabel);
    lf.getLblPassword().setFont(fontLabel);
    lf.getTxtUsername().setFont(fontInput);
    lf.getTxtPassword().setFont(fontInput);
    lf.getBtnLogin().setFont(fontButton);
    lf.getBtnCancel().setFont(fontButton);

    
    lf.getBtnLogin().setBackground(new Color(70, 130, 180));
    lf.getBtnLogin().setForeground(Color.WHITE);
    lf.getBtnLogin().setContentAreaFilled(true);
    lf.getBtnCancel().setBackground(new Color(220, 53, 69));
    lf.getBtnCancel().setForeground(Color.WHITE);
    lf.getBtnCancel().setContentAreaFilled(true);

    
    lf.setOpaque(true);  
    lf.setBackground(new Color(245, 245, 245));

    
    lf.getBtnLogin().setCursor(new Cursor(Cursor.HAND_CURSOR));
    lf.getBtnCancel().setCursor(new Cursor(Cursor.HAND_CURSOR));
    lf.setOpaque(true);
    lf.setBackground(new Color(245, 245, 245));
}
    private void addActionListener() {
        lf.loginAddActionListener(e -> login());
    }

    private void login() {
        try {
            String username = lf.getTxtUsername().getText().trim();
            String password = String.valueOf(lf.getTxtPassword().getPassword()).trim();

            Komunikacija.getInstance().connection();
            Trener ulogovani = Komunikacija.getInstance().login(username, password);

            if (ulogovani == null) {
                JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni!", "Greška", JOptionPane.ERROR_MESSAGE);
            } else {
                Kordinator.getInstance().setUlogovani(ulogovani);
                JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                Kordinator.getInstance().otvoriGlavnuFormu();
                SwingUtilities.getWindowAncestor(lf).setVisible(false);

                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(lf, "Greška prilikom prijave.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void otvoriFormu() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(lf);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

        

