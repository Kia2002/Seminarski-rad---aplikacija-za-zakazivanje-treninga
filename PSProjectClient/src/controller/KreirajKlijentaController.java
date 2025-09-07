/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Klijent;
import domain.NivoFizickeSpreme;
import domain.Trener;

import forme.KreirajKlijentaForma;
import forme.PrikazKlijenataForma;
import forme.model.KlijentTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import komunikacija.Komunikacija;
import kordinator.Kordinator;

/**
 *
 * @author Aleksa
 */
public class KreirajKlijentaController {
    private final KreirajKlijentaForma dkf;

    public KreirajKlijentaController(KreirajKlijentaForma dkf) {
        this.dkf = dkf;
        addActionListener();
    }

    public void otvoriFormu() throws Exception {
        pripremiFormu();
        dkf.setVisible(true);
    } 

    private void addActionListener() {
        dkf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String ime = dkf.getTxtIme().getText().trim();
                String prezime = dkf.getTxtPrezime().getText().trim();
                String email = dkf.getTxtEmail().getText().trim();

                
                if(ime.isEmpty() && prezime.isEmpty() && email.isEmpty()){
                    JOptionPane.showMessageDialog(dkf, "Sistem ne može da kreira klijenta.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(ime.isEmpty()){
                    JOptionPane.showMessageDialog(dkf, "Potrebno je da unesete ime klijenta.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!ime.matches("[a-zA-ZšđčćžŠĐČĆŽ\\s]+") || ime.length() <= 2) {
                    JOptionPane.showMessageDialog(dkf, "Ime koje ste uneli nije odgovarajuće.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(dkf, "Potrebno je da unesete prezime klijenta.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!prezime.matches("[a-zA-ZšđčćžŠĐČĆŽ\\s]+") || prezime.length() <= 2) {
                    JOptionPane.showMessageDialog(dkf, "Prezime koje ste uneli nije odgovarajuće.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(dkf, "Potrebno je da unesete email klijenta.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(dkf, "Email nije u odgovarajućem formatu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                NivoFizickeSpreme nivo = (NivoFizickeSpreme) dkf.getCmbNivo().getSelectedItem();
                if (nivo == null || nivo.getNivo().equals("Odaberite nivo")) {
                    JOptionPane.showMessageDialog(dkf, "Potrebno je da selektujete nivo fizičke spreme.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Klijent k = new Klijent(-1L, ime, prezime, email, nivo);

              
                try {
                    Komunikacija.getInstance().dodajKlijenta(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je kreirao klijenta.", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                }  catch(Exception ex) {
                    JOptionPane.showMessageDialog(dkf, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void pripremiFormu() throws Exception {
        List<NivoFizickeSpreme> lista = Komunikacija.getInstance().ucitajNivoe();

        NivoFizickeSpreme prazno = new NivoFizickeSpreme();
        prazno.setNivo("Odaberite kategoriju");
        lista.add(0, prazno);

        for (NivoFizickeSpreme n : lista) {
            dkf.getCmbNivo().addItem(n);
        }
    }
}