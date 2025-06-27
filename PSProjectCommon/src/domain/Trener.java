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
public class Trener implements AbstractDomainObject{
    private Long idTrener;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;
    private String email;

    public Long getIdTrener() {
        return idTrener;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public String getEmail() {
        return email;
    }

    public void setIdTrener(Long idTrener) {
        this.idTrener = idTrener;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Trener() {
    }

    public Trener(String korisnickoIme, String sifra) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public Trener(Long idTrener, String ime, String prezime, String korisnickoIme, String sifra, String email) {
        this.idTrener = idTrener;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.email = email;
    }

    public Trener(String ime, String prezime, String korisnickoIme, String sifra, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.email = email;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Trener other = (Trener) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String vratiNazivTabele() {
        return "trener";
    }

    @Override
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws Exception {
        List<AbstractDomainObject> treneri = new LinkedList<>();
        while (rs.next()) {
                
                Long id = rs.getLong("trener.idTrener");
               String ime = rs.getString("trener.ime");
               String prezime = rs.getString("trener.prezime");
               String username = rs.getString("trener.korisnickoIme");
               String pass = rs.getString("trener.sifra");
               String email = rs.getString("trener.email");
               Trener trener = new Trener(id,ime,prezime,username,pass,email);
                treneri.add(trener);
            }
        return treneri;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, korisnickoIme, sifra, email";
    }

    @Override
public String vratiVrednostiZaUbacivanje() {
    return "'" + ime + "', '" + prezime + "', '" + korisnickoIme + "', '" + sifra + "', '" + email + "'";
}


    @Override
    public String vratiPrimarniKljuc() {
        return "trener.idTrener=" + idTrener;
    }

    @Override
    public AbstractDomainObject vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public String vratiVrednostiZaIzmenu() {
    return "ime='" + ime + "', prezime='" + prezime + "', korisnickoIme='" + korisnickoIme + "', sifra='" + sifra + "', email='" + email + "'";
}

    
    
}
