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
public class NivoFizickeSpreme implements ApstraktniDomenskiObjekat{
    private Long idNivoFizickeSpreme;
    private String nivo;
    private String opis;

    public NivoFizickeSpreme() {
    }

    public NivoFizickeSpreme(String nivo, String opis) {
        this.nivo = nivo;
        this.opis = opis;
    }

    public NivoFizickeSpreme(Long idNivoFizickeSpreme, String nivo, String opis) {
        this.idNivoFizickeSpreme = idNivoFizickeSpreme;
        this.nivo = nivo;
        this.opis = opis;
    }

    public Long getIdNivoFizickeSpreme() {
        return idNivoFizickeSpreme;
    }

    public String getNivo() {
        return nivo;
    }

    public String getOpis() {
        return opis;
    }

    public void setIdNivoFizickeSpreme(Long idNivoFizickeSpreme) {
        this.idNivoFizickeSpreme = idNivoFizickeSpreme;
    }

    public void setNivo(String nivo) {
        this.nivo = nivo;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return nivo;
    }

   

   @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    NivoFizickeSpreme other = (NivoFizickeSpreme) obj;
    return Objects.equals(this.idNivoFizickeSpreme, other.idNivoFizickeSpreme);
}
    @Override
    public int hashCode() {
    return Objects.hash(idNivoFizickeSpreme);
    }


    @Override
    public String vratiNazivTabele() {
        return "nivofizickespreme";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> nivoi = new LinkedList<>();
        
        while (rs.next()) {
                
                Long id = rs.getLong("nivofizickespreme.idNivoFizickeSpreme");
               String naziv = rs.getString("nivofizickespreme.nivo");
               String opis = rs.getString("nivofizickespreme.opis");
               NivoFizickeSpreme nivo = new NivoFizickeSpreme(id,naziv,opis);
                nivoi.add(nivo);
            }
        
        return nivoi;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nivo, opis";
    }

    @Override
public String vratiVrednostiZaUbacivanje() {
    return "'" + nivo + "', '" + opis + "'";
}


    @Override
    public String vratiPrimarniKljuc() {
        return "nivofizickespreme.idNivoFizickeSpreme=" + idNivoFizickeSpreme;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public String vratiVrednostiZaIzmenu() {
    return "nivo='" + nivo + "', opis='" + opis + "'";
}

    
    
}
