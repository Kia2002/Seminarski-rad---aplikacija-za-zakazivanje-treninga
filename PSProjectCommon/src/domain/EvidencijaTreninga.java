package domain;

import java.sql.ResultSet;
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
public class EvidencijaTreninga implements ApstraktniDomenskiObjekat{
    private Long idEvidencijaTreninga;
    private Long ukupnaCena;
    private Trener trener;
    private Klijent klijent;
    private List<StavkaEvidencijeTreninga> stavke;

    public EvidencijaTreninga(Long idEvidencijaTreninga, Long ukupnaCena, Trener trener, Klijent klijent) {
        this.idEvidencijaTreninga = idEvidencijaTreninga;
        this.ukupnaCena = ukupnaCena;
        this.trener = trener;
        this.klijent = klijent;
    }

    public void setStavke(List<StavkaEvidencijeTreninga> stavke) {
        this.stavke = stavke;
    }

    public List<StavkaEvidencijeTreninga> getStavke() {
        return stavke;
    }

    public EvidencijaTreninga(Long ukupnaCena, Trener trener, Klijent klijent, List<StavkaEvidencijeTreninga> stavke) {
        this.ukupnaCena = ukupnaCena;
        this.trener = trener;
        this.klijent = klijent;
        this.stavke = stavke;
    }

    public EvidencijaTreninga(Long idEvidencijaTreninga, Long ukupnaCena, Trener trener, Klijent klijent, List<StavkaEvidencijeTreninga> stavke) {
        this.idEvidencijaTreninga = idEvidencijaTreninga;
        this.ukupnaCena = ukupnaCena;
        this.trener = trener;
        this.klijent = klijent;
        this.stavke = stavke;
    }

    public EvidencijaTreninga() {
    }

    public EvidencijaTreninga(Long ukupnaCena, Trener trener, Klijent klijent) {
        this.ukupnaCena = ukupnaCena;
        this.trener = trener;
        this.klijent = klijent;
    }

    public Long getIdEvidencijaTreninga() {
        return idEvidencijaTreninga;
    }

    public Long getUkupnaCena() {
        return ukupnaCena;
    }

    public Trener getTrener() {
        return trener;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setIdEvidencijaTreninga(Long idEvidencijaTreninga) {
        this.idEvidencijaTreninga = idEvidencijaTreninga;
    }

    public void setUkupnaCena(Long ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    @Override
    public String toString() {
        return "EvidencijaTreninga{" + "idEvidencijaTreninga=" + idEvidencijaTreninga + ", ukupnaCena=" + ukupnaCena + ", trener=" + trener + ", klijent=" + klijent + ", stavke=" + stavke + '}';
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
        final EvidencijaTreninga other = (EvidencijaTreninga) obj;
        if (!Objects.equals(this.trener, other.trener)) {
            return false;
        }
        if (!Objects.equals(this.klijent, other.klijent)) {
            return false;
        }
        return Objects.equals(this.stavke, other.stavke);
    }

    @Override
    public String vratiNazivTabele() {
        return "evidencijatreninga";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ukupnaCena, idKlijent, idTrener";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return ukupnaCena + ", " + klijent.getIdKlijent() + ", " + trener.getIdTrener();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "evidencijatreninga.idEvidencijaTreninga=" + idEvidencijaTreninga;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public String vratiVrednostiZaIzmenu() {
    return "ukupnaCena=" + ukupnaCena + ", idKlijent=" + klijent.getIdKlijent() + ", idTrener=" + trener.getIdTrener();
}


    

    
    
    
}
