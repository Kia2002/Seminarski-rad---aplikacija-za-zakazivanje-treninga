/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.trener;

import domain.NivoFizickeSpreme;
import domain.Trener;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Aleksa
 */
public class UcitajTrenereSO extends ApstraktnaGenerickaOperacija {
      List<Trener> treneri;

    public List<Trener> getTreneri() {
        return treneri;
    }
  
  
  
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        treneri = broker.getAll(new Trener(),"");
    }
    
}