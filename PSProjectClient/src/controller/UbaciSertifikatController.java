/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Klijent;
import domain.NivoFizickeSpreme;
import domain.Sertifikat;
import forme.UbaciSertifikatForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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
