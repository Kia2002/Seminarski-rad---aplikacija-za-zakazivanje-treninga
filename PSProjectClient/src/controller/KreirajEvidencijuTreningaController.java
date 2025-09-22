/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.EvidencijaTreninga;
import domain.Klijent;
import domain.NivoFizickeSpreme;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;

/**
 *
 * @author Aleksa
 */
public class KreirajEvidencijuTreningaController {
    
 private final KreirajEvidencijuTreningaForma def;
private int trenutniRedniBroj;
private StavkaTableModel modelTabele;
private List<StavkaEvidencijeTreninga> stavke;
private int ukupnaCena;
private EvidencijaTreninga ev;
    public KreirajEvidencijuTreningaController(KreirajEvidencijuTreningaForma def) {
        this.def = def;
        addActionListener();
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
                Long ocena =  Long.parseLong((String) def.getCmbOcena().getSelectedItem());
        Long rb = (long) trenutniRedniBroj;
        Long brojSati = ChronoUnit.HOURS.between(vremeOd, vremeDo);
        Long cena = brojSati * te.getCenaPoSatu();
                
                
                if (vremeOd.isAfter(vremeDo)) {
        JOptionPane.showMessageDialog(null, "Vreme od mora biti pre vremena do.", "Greška", JOptionPane.ERROR_MESSAGE);
        return; 
    }
       
        for (StavkaEvidencijeTreninga stavka : stavke) {
                if(te.getDatum().equals(stavka.getTermin().getDatum())){
                if ((vremeOd.isBefore(stavka.getVremeDo()) && vremeDo.isAfter(stavka.getVremeOd()))) {
                    JOptionPane.showMessageDialog(null, "Trening se preklapa sa već postojećim!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                }
            }
if (rb % 5 == 0) {
    cena = Math.round(cena * 0.8);
}
              StavkaEvidencijeTreninga stavka = new StavkaEvidencijeTreninga(rb, ocena, vremeOd, vremeDo, cena, te);
        stavke.add(stavka);
        modelTabele.dodajStavku(stavka);
         trenutniRedniBroj++;
         def.getTxtVremeOd().setText("");
        def.getTxtVremeDo().setText("");
        def.getCmbTermin().setSelectedIndex(0);
        def.getCmbOcena().setSelectedIndex(0);
        
        ukupnaCena += stavka.getCena();
       def.getTxtUkupnaCena().setText(String.valueOf(ukupnaCena));
       
                
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
        ev.setUkupnaCena((long)ukupnaCena);
        if (stavke.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Sistem ne može da kreira evidenciju treninga.", 
                "Greška", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        ev.setStavke(stavke);
         try {
                    Komunikacija.getInstance().kreirajEvidenciju(ev);
                    JOptionPane.showMessageDialog(def, "Sistem je kreirao evidenciju treninga.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    def.dispose();
                }  catch(Exception ex) {
                    JOptionPane.showMessageDialog(def, ex.getMessage(), "Neuspešno", JOptionPane.ERROR_MESSAGE);
                }
       
    } catch (Exception ex) {
        ex.printStackTrace();
         JOptionPane.showMessageDialog(null, "Sistem ne može da kreira evidenciju treninga", "Greška", JOptionPane.ERROR_MESSAGE);
    }
            }
        });
    }

    

    private void pripremiFormu(FormaMod mod) throws Exception {
        trenutniRedniBroj = 1;
        ukupnaCena = 0;
       modelTabele = new StavkaTableModel();
       stavke = new ArrayList<>();
    def.getTblStavke().setModel(modelTabele);
    def.getTxtUkupnaCena().setText(String.valueOf(ukupnaCena));
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
              for (Trener t : treneri) {
            
            if(t.equals(Kordinator.getInstance().getUlogovani())){
            def.getCmbTrener().setSelectedItem(t);
            def.getCmbTrener().setEnabled(false);
            }
        }
                break;
                
                
            
        case DETALJI:
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
            def.getTxtUkupnaCena().setText(String.valueOf(e1.getUkupnaCena()));
           try {
    List<StavkaEvidencijeTreninga> stavke = Komunikacija.getInstance()
            .ucitajStakve(e1.getIdEvidencijaTreninga());
    modelTabele = new StavkaTableModel(stavke);
    def.getTblStavke().setModel(modelTabele);
} catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(def, "Greška pri učitavanju stavki!", "Greška", JOptionPane.ERROR_MESSAGE);
}
            def.getjLabel4().setVisible(false);
             def.getjLabel5().setVisible(false);
              def.getjLabel6().setVisible(false);
               def.getjLabel7().setVisible(false);
               break;
        }
    }

}
