/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.nivofizickespreme;

import domain.NivoFizickeSpreme;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Aleksa
 */
public class UcitajNivoeSO extends ApstraktnaGenerickaOperacija {
      List<NivoFizickeSpreme> nivoi;

    public List<NivoFizickeSpreme> getNivoi() {
        return nivoi;
    }
  
  
  
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        nivoi = broker.getAll(new NivoFizickeSpreme(),"");
    }
    
}
