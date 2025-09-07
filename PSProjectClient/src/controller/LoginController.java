/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import komunikacija.Komunikacija;
import kordinator.Kordinator;
import domain.Trener;
import forme.LoginForma;
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
        addActionListener();
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

        

