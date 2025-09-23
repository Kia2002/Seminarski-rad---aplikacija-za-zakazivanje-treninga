/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.evidencijaTreninga;

import domain.EvidencijaTreninga;
import domain.Trener;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Aleksa
 */
public class UcitajEvidencijeTreningaZaposlenogSO extends ApstraktnaGenerickaOperacija {

   private List<EvidencijaTreninga> lista;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Trener)) {
            throw new Exception("Parametar mora biti objekat klase trener.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Trener trener = (Trener) param;

        String uslov = " JOIN klijent ON evidencijatreninga.idKlijent = klijent.idKlijent " +
                       "JOIN trener ON evidencijatreninga.idTrener= trener.idTrener " +
                       "JOIN nivofizickespreme ON klijent.idNivoFizickeSpreme = nivofizickespreme.idNivoFizickeSpreme " +
                       "WHERE evidencijatreninga.idTrener= " + trener.getIdTrener();

        lista = broker.getAll(new EvidencijaTreninga(), uslov);
    }

    public List<EvidencijaTreninga> getLista() {
        return lista;
    }
    
    
}

