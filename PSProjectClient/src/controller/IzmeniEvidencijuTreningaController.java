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
import forme.IzmeniEvidencijuTreningaForma;
import forme.PrikazEvidencijaTreningaForma;
import forme.model.ModelTabeleEvidencijeTreninga;
import forme.model.StavkaTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;

/**
 *
 * @author Aleksa
 */
public class IzmeniEvidencijuTreningaController {
    private final IzmeniEvidencijuTreningaForma ief;
    List<StavkaEvidencijeTreninga> originalneStavke;

    public List<StavkaEvidencijeTreninga> getOriginalneStavke() {
        return originalneStavke;
    }
    
    private void applyTheme() {
        
        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 14);

       
        JComboBox<?>[] combos = {ief.getCmbKlijent()};
    for (JComboBox<?> cb : combos) {
        cb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cb.setBackground(Color.WHITE);
    }
    
        ief.getBtnPretrazi().setFont(fontButton);
        ief.getBtnResetuj().setFont(fontButton);
        ief.getBtnIzmeni().setFont(fontButton);

        
        Color primary = new Color(70, 130, 180);
        Color danger = new Color(220, 53, 69);

        ief.getBtnPretrazi().setBackground(primary);
        ief.getBtnPretrazi().setForeground(Color.WHITE);
        ief.getBtnPretrazi().setFocusPainted(false);
        ief.getBtnPretrazi().setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        ief.getBtnResetuj().setBackground(new Color(100, 149, 237));
        ief.getBtnResetuj().setForeground(Color.WHITE);
        ief.getBtnResetuj().setFocusPainted(false);
        ief.getBtnResetuj().setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        ief.getBtnIzmeni().setBackground(danger);
        ief.getBtnIzmeni().setForeground(Color.WHITE);
        ief.getBtnIzmeni().setFocusPainted(false);
        ief.getBtnIzmeni().setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        
      

       
        ief.getTblEvidencije().setFont(fontLabel);
        ief.getTblEvidencije().setBackground(Color.WHITE);
        ief.getTblStavke().setFont(fontLabel);
        ief.getTblStavke().setBackground(Color.WHITE);
    }
    
    public void setOriginalneStavke(List<StavkaEvidencijeTreninga> originalneStavke) {
        this.originalneStavke = originalneStavke;
    }
    
    public IzmeniEvidencijuTreningaController(IzmeniEvidencijuTreningaForma ief) {
        this.ief = ief;
        applyTheme();
        addActionListener();
        addMouseListener();
    }
     public void otvoriFormu() throws Exception {
        pripremiFormu();
        ief.setVisible(true);
    }
     public void osveziFormu() throws Exception {
        pripremiFormu();
    }
      public void pripremiFormu() throws Exception {
          Trener ulogovani = Kordinator.getInstance().getUlogovani();
        List<EvidencijaTreninga> evidencijeTreninga = komunikacija.Komunikacija.getInstance().ucitajEvidencijeTreningaZaposlenog(ulogovani);
          ModelTabeleEvidencijeTreninga mte = new ModelTabeleEvidencijeTreninga(evidencijeTreninga);
        ief.getTblEvidencije().setModel(mte);
        
        List<StavkaEvidencijeTreninga> stavke = new ArrayList<>();
          StavkaTableModel mts = new StavkaTableModel(stavke);
        ief.getTblStavke().setModel(mts);
        List<Trener> treneri = Komunikacija.getInstance().ucitajTrenere();
        
        
        
        
        
        List<Klijent> klijenti = Komunikacija.getInstance().ucitajKlijente();
        Klijent prazno = new Klijent();
        prazno.setIme("Odaberite klijenta");
        klijenti.add(0, prazno);
        ief.getCmbKlijent().removeAllItems();
        for (Klijent k : klijenti) {
            ief.getCmbKlijent().addItem(k);
        }
        
        
        
    }
      
      
    private void addActionListener(){
        ief.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Klijent k = (Klijent) ief.getCmbKlijent().getSelectedItem();
                
                
                
                ModelTabeleEvidencijeTreninga mte = (ModelTabeleEvidencijeTreninga) ief.getTblEvidencije().getModel();
                
                mte.pretrazi(k);
                if (mte.getLista().isEmpty()) {
                    JOptionPane.showMessageDialog(ief, "Sistem ne može da nađe evidencije treninga po zadatim kriterijumima.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ief, "Sistem je našao evidencije treninga po zadatim kriterijumima.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        });
        ief.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pripremiFormu();
                } catch (Exception ex) {
                    Logger.getLogger(PrikazKlijenataController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ief.getCmbKlijent().setSelectedIndex(0);
               
                
                
            }
        });
        
        ief.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 int red = ief.getTblEvidencije().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ief, "Sistem ne može da nađe evidenciju treninga.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ief, "Sistem je našao evidenciju treninga.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleEvidencijeTreninga mte = (ModelTabeleEvidencijeTreninga) ief.getTblEvidencije().getModel();

                    EvidencijaTreninga evidencijaTreninga = mte.getLista().get(red);
                   Kordinator.getInstance().dodajParam("evidencija", evidencijaTreninga);
                   ief.dispose();
                     try {
                         Kordinator.getInstance().otvoriIzmeniEvidencijeZaposlenogFormu();
                     } catch (Exception ex) {
                         Logger.getLogger(IzmeniEvidencijuTreningaController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }

            }

        });
    }

    private void addMouseListener() {
        ief.getTblEvidencije().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = ief.getTblEvidencije().getSelectedRow();
                if(red!=-1){
                ModelTabeleEvidencijeTreninga mte = (ModelTabeleEvidencijeTreninga) ief.getTblEvidencije().getModel();
                EvidencijaTreninga evidencija = mte.getLista().get(red);
                List<StavkaEvidencijeTreninga> stavke;
                    try {
                        stavke = Komunikacija.getInstance().ucitajStakve(evidencija.getIdEvidencijaTreninga());
                        StavkaTableModel stm = new StavkaTableModel(stavke);
                ief.getTblStavke().setModel(stm);
                    } catch (Exception ex) {
                        Logger.getLogger(PrikazEvidencijaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                
                }

            }
            
            
            
        });
    }

    }

