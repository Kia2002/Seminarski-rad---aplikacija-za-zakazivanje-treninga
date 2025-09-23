/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.EvidencijaTreninga;
import domain.StavkaEvidencijeTreninga;
import domain.Trener;
import forme.IzmeniEvidencijuTreningaForma;
import forme.PrikazEvidencijaTreningaForma;
import forme.model.ModelTabeleEvidencijeTreninga;
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
public class IzmeniEvidencijuTreningaController {
    private final IzmeniEvidencijuTreningaForma ief;
    List<StavkaEvidencijeTreninga> originalneStavke;

    public List<StavkaEvidencijeTreninga> getOriginalneStavke() {
        return originalneStavke;
    }

    public void setOriginalneStavke(List<StavkaEvidencijeTreninga> originalneStavke) {
        this.originalneStavke = originalneStavke;
    }
    
    public IzmeniEvidencijuTreningaController(IzmeniEvidencijuTreningaForma ief) {
        this.ief = ief;
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
    }
      
      
    private void addActionListener(){
        ief.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imeK = ief.getTxtIme().getText().trim();
                String prezimeK = ief.getTxtPrezime().getText().trim();
                
                
                
                ModelTabeleEvidencijeTreninga mte = (ModelTabeleEvidencijeTreninga) ief.getTblEvidencije().getModel();
                mte.pretrazi(imeK,prezimeK);
                
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
                ief.getTxtIme().setText("");
                ief.getTxtPrezime().setText("");
                
                
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

