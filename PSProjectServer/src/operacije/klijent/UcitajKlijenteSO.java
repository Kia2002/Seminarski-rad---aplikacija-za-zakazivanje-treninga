/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijent;

import domain.Klijent;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Aleksa
 */
public class UcitajKlijenteSO extends ApstraktnaGenerickaOperacija{
    private List<Klijent> klijenti;

    public List<Klijent> getKlijenti() {
        return klijenti;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        String uslov = " JOIN nivofizickespreme ON klijent.idnivofizickespreme=nivofizickespreme.idnivofizickespreme";
            klijenti = broker.getAll(new Klijent(), uslov);
    }
    
}
