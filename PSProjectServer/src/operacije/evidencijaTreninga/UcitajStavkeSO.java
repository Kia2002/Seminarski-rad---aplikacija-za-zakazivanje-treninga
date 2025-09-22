/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.evidencijaTreninga;

import domain.EvidencijaTreninga;
import domain.StavkaEvidencijeTreninga;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Aleksa
 */
public class UcitajStavkeSO extends ApstraktnaGenerickaOperacija{
    private List<StavkaEvidencijeTreninga> stavke;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        String uslov = " JOIN termin ON stavkaevidencijetreninga.idTermin = termin.idTermin WHERE idEvidencijaTreninga=" + (Long) param;
        stavke = broker.getAll(new StavkaEvidencijeTreninga(), uslov);
    }

    public List<StavkaEvidencijeTreninga> getStavke() {
        return stavke;
    }

}