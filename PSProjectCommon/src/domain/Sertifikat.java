package domain;

import java.sql.ResultSet;
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
public class Sertifikat implements ApstraktniDomenskiObjekat{
    private Long idSertifikat;
    private String naziv;
    private String opis;

    public Long getIdSertifikat() {
        return idSertifikat;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setIdSertifikat(Long idSertifikat) {
        this.idSertifikat = idSertifikat;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Sertifikat() {
    }

    public Sertifikat(String naziv, String opis) {
        this.naziv = naziv;
        this.opis = opis;
    }

    public Sertifikat(Long idSertifikat, String naziv, String opis) {
        this.idSertifikat = idSertifikat;
        this.naziv = naziv;
        this.opis = opis;
    }

    @Override
    public String toString() {
        return naziv;
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
        final Sertifikat other = (Sertifikat) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return Objects.equals(this.opis, other.opis);
    }

    @Override
    public String vratiNazivTabele() {
        return "sertifikat";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
                List<ApstraktniDomenskiObjekat> sertifikati = new LinkedList<>();

        while (rs.next()) {
                
                Long id = rs.getLong("sertifikat.idSertifikat");
               String naziv = rs.getString("sertifikat.naziv");
               String opis = rs.getString("sertifikat.opis");
               Sertifikat sertifikat = new Sertifikat(id, naziv, opis);
                sertifikati.add(sertifikat);
            }
        
        return sertifikati;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, opis";
    }

    @Override
public String vratiVrednostiZaUbacivanje() {
    return "'" + naziv + "', '" + opis + "'";
}


    @Override
    public String vratiPrimarniKljuc() {
        return "sertifikat.idSertifikat=" + idSertifikat;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public String vratiVrednostiZaIzmenu() {
    return "naziv='" + naziv + "', opis='" + opis + "'";
}

    
}
