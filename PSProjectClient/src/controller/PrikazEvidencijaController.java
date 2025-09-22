/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.EvidencijaTreninga;
import domain.NivoFizickeSpreme;
import domain.StavkaEvidencijeTreninga;
import forme.PrikazEvidencijaTreningaForma;
import forme.PrikazKlijenataForma;
import forme.model.ModelTabeleEvidencijeTreninga;
import forme.model.ModelTabeleKlijent;
import forme.model.StavkaTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;

/**
 *
 * @author Aleksa
 */
public class PrikazEvidencijaController {
    private final PrikazEvidencijaTreningaForma pef;
    
    
    public PrikazEvidencijaController(PrikazEvidencijaTreningaForma pef) {
        this.pef = pef;
        addActionListener();
        addMouseListener();
    }
     public void otvoriFormu() throws Exception {
        pripremiFormu();
        pef.setVisible(true);
    }
     public void osveziFormu() throws Exception {
        pripremiFormu();
    }
      public void pripremiFormu() throws Exception {
        List<EvidencijaTreninga> evidencijeTreninga = komunikacija.Komunikacija.getInstance().ucitajEvidencijeTreninga();
          ModelTabeleEvidencijeTreninga mte = new ModelTabeleEvidencijeTreninga(evidencijeTreninga);
        pef.getTblEvidencije().setModel(mte);
        
        List<StavkaEvidencijeTreninga> stavke = new ArrayList<>();
          StavkaTableModel mts = new StavkaTableModel(stavke);
        pef.getTblStavke().setModel(mts);
    }
    private void addActionListener(){
        pef.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imeK = pef.getTxtIme().getText().trim();
                String prezimeK = pef.getTxtPrezime().getText().trim();
                String imeT = pef.getTxtImeTrener().getText().trim();
                String prezimeT = pef.getTxtPrezimeTrener().getText().trim();
                
                
                ModelTabeleEvidencijeTreninga mte = (ModelTabeleEvidencijeTreninga) pef.getTblEvidencije().getModel();
                mte.pretrazi(imeK,prezimeK,imeT , prezimeT);
                
                if (mte.getLista().isEmpty()) {
                    JOptionPane.showMessageDialog(pef, "Sistem ne može da nađe evidencije treninga po zadatim kriterijumima.", "Neuspešno", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pef, "Sistem je našao evidencije treninga po zadatim kriterijumima.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        });
        pef.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pripremiFormu();
                } catch (Exception ex) {
                    Logger.getLogger(PrikazKlijenataController.class.getName()).log(Level.SEVERE, null, ex);
                }
                pef.getTxtIme().setText("");
                pef.getTxtPrezime().setText("");
                pef.getTxtImeTrener().setText("");
                pef.getTxtPrezimeTrener().setText("");
                
            }
        });
        
        pef.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pef.getTblEvidencije().getSelectedRow();
                if(red==-1){
                JOptionPane.showMessageDialog(pef, "Sistem ne može da nađe evidenciju treninga.", "Greška", JOptionPane.ERROR_MESSAGE);
                }else{
                    
                        JOptionPane.showMessageDialog(pef, "Sistem je našao evidenciju treninga.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        ModelTabeleEvidencijeTreninga mte  = (ModelTabeleEvidencijeTreninga) pef.getTblEvidencije().getModel();
                        EvidencijaTreninga evidencija = mte.getLista().get(red);
                        Kordinator.getInstance().dodajParam("evidencija", evidencija);
                    try {
                        Kordinator.getInstance().otvoriDetaljiEvidencijeFormu();
                    } catch (Exception ex) {
                        Logger.getLogger(PrikazKlijenataController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            }
        });
    }

    private void addMouseListener() {
        pef.getTblEvidencije().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = pef.getTblEvidencije().getSelectedRow();
                if(red!=-1){
                ModelTabeleEvidencijeTreninga mte = (ModelTabeleEvidencijeTreninga) pef.getTblEvidencije().getModel();
                EvidencijaTreninga evidencija = mte.getLista().get(red);
                List<StavkaEvidencijeTreninga> stavke;
                    try {
                        stavke = Komunikacija.getInstance().ucitajStakve(evidencija.getIdEvidencijaTreninga());
                        StavkaTableModel stm = new StavkaTableModel(stavke);
                pef.getTblStavke().setModel(stm);
                    } catch (Exception ex) {
                        Logger.getLogger(PrikazEvidencijaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                
                }

            }
            
            
            
        });
    }
    }

