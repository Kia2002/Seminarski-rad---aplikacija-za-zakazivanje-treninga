/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

/**
 *
 * @author Aleksa
 */
import domain.StavkaEvidencijeTreninga;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StavkaTableModel extends AbstractTableModel {
    private final String[] kolone = {"Redni Broj", "Vreme Od", "Vreme Do", "Termin", "Ocena", "Cena"};
    private final List<StavkaEvidencijeTreninga> stavke;

    public StavkaTableModel(List<StavkaEvidencijeTreninga> stavke) {
        this.stavke = stavke;
        fireTableDataChanged();
    }

    public StavkaTableModel() {
        this.stavke = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return stavke.size();
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
        StavkaEvidencijeTreninga stavka = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0: return stavka.getRb();
            case 1: return stavka.getVremeOd();
            case 2: return stavka.getVremeDo();
            case 3: return stavka.getTermin().getDatum();
            case 4: return stavka.getOcena();
            case 5: return stavka.getCena();
            default: return null;
        }
    }

    public void dodajStavku(StavkaEvidencijeTreninga stavka) {
        stavke.add(stavka);
        fireTableDataChanged();  
    }

    public void obrisiStavku(int index) {
        if (index >= 0 && index < stavke.size()) {
            stavke.remove(index);
            fireTableDataChanged();
        }
    }

    public void ocistiTabelu() {
        stavke.clear();
        fireTableDataChanged();
    }

    public List<StavkaEvidencijeTreninga> getStavke() {
        return stavke;
    }
}
