package domain;


import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
public class StavkaEvidencijeTreninga implements ApstraktniDomenskiObjekat{
    EvidencijaTreninga evidencijaTreninga;
    private Long rb;
    private Long ocena;
    private LocalTime vremeOd;
    private LocalTime vremeDo;
    private Long cena;
    private Termin termin;

    public StavkaEvidencijeTreninga() {
    }

    public EvidencijaTreninga getEvidencijaTreninga() {
        return evidencijaTreninga;
    }

    public void setEvidencijaTreninga(EvidencijaTreninga evidencijaTreninga) {
        this.evidencijaTreninga = evidencijaTreninga;
    }

    public StavkaEvidencijeTreninga(Long rb, Long ocena, LocalTime vremeOd, LocalTime vremeDo, Long cena, Termin termin) {
        this.rb = rb;
        this.ocena = ocena;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
        this.cena = cena;
        this.termin = termin;
    }

    public Long getRb() {
        return rb;
    }

   
    public Long getOcena() {
        return ocena;
    }

    public LocalTime getVremeOd() {
        return vremeOd;
    }

    public LocalTime getVremeDo() {
        return vremeDo;
    }

    public Long getCena() {
        return cena;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setRb(Long rb) {
        this.rb = rb;
    }

    

    public void setOcena(Long ocena) {
        this.ocena = ocena;
    }

    public void setVremeOd(LocalTime vremeOd) {
        this.vremeOd = vremeOd;
    }

    public void setVremeDo(LocalTime vremeDo) {
        this.vremeDo = vremeDo;
    }

    public void setCena(Long cena) {
        this.cena = cena;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    @Override
    public String toString() {
        return "StavkaEvidencijeTreninga{" + "rb=" + rb + ", ocena=" + ocena + ", vremeOd=" + vremeOd + ", vremeDo=" + vremeDo + ", cena=" + cena + ", termin=" + termin + '}';
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
        final StavkaEvidencijeTreninga other = (StavkaEvidencijeTreninga) obj;
        if (!Objects.equals(this.rb, other.rb)) {
            return false;
        }
        if (!Objects.equals(this.vremeOd, other.vremeOd)) {
            return false;
        }
        if (!Objects.equals(this.vremeDo, other.vremeDo)) {
            return false;
        }
        return Objects.equals(this.termin, other.termin);
    }

   

    @Override
    public String vratiNazivTabele() {
        return "stavkaevidencijetreninga";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            LocalDate datum = rs.getDate("datum").toLocalDate();
Termin t = new Termin(rs.getLong("idTermin"), datum, rs.getLong("cenaPoSatu"));
            StavkaEvidencijeTreninga stavkaEvidencijeTreninga = new StavkaEvidencijeTreninga(rs.getLong("rb"),rs.getLong("ocena"),rs.getTime("vremeOd").toLocalTime(),rs.getTime("vremeDo").toLocalTime(),rs.getLong("cena"),t);
            lista.add(stavkaEvidencijeTreninga);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idEvidencijaTreninga, rb, ocena, vremeOd, vremeDo, cena, idTermin";
    }

   @Override
public String vratiVrednostiZaUbacivanje() {
    return evidencijaTreninga.getIdEvidencijaTreninga() + ", " + rb + ", " + ocena + ", '" +
           vremeOd + "', '" + vremeDo + "', " + cena + ", " + termin.getIdTermin();
}

@Override
public String vratiPrimarniKljuc() {
    return "stavkaevidencijetreninga.rb=" + rb + 
           " AND stavkaevidencijetreninga.idEvidencijaTreninga=" + evidencijaTreninga.getIdEvidencijaTreninga();
}

@Override
public String vratiVrednostiZaIzmenu() {
    return "ocena=" + ocena + ", vremeOd='" + vremeOd + "', vremeDo='" + vremeDo + 
           "', cena=" + cena + ", idTermin=" + termin.getIdTermin();
}


    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
}
