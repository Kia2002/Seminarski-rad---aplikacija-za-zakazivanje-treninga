/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import domain.Trener;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Aleksa
 */
public class LoginSO extends AbstractSO{
Trener ulogovaniTrener;
    @Override
protected void precondition(Object param) throws Exception {
    // Nema preduslova za login
}


    @Override
    protected void executeOperation(Object param, String key) throws Exception {
List<Trener> sviTreneri = repository.getAll((Trener)param, null);
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

    public Trener getUlogovaniTrener() {
        return ulogovaniTrener;
    }
    
}
