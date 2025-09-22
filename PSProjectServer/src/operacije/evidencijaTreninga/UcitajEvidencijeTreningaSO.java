/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.evidencijaTreninga;

import domain.EvidencijaTreninga;
import domain.Klijent;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Aleksa
 */
public class UcitajEvidencijeTreningaSO extends ApstraktnaGenerickaOperacija{
    private List<EvidencijaTreninga> evidencije;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN klijent ON evidencijatreninga.idKlijent = klijent.idKlijent JOIN trener ON evidencijatreninga.idTrener = trener.idTrener JOIN nivofizickespreme ON klijent.idNivofizickespreme = nivofizickespreme.idNivofizickespreme";
        evidencije = broker.getAll(new EvidencijaTreninga(), uslov);
    }

    public List<EvidencijaTreninga> getEvidencije() {
        return evidencije;
    }

}
