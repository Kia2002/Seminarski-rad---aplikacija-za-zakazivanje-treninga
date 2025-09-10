/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domain.Klijent;
import domain.NivoFizickeSpreme;
import domain.Termin;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aleksa
 */
public class KlijentTableModel extends AbstractTableModel{
private List<Klijent> klijenti;
    String[] columns = {"Id","Ime","Prezime","Email", "Nivo fizicke spreme"};

    public List<Klijent> getKlijenti() {
        return klijenti;
    }

    public void setKlijenti(List<Klijent> klijenti) {
        this.klijenti = klijenti;
    }

    public KlijentTableModel(List<Klijent> klijenti) {
        this.klijenti = klijenti;
    }
        
    @Override
    public int getRowCount() {
if (klijenti == null) {
            return 0;
        }
        return klijenti.size();    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent klijent = klijenti.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return klijent.getIdKlijent();
                case 1:
                return klijent.getIme();
                case 2:
                    return klijent.getPrezime();
                case 3: return klijent.getEmail();
                case 4: return klijent.getNivoFizickeSpreme();
                
            default:
                throw new AssertionError();
        }
    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    public Klijent getKlijent(int row) {
        return klijenti.get(row);
    }

    public void pretrazi(String ime, String prezime, String email, NivoFizickeSpreme nivo) {
        List<Klijent> filteredList = klijenti.stream()
            .filter(k -> (ime == null || ime.isEmpty() || k.getIme().toLowerCase().contains(ime.toLowerCase())))
            .filter(k -> (prezime == null || prezime.isEmpty() || k.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
            .filter(k -> (email == null || email.isEmpty() || k.getEmail().toLowerCase().contains(email.toLowerCase())))
            .filter(k -> (nivo == null || nivo.getNivo().equals("Odaberite nivo fizičke spreme") || k.getNivoFizickeSpreme().equals(nivo)))
            .collect(Collectors.toList());
        this.klijenti = filteredList;
        fireTableDataChanged();
    }
}
