package domain;


import java.sql.ResultSet;
import java.time.LocalDate;
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
public class TS implements ApstraktniDomenskiObjekat{
    private Trener trener;
    private Sertifikat sertifikat;
    private LocalDate datum;

    public TS() {
    }

    public TS(Trener trener, Sertifikat sertifikat, LocalDate datum) {
        this.trener = trener;
        this.sertifikat = sertifikat;
        this.datum = datum;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public void setSertifikat(Sertifikat sertifikat) {
        this.sertifikat = sertifikat;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Trener getTrener() {
        return trener;
    }

    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    public LocalDate getDatum() {
        return datum;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final TS other = (TS) obj;
        if (!Objects.equals(this.trener, other.trener)) {
            return false;
        }
        if (!Objects.equals(this.sertifikat, other.sertifikat)) {
            return false;
        }
        return Objects.equals(this.datum, other.datum);
    }

    @Override
    public String vratiNazivTabele() {
        return "ts";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idTrener, idSertifikat, datum";
    }

    @Override
public String vratiVrednostiZaUbacivanje() {
    return trener.getIdTrener() + ", " + sertifikat.getIdSertifikat() + ", '" + datum + "'";
}


    @Override
    public String vratiPrimarniKljuc() {
        return "idTrener=" + trener.getIdTrener() + " AND idSertifikat=" +  sertifikat.getIdSertifikat();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public String vratiVrednostiZaIzmenu() {
    return "idTrener=" + trener.getIdTrener() + ", idSertifikat=" + sertifikat.getIdSertifikat() + ", datum='" + datum + "'";
}

    
    
}
