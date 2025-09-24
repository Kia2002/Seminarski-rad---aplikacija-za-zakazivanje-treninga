/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.EvidencijaTreninga;
import domain.Klijent;
import domain.NivoFizickeSpreme;
import domain.StatusStavke;
import domain.StavkaEvidencijeTreninga;
import domain.Termin;
import domain.Trener;
import forme.FormaMod;
import static forme.FormaMod.DETALJI;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.KreirajEvidencijuTreningaForma;
import forme.KreirajKlijentaForma;
import forme.model.ModelTabeleEvidencijeTreninga;
import forme.model.StavkaTableModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import komunikacija.Komunikacija;
import kordinator.Kordinator;

/**
 *
 * @author Aleksa
 */
public class KreirajEvidencijuTreningaController {

    private final KreirajEvidencijuTreningaForma def;
    private int trenutniRedniBroj = 1;
    private StavkaTableModel modelTabele;
    private List<StavkaEvidencijeTreninga> stavke;
    private int ukupnaCena;
    private EvidencijaTreninga ev;

    public KreirajEvidencijuTreningaController(KreirajEvidencijuTreningaForma def) {
        this.def = def;
        addActionListener();
        applyTheme(); 
    }
    
    private void applyTheme() {
    
    JButton[] buttons = {
        def.getBtnDodaj(), def.getBtnDodajStavku(),
        def.getBtnIzmeni(), def.getBtnObrisiStavku()
    };
    for (JButton b : buttons) {
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setBackground(new Color(45, 137, 239)); 
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        Dimension dim = new Dimension(140, 35); 
        b.setPreferredSize(dim);
    }

    
    JTextField[] textFields = {def.getTxtVremeOd(), def.getTxtVremeDo(), def.getTxtUkupnaCena()};
    for (JTextField tf : textFields) {
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setBackground(new Color(245, 245, 245));
    }

    JComboBox<?>[] combos = {def.getCmbTermin(), def.getCmbOcena(), def.getCmbTrener(), def.getCmbKlijent()};
    for (JComboBox<?> cb : combos) {
        cb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cb.setBackground(Color.WHITE);
    }

 
    JTable[] tables = {def.getTblStavke()};
    for (JTable t : tables) {
        t.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        t.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        t.setRowHeight(25);
    }

   
    def.setBackground(new Color(245, 245, 245)); 
}
    
    public void otvoriFormu(FormaMod mod) throws Exception {
        pripremiFormu(mod);
        def.setVisible(true);
    }

private void addActionListener() {

    def.dodajStavkuAddActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dodaj(e);
        }

        private void dodaj(ActionEvent e) {
            Termin te = (Termin) def.getCmbTermin().getSelectedItem();
            LocalTime vremeOd, vremeDo;
            try {
                vremeOd = LocalTime.parse(def.getTxtVremeOd().getText(), DateTimeFormatter.ofPattern("HH:mm"));
                vremeDo = LocalTime.parse(def.getTxtVremeDo().getText(), DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null,
                        "Vreme mora biti u formatu HH:mm (npr. 09:30).",
                        "Greška",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Long ocena = Long.parseLong((String) def.getCmbOcena().getSelectedItem());
            Long rb = (long) trenutniRedniBroj;
            Long brojSati = ChronoUnit.HOURS.between(vremeOd, vremeDo);
           
            if (brojSati > 2) {
             JOptionPane.showMessageDialog(null, "Trening mora trajati kraće od 3 sata.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
                }
            if (vremeOd.isAfter(vremeDo)) {
                JOptionPane.showMessageDialog(null, "Vreme od mora biti pre vremena do.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            
            Long cena = brojSati * te.getCenaPoSatu();
            for (StavkaEvidencijeTreninga stavka : modelTabele.getAktivneStavke()) {
                if (te.getDatum().equals(stavka.getTermin().getDatum())) {
                    if ((vremeOd.isBefore(stavka.getVremeDo()) && vremeDo.isAfter(stavka.getVremeOd()))) {
                        JOptionPane.showMessageDialog(null, "Trening se preklapa sa već postojećim!", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            StavkaEvidencijeTreninga stavka = new StavkaEvidencijeTreninga(rb, ocena, vremeOd, vremeDo, cena, te);
            stavka.setStatus(StatusStavke.NOVA);
            stavka.setEvidencijaTreninga(ev);
            stavke.add(stavka);

            modelTabele.dodajStavku(stavka);
            
            trenutniRedniBroj++;
            def.getTxtUkupnaCena().setText(String.valueOf(modelTabele.getUkupnaCena()));
            def.getTxtVremeOd().setText("");
            def.getTxtVremeDo().setText("");
            def.getCmbTermin().setSelectedIndex(0);
            def.getCmbOcena().setSelectedIndex(0);

           
            
        }
    });

    def.dodajAddActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dodaj(e);
        }

        private void dodaj(ActionEvent e) {
            try {
                Trener trener = (Trener) def.getCmbTrener().getSelectedItem();
                Klijent klijent = (Klijent) def.getCmbKlijent().getSelectedItem();

                ev.setKlijent(klijent);
                ev.setTrener(trener);
                ev.setUkupnaCena(modelTabele.getUkupnaCena());
                if (stavke.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Sistem ne može da zapamti evidenciju treninga.",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ev.setStavke(stavke);
                try {
                    Komunikacija.getInstance().kreirajEvidenciju(ev);
                    JOptionPane.showMessageDialog(def, "Sistem je zapamtio evidenciju treninga.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    def.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(def, ex.getMessage(), "Neuspešno", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    def.obrisiStavkuAddActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int red = def.getTblStavke().getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(def, "Morate da selektujete stavku", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int potvrda = JOptionPane.showConfirmDialog(def, "Da li ste sigurni?", "Potvrda", JOptionPane.YES_NO_OPTION);
            if (potvrda != JOptionPane.YES_OPTION) return;

            StavkaEvidencijeTreninga s = modelTabele.getAktivneStavke().get(red);
            s.setStatus(StatusStavke.OBRISANA);
            modelTabele.obrisiStavku(s);
           
            def.getTxtUkupnaCena().setText(String.valueOf(modelTabele.getUkupnaCena()));
            System.out.println("KLASA ISCONTROLEER STATUS STAVKE: " + s.getStatus());

           
          
        }
    });

    def.izmeniAddActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Trener trener = (Trener) def.getCmbTrener().getSelectedItem();
                Klijent klijent = (Klijent) def.getCmbKlijent().getSelectedItem();

                ev.setTrener(trener);
                ev.setKlijent(klijent);

                modelTabele = (StavkaTableModel) def.getTblStavke().getModel();
                ev.setStavke(modelTabele.getStavke());

                if (modelTabele.getAktivneStavke().isEmpty()) {
                    JOptionPane.showMessageDialog(def, "Sistem ne može da zapamti evidenciju treninga", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ev.setUkupnaCena(modelTabele.getUkupnaCena());
                
                
                Komunikacija.getInstance().izmeniEvidenciju(ev);

                JOptionPane.showMessageDialog(def, "Sistem je zapamtio evidenciju treninga.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                def.dispose();
            
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(def, "Sistem ne može da zapamti evidenciju treninga.", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
}




     private void pripremiFormu(FormaMod mod) throws Exception {
      
    
    
        modelTabele = new StavkaTableModel();
        
        stavke = new ArrayList<>();
        def.getTblStavke().setModel(modelTabele);
        
        def.getTxtUkupnaCena().setText(String.valueOf(modelTabele.getUkupnaCena()));
        
        List<Trener> treneri = Komunikacija.getInstance().ucitajTrenere();
        ev = new EvidencijaTreninga();

        for (Trener t : treneri) {
            def.getCmbTrener().addItem(t);
            if(t.equals(Kordinator.getInstance().getUlogovani())){
                def.getCmbTrener().setSelectedItem(t);
                def.getCmbTrener().setEnabled(false);
            }
        }
        
        List<Klijent> klijenti = Komunikacija.getInstance().ucitajKlijente();
        for (Klijent k : klijenti) {
            def.getCmbKlijent().addItem(k);
        }
        
        List<Termin> termini = Komunikacija.getInstance().ucitajTermine();
        for (Termin te : termini) {
            def.getCmbTermin().addItem(te);
        }
        
        switch(mod){
            case DODAJ:
                def.getBtnObrisiStavku().setVisible(false);
                for (Trener t : treneri) {
                    if(t.equals(Kordinator.getInstance().getUlogovani())){
                        def.getCmbTrener().setSelectedItem(t);
                        def.getCmbTrener().setEnabled(false);
                    }
                    def.getBtnIzmeni().setVisible(false);
                }
                break;
                
            case IZMENI:
                EvidencijaTreninga e2 = (EvidencijaTreninga) Kordinator.getInstance().vratiParam("evidencija");
                ev = e2;

                for (Trener t : treneri) {
                    if (t.equals(Kordinator.getInstance().getUlogovani())) {
                        def.getCmbTrener().setSelectedItem(t);
                        def.getCmbTrener().setEnabled(false);
                    }
                }

                for (Klijent k : klijenti) {
                    if (k.equals(e2.getKlijent())) {
                        def.getCmbKlijent().setSelectedItem(k);
                    }
                }

                List<StavkaEvidencijeTreninga> stavkeZaPrikaz = Komunikacija.getInstance()
                        .ucitajStakve(e2.getIdEvidencijaTreninga());
                
               
                int maxRb = 0;
                for (StavkaEvidencijeTreninga s : stavkeZaPrikaz) {
                    if (s.getStatus() == null) {
                        s.setStatus(StatusStavke.POSTOJECA);
                    }
                    s.setEvidencijaTreninga(ev);
                    if (s.getRb() > maxRb) {
                        maxRb = s.getRb().intValue();
                    }
                }

                modelTabele = new StavkaTableModel(stavkeZaPrikaz);
                def.getTblStavke().setModel(modelTabele);

                stavke.clear();
                stavke.addAll(stavkeZaPrikaz);

                
                trenutniRedniBroj = maxRb + 1;

                ukupnaCena = 0;
                for (StavkaEvidencijeTreninga s : stavke) {
                    ukupnaCena += s.getCena();
                }
               def.getTxtUkupnaCena().setText(String.valueOf(modelTabele.getUkupnaCena()));

                def.getBtnDodaj().setVisible(false);
                break;
            
            case DETALJI:
                def.getBtnIzmeni().setVisible(false);
                EvidencijaTreninga e1 = (EvidencijaTreninga) kordinator.Kordinator.getInstance().vratiParam("evidencija");
                def.getBtnDodaj().setVisible(false);
                def.getBtnDodajStavku().setVisible(false);
                def.getCmbTrener().setSelectedItem(e1.getTrener());
                def.getCmbTrener().setEnabled(false);
                def.getCmbKlijent().setSelectedItem(e1.getKlijent());
                def.getCmbKlijent().setEnabled(false);
                def.getCmbTermin().setVisible(false);
                def.getTxtVremeDo().setVisible(false);
                def.getTxtVremeOd().setVisible(false);
                def.getCmbOcena().setVisible(false);
               def.getTxtUkupnaCena().setText(String.valueOf(modelTabele.getUkupnaCena()));
                try {
                    List<StavkaEvidencijeTreninga> stavke = Komunikacija.getInstance()
                            .ucitajStakve(e1.getIdEvidencijaTreninga());
                    modelTabele = new StavkaTableModel(stavke);
                    def.getTblStavke().setModel(modelTabele);
                     def.getTxtUkupnaCena().setText(String.valueOf(modelTabele.getUkupnaCena()));
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(def, "Greška pri učitavanju stavki!", "Greška", JOptionPane.ERROR_MESSAGE);
                }
                def.getBtnObrisiStavku().setVisible(false);
                def.getjLabel4().setVisible(false);
                def.getjLabel5().setVisible(false);
                def.getjLabel6().setVisible(false);
                def.getjLabel7().setVisible(false);
                break;
        }
    }
    
     
}
     



