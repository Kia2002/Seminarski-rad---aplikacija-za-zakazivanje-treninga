/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domain.EvidencijaTreninga;
import domain.Klijent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aleksa
 */
public class ModelTabeleEvidencijeTreninga extends AbstractTableModel {

    List<EvidencijaTreninga> lista = new ArrayList<>();
    String[] kolone = {"Id", "Trener", "Klijent", "Ukupna cena"};

    public ModelTabeleEvidencijeTreninga(List<EvidencijaTreninga> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();

    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EvidencijaTreninga e = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return e.getIdEvidencijaTreninga();
            
            case 1:
                return e.getTrener().getIme()+" "+e.getTrener().getPrezime();

            case 2:
                return e.getKlijent().getIme()+" "+e.getKlijent().getPrezime();
            case 3:
                return e.getUkupnaCena();

            default:
                return "NA";
        }
    }

    public List<EvidencijaTreninga> getLista() {
        return lista;
    }


    

    public void pretrazi(String imeK, String prezimeK, String imeT, String prezimeT) {
        List<EvidencijaTreninga> filteredList = lista.stream()
            .filter(e -> (imeK == null || imeK.isEmpty() || e.getKlijent().getIme().toLowerCase().contains(imeK.toLowerCase())))
            .filter(e -> (prezimeK == null || prezimeK.isEmpty() || e.getKlijent().getPrezime().toLowerCase().contains(prezimeK.toLowerCase())))
            .filter(e -> (imeT == null || imeT.isEmpty() || e.getTrener().getIme().toLowerCase().contains(imeT.toLowerCase())))
            .filter(e -> (prezimeT == null || prezimeT.isEmpty() || e.getTrener().getPrezime().toLowerCase().contains(prezimeT.toLowerCase())))
            .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();
    }

    public void pretrazi(String imeK, String prezimeK) {
        List<EvidencijaTreninga> filteredList = lista.stream()
            .filter(e -> (imeK == null || imeK.isEmpty() || e.getKlijent().getIme().toLowerCase().contains(imeK.toLowerCase())))
            .filter(e -> (prezimeK == null || prezimeK.isEmpty() || e.getKlijent().getPrezime().toLowerCase().contains(prezimeK.toLowerCase())))
            
            .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();
    }
    
}