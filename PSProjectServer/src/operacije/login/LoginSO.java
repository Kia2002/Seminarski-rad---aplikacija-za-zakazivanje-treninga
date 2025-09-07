/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.login;

import domain.Klijent;
import domain.Trener;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Aleksa
 */
public class LoginSO extends ApstraktnaGenerickaOperacija{
Trener trener;
    @Override
protected void preduslovi(Object param) throws Exception {
   if(param==null || !(param instanceof Trener)){
   throw new Exception("Sistem ne može da pronadje trenera.");
   }
}


    /*@Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
List<Trener> sviTreneri = broker.getAll((Trener)param, null);
System.out.println("Dohvaceno trenera: " + sviTreneri.size());

       // List<Trener> sviTreneri = repository.getAll((Trener) param, null);
        System.out.println("Klasa LoginSO" + sviTreneri);
        for (Trener t : sviTreneri) {
    Trener unos = (Trener) param;
    if (t.getKorisnickoIme().equals(unos.getKorisnickoIme()) &&
        t.getSifra().equals(unos.getSifra())) {

        ulogovaniTrener = t;
        return;
    }
}

        ulogovaniTrener = null;
    }
    
    
    */
@Override
protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
    
    trener = (Trener) param;

    String uslov = " WHERE korisnickoIme = '" + trener.getKorisnickoIme() +
                   "' AND sifra = '" + trener.getSifra() + "'";

    trener = (Trener) broker.get(trener, uslov);

    System.out.println("LOGIN Operacija: " + trener);
    
//    if (instruktor == null) {
//        throw new LoginException("Korisničko ime i šifra nisu ispravni");
//
//    }
    
}
    public Trener getUlogovaniTrener() {
        return trener;
    }
    
}
