 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Klijent;
import domain.Trener;
import forme.GlavnaForma;
import forme.PrikazKlijenataForma;
import forme.model.KlijentTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import kordinator.Kordinator;

/**
 *
 * @author Aleksa
 */
public class PrikazKlijenataController {
    private final PrikazKlijenataForma pkf;
    
    
    public PrikazKlijenataController(PrikazKlijenataForma pkf) {
        this.pkf = pkf;
        addActionListener();
    }

    private void addActionListener() {
        pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getTblKlijent().getSelectedRow();
                if(red==-1){
                JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                }else{
                    
                        JOptionPane.showMessageDialog(pkf, "Sistem je našao klijenta.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        KlijentTableModel ktm  = (KlijentTableModel) pkf.getTblKlijent().getModel();
                        Klijent k = ktm.getKlijenti().get(red);
                        try {
                        komunikacija.Komunikacija.getInstance().obrisiKlijenta(k);
                        JOptionPane.showMessageDialog(pkf, "Sistem je uspešno obrisao klijenta.", "Uspešno", JOptionPane.INFORMATION_MESSAGE); 
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pkf, "Sistem ne može da obriše klijenta.", "Greška", JOptionPane.ERROR_MESSAGE); 
                    }
            }
            }
        });
    }

    public void otvoriFormu() throws Exception {
        pripremiFormu();
        pkf.setVisible(true);
    } 

    public void pripremiFormu() throws Exception {
        List<Klijent> klijenti =    komunikacija.Komunikacija.getInstance().ucitajKlijente();
        KlijentTableModel ktp = new KlijentTableModel(klijenti);
        pkf.getTblKlijent().setModel(ktp);
    }
}
