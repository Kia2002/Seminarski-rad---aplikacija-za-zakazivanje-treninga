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
public class Klijent implements AbstractDomainObject{
    private Long idKlijent;
    private String ime;
    private String prezime; 
    private String email;
    private NivoFizickeSpreme nivoFizickeSpreme;

    public Klijent() {
    }

    public Klijent(String ime, String prezime, String email, NivoFizickeSpreme nivoFizickeSpreme) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.nivoFizickeSpreme = nivoFizickeSpreme;
    }

    public Klijent(Long idKlijent, String ime, String prezime, String email, NivoFizickeSpreme nivoFizickeSpreme) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.nivoFizickeSpreme = nivoFizickeSpreme;
    }

    public Long getIdKlijent() {
        return idKlijent;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getEmail() {
        return email;
    }

    public NivoFizickeSpreme getNivoFizickeSpreme() {
        return nivoFizickeSpreme;
    }

    public void setIdKlijent(Long idKlijent) {
        this.idKlijent = idKlijent;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNivoFizickeSpreme(NivoFizickeSpreme nivoFizickeSpreme) {
        this.nivoFizickeSpreme = nivoFizickeSpreme;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    public Klijent(Long idKlijent, String ime, String prezime, String email) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
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
        final Klijent other = (Klijent) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws Exception {
        /*List<AbstractDomainObject> klijenti = new LinkedList<>();
        
        
       while (rs.next()) {
                
                Long id = rs.getLong("idKlijent");
               String ime = rs.getString("ime");
               String prezime = rs.getString("prezime");
               String email = rs.getString("email");
               NivoFizickeSpreme nivo = new NivoFizickeSpreme();
        nivo.setIdNivoFizickeSpreme(rs.getLong("idNivoFizickeSpreme"));
        nivo.setNivo(rs.getString("n.nivo"));
               Klijent klijent = new Klijent(id,ime,prezime,email,nivo);
                klijenti.add(klijent);
            }
        
        return klijenti; */
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, email, idNivoFizickeSpreme";
    }

    @Override
public String vratiVrednostiZaUbacivanje() {
    return "'" + ime + "', '" + prezime + "', '" + email + "', " + nivoFizickeSpreme.getIdNivoFizickeSpreme();
}


    @Override
    public String vratiPrimarniKljuc() {
        return "klijent.idKlijent=" + idKlijent;
    }

    @Override
    public AbstractDomainObject vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public String vratiVrednostiZaIzmenu() {
    return "ime='" + ime + "', prezime='" + prezime + "', email='" + email + "', idNivoFizickeSpreme=" + nivoFizickeSpreme.getIdNivoFizickeSpreme();
}

    
    
}
