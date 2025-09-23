/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

/**
 *
 * @author Aleksa
 */
import domain.StatusStavke;
import domain.StavkaEvidencijeTreninga;
import java.time.temporal.ChronoUnit;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StavkaTableModel extends AbstractTableModel {
    private final String[] kolone = {"Redni Broj", "Vreme Od", "Vreme Do", "Termin", "Ocena", "Cena"};
    private final List<StavkaEvidencijeTreninga> stavke;

    public StavkaTableModel(List<StavkaEvidencijeTreninga> stavke) {
        //this.stavke = stavke;
        this.stavke = (stavke != null) ? stavke : new ArrayList<>();
        fireTableDataChanged();
    }
  public List<StavkaEvidencijeTreninga> getStavke() {
        return stavke;
    }
    public StavkaTableModel() {
        this.stavke = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return getAktivneStavke().size();
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
        StavkaEvidencijeTreninga stavka = getAktivneStavke().get(rowIndex);
        switch (columnIndex) {
            case 0: return rowIndex+1;
            case 1: return stavka.getVremeOd();
            case 2: return stavka.getVremeDo();
            case 3: return stavka.getTermin().getDatum();
            case 4: return stavka.getOcena();
            case 5: return stavka.getCena();
            default: return "N/A";
        }
    }

    public void dodajStavku(StavkaEvidencijeTreninga stavka) {
        stavke.add(stavka);
        fireTableDataChanged();  
    }
public List<StavkaEvidencijeTreninga> getAktivneStavke() {
        List<StavkaEvidencijeTreninga> aktivne = new ArrayList<>();
        for (StavkaEvidencijeTreninga se : stavke) {
            if (se.getStatus()!= StatusStavke.OBRISANA) {
                aktivne.add(se);
            }
        }
        return aktivne;
    }
      public void obrisiStavku(StavkaEvidencijeTreninga se) {
        
        se.setStatus(StatusStavke.OBRISANA);
        fireTableDataChanged();
    }
public List<StavkaEvidencijeTreninga> getSveStavke() {
        return new ArrayList<>(stavke);
    }
    public void ocistiTabelu() {
        stavke.clear();
        fireTableDataChanged();
    }

    public long getUkupnaCena() {
    long ukupno = 0;
    for (StavkaEvidencijeTreninga s : getAktivneStavke()) {
        ukupno += s.getCena();
    }

   
    if (getAktivneStavke().size() > 5) {
        ukupno = (long) (ukupno * 0.9);
    }

    return ukupno;
}
}