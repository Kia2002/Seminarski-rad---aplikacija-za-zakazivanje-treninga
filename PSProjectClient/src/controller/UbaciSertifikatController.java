/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Klijent;
import domain.NivoFizickeSpreme;
import domain.Sertifikat;
import forme.UbaciSertifikatForma;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import komunikacija.Komunikacija;

/**
 *
 * @author Aleksa
 */
public class UbaciSertifikatController {
    private final UbaciSertifikatForma usf;

    public UbaciSertifikatController(UbaciSertifikatForma usf) {
        this.usf = usf;
        addActionListener();
        applyTheme();
    }
    
    private void applyTheme() {
   
    JButton[] buttons = { usf.getBtnDodaj() };
    for (JButton b : buttons) {
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setBackground(new Color(45, 137, 239));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        b.setPreferredSize(new Dimension(140, 35));
    }

   
    JTextField[] textFields = { usf.getTxtNaziv() };
    for (JTextField tf : textFields) {
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setBackground(Color.WHITE);
    }

    
    JTextArea[] textAreas = { usf.getTxtOpis() };
    for (JTextArea ta : textAreas) {
        ta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ta.setBackground(Color.WHITE);
    }

   
    usf.getContentPane().setBackground(new Color(245, 245, 245));
}

    
    public void otvoriFormu() throws Exception {
      
      
        usf.setVisible(true);
    } 
    
   
        
        private void addActionListener() {
        usf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String naziv = usf.getTxtNaziv().getText().trim().toUpperCase();
        String opis = usf.getTxtOpis().getText().trim();
        
        if(naziv.isEmpty()||opis.isEmpty()){
        JOptionPane.showMessageDialog(usf,"Sistem ne može da zapamti sertifikat." , "Neuspešno", JOptionPane.ERROR_MESSAGE);
        return;
        }
             Sertifikat s = new Sertifikat(naziv,opis);
             
                try {
                    Komunikacija.getInstance().ubaciSertifikat(s);
                    JOptionPane.showMessageDialog(usf, "Sistem je zapamtio sertifkat.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    usf.dispose();
                }  catch(Exception ex) {
                    JOptionPane.showMessageDialog(usf, ex.getMessage(), "Neuspešno", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
       
        
    }

   
    
    
    
}
