package domain;


import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Aleksa
 */
public class Termin implements ApstraktniDomenskiObjekat{
    private Long idTermin;
    private LocalDate datum;
    private Long cenaPoSatu;

    public Long getIdTermin() {
        return idTermin;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Long getCenaPoSatu() {
        return cenaPoSatu;
    }

    public void setIdTermin(Long idTermin) {
        this.idTermin = idTermin;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setCenaPoSatu(Long cenaPoSatu) {
        this.cenaPoSatu = cenaPoSatu;
    }

    public Termin() {
    }

    public Termin(Long idTermin, LocalDate datum, Long cenaPoSatu) {
        this.idTermin = idTermin;
        this.datum = datum;
        this.cenaPoSatu = cenaPoSatu;
    }

    public Termin(LocalDate datum, Long cenaPoSatu) {
        this.datum = datum;
        this.cenaPoSatu = cenaPoSatu;
    }

    @Override
    public String toString() {
        return "Datum: " + datum + " Cena po satu: " + cenaPoSatu;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Termin other = (Termin) obj;
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        return Objects.equals(this.cenaPoSatu, other.cenaPoSatu);
    }

    @Override
    public String vratiNazivTabele() {
        return "termin";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
    List<ApstraktniDomenskiObjekat> termini = new LinkedList<>();
    while (rs.next()) {
                
                Long id = rs.getLong("termin.idTermin");
                LocalDate datum = rs.getDate("termin.datum").toLocalDate();
               Long cenaPoSatu = rs.getLong("termin.cenaPoSatu");
              
               Termin termin = new Termin(id,datum,cenaPoSatu);
                termini.add(termin);
            }
    return termini;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum, cenaPoSatu";
    }

    @Override
public String vratiVrednostiZaUbacivanje() {
    return "'" + datum + "', " + cenaPoSatu;
}


    @Override
    public String vratiPrimarniKljuc() {
        return "termin.idTermin=" + idTermin;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public String vratiVrednostiZaIzmenu() {
    return "datum='" + datum + "', cenaPoSatu=" + cenaPoSatu;
}

    
}
