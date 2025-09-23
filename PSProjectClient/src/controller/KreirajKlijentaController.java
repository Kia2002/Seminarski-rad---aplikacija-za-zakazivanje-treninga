/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Klijent;
import domain.NivoFizickeSpreme;
import domain.Trener;
import forme.FormaMod;
import static forme.FormaMod.IZMENI;

import forme.KreirajKlijentaForma;
import forme.PrikazKlijenataForma;
import forme.model.ModelTabeleKlijent;
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

    public void otvoriFormu(FormaMod mod) throws Exception {
      
        pripremiFormu(mod);
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
                    JOptionPane.showMessageDialog(dkf, "Sistem ne može da zapamti klijenta.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(ime.isEmpty()){
                    JOptionPane.showMessageDialog(dkf, "Potrebno je da unesete ime klijenta.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!ime.matches("[a-zA-ZšđčćžŠĐČĆŽ\\s]+") || ime.length() <= 2) {
                    JOptionPane.showMessageDialog(dkf, "Ime koje ste uneli nije odgovarajuće.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(dkf, "Potrebno je da unesete prezime klijenta.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!prezime.matches("[a-zA-ZšđčćžŠĐČĆŽ\\s]+") || prezime.length() <= 2) {
                    JOptionPane.showMessageDialog(dkf, "Prezime koje ste uneli nije odgovarajuće.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(dkf, "Potrebno je da unesete email klijenta.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(dkf, "Email nije u odgovarajućem formatu", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                NivoFizickeSpreme nivo = (NivoFizickeSpreme) dkf.getCmbNivo().getSelectedItem();
                if (nivo == null || nivo.getNivo().equals("Odaberite nivo")) {
                    JOptionPane.showMessageDialog(dkf, "Potrebno je da selektujete nivo fizičke spreme.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Klijent k = new Klijent(-1L, ime, prezime, email, nivo);

              
                try {
                    Komunikacija.getInstance().dodajKlijenta(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio klijenta.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                }  catch(Exception ex) {
                    JOptionPane.showMessageDialog(dkf, ex.getMessage(), "Neuspešno", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        dkf.AzurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                azuriraj(e);
            }

            private void azuriraj(ActionEvent e) {
                Long id = Long.parseLong(dkf.getTxtId().getText().trim());
                String ime = dkf.getTxtIme().getText().trim();
                String prezime = dkf.getTxtPrezime().getText().trim();
                String email = dkf.getTxtEmail().getText().trim();

                
                if(ime.isEmpty() && prezime.isEmpty() && email.isEmpty()){
                    JOptionPane.showMessageDialog(dkf, "Sistem ne može da zapamti klijenta.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(ime.isEmpty()){
                    JOptionPane.showMessageDialog(dkf, "Potrebno je da unesete ime klijenta.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!ime.matches("[a-zA-ZšđčćžŠĐČĆŽ\\s]+") || ime.length() <= 2) {
                    JOptionPane.showMessageDialog(dkf, "Ime koje ste uneli nije odgovarajuće.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(dkf, "Potrebno je da unesete prezime klijenta.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!prezime.matches("[a-zA-ZšđčćžŠĐČĆŽ\\s]+") || prezime.length() <= 2) {
                    JOptionPane.showMessageDialog(dkf, "Prezime koje ste uneli nije odgovarajuće.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(dkf, "Potrebno je da unesete email klijenta.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(dkf, "Email nije u odgovarajućem formatu", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                NivoFizickeSpreme nivo = (NivoFizickeSpreme) dkf.getCmbNivo().getSelectedItem();
                if (nivo == null || nivo.getNivo().equals("Odaberite nivo")) {
                    JOptionPane.showMessageDialog(dkf, "Potrebno je da selektujete nivo fizičke spreme.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Klijent k = new Klijent(id, ime, prezime, email, nivo);

              
                try {
                    Komunikacija.getInstance().azurirajKlijenta(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio klijenta.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                }  catch(Exception ex) {
                    JOptionPane.showMessageDialog(dkf, ex.getMessage(), "Neuspešno", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    

    private void pripremiFormu(FormaMod mod) throws Exception {
        switch(mod){
            case DODAJ: 
                List<NivoFizickeSpreme> lista = Komunikacija.getInstance().ucitajNivoe();
        
        for (NivoFizickeSpreme n : lista) {
            dkf.getCmbNivo().addItem(n);
        }
                dkf.getTxtId().setEnabled(false);
                dkf.getBtnAzuriraj().setVisible(false);
                dkf.getBtnDodaj().setVisible(true);
                dkf.getBtnDodaj().setEnabled(true);
                dkf.getLblId().setVisible(false);
                dkf.getTxtId().setVisible(false);
                komunikacija.Komunikacija.getInstance().ucitajNivoe();
                break;
                
                
            case IZMENI:
        List<NivoFizickeSpreme> lista1 = Komunikacija.getInstance().ucitajNivoe();
        
        for (NivoFizickeSpreme n : lista1) {
            dkf.getCmbNivo().addItem(n);
        }
                dkf.getBtnAzuriraj().setVisible(true);
                dkf.getBtnDodaj().setVisible(false);
                dkf.getBtnAzuriraj().setEnabled(true);
                Klijent k = (Klijent) kordinator.Kordinator.getInstance().vratiParam("klijent");
                dkf.getTxtIme().setText(k.getIme());
                dkf.getTxtPrezime().setText(k.getPrezime());
                dkf.getTxtEmail().setText(k.getEmail());
                dkf.getCmbNivo().setSelectedItem(k.getNivoFizickeSpreme());
                dkf.getTxtId().setText(k.getIdKlijent()+"");
                dkf.getTxtId().setEnabled(false);
                break;
        case DETALJI:
        List<NivoFizickeSpreme> lista2 = Komunikacija.getInstance().ucitajNivoe();
        
        for (NivoFizickeSpreme n : lista2) {
            dkf.getCmbNivo().addItem(n);
        }
                dkf.getBtnAzuriraj().setVisible(false);
                dkf.getBtnDodaj().setVisible(false);
                dkf.getBtnAzuriraj().setEnabled(false);
                Klijent k2 = (Klijent) kordinator.Kordinator.getInstance().vratiParam("klijent");
                dkf.getTxtIme().setText(k2.getIme());
                dkf.getTxtPrezime().setText(k2.getPrezime());
                dkf.getTxtEmail().setText(k2.getEmail());
                dkf.getCmbNivo().setSelectedItem(k2.getNivoFizickeSpreme());
                dkf.getTxtId().setText(k2.getIdKlijent()+"");
                dkf.getTxtId().setEnabled(false);
                dkf.getTxtIme().setEnabled(false);
                dkf.getTxtPrezime().setEnabled(false);
                dkf.getTxtEmail().setEnabled(false);
                dkf.getCmbNivo().setEnabled(false);
                break;
        }
    }
}