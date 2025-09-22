/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termin;

import domain.Termin;
import domain.Trener;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Aleksa
 */
public class UcitajTermineSO extends ApstraktnaGenerickaOperacija {
      List<Termin> termini;

    public List<Termin> getTermini() {
        return termini;
    }
  
  
  
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        termini = broker.getAll(new Termin(),"");
    }
    
}
