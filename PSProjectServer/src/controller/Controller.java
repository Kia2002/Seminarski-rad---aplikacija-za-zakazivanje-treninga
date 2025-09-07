/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Klijent;
import domain.NivoFizickeSpreme;
import domain.Trener;
import java.util.List;
import operacije.klijent.KreirajKlijentaSO;
import operacije.klijent.ObrisiKlijentaSO;
import operacije.klijent.UcitajKlijenteSO;
import operacije.login.LoginSO;
import operacije.nivofizickespreme.UcitajNivoeSO;

/**
 *
 * @author Aleksa
 */
public class Controller {
    private static Controller instance;
    
    private Controller(){
    }
    
    public static Controller getInstance(){
    if(instance==null){
    instance = new Controller();
    }
    return instance;
    }

    public Trener login(Trener t) throws Exception {
        LoginSO loginso = new LoginSO();
        loginso.izvrsi(t, null);
        System.out.println("Klasa kontroller: "+loginso.getUlogovaniTrener());
        return loginso.getUlogovaniTrener();
    }

    public List<Klijent> ucitajKlijente() throws Exception {
         UcitajKlijenteSO operacija = new UcitajKlijenteSO();
         operacija.izvrsi(null, null);
         System.out.println("KLASA CONTROLLER " + operacija.getKlijenti());
         return operacija.getKlijenti();
    }

    public void obrisiKlijenta(Klijent k) throws Exception {
        ObrisiKlijentaSO operacija = new ObrisiKlijentaSO();
        operacija.izvrsi(k, null);
    }

    public void dodajKlijenta(Klijent k) throws Exception {
        KreirajKlijentaSO operacija = new KreirajKlijentaSO();
        operacija.izvrsi(k, null);
    }

    public List<NivoFizickeSpreme> ucitajNivoe() throws Exception {
        UcitajNivoeSO operacija =new UcitajNivoeSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER: "+operacija.getNivoi());
        return operacija.getNivoi();
    }
    
}
