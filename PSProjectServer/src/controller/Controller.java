/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.EvidencijaTreninga;
import domain.Klijent;
import domain.NivoFizickeSpreme;
import domain.Sertifikat;
import domain.StavkaEvidencijeTreninga;
import domain.Termin;
import domain.Trener;
import exception.KlijentVecPostojiException;
import java.util.List;
import operacije.evidencijaTreninga.AzurirajEvidencijuTreningaSO;
import operacije.evidencijaTreninga.KreirajEvidencijuTreningaSO;
import operacije.evidencijaTreninga.UcitajEvidencijeTreningaSO;
import operacije.evidencijaTreninga.UcitajEvidencijeTreningaZaposlenogSO;
import operacije.evidencijaTreninga.UcitajStavkeSO;
import operacije.klijent.AzurirajKlijentaSO;
import operacije.klijent.KreirajKlijentaSO;
import operacije.klijent.ObrisiKlijentaSO;
import operacije.klijent.UcitajKlijenteSO;
import operacije.login.LoginSO;
import operacije.nivofizickespreme.UcitajNivoeSO;
import operacije.sertifikat.UbaciSertifikatSO;
import operacije.termin.UcitajTermineSO;
import operacije.trener.UcitajTrenereSO;

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

    public void azurirajKlijenta(Klijent aKlijent) throws Exception{
        AzurirajKlijentaSO operacija = new AzurirajKlijentaSO();
        operacija.izvrsi(aKlijent, null);
    }

    public List<EvidencijaTreninga> ucitajEvidencijeTreninga() throws Exception {
        UcitajEvidencijeTreningaSO operacija = new UcitajEvidencijeTreningaSO();
        operacija.izvrsi(null, null);
        return operacija.getEvidencije();
    }

    public List<StavkaEvidencijeTreninga> ucitajStavke(Long id) throws Exception {
        UcitajStavkeSO operacija = new UcitajStavkeSO();
        operacija.izvrsi(id, null);
        return operacija.getStavke();
    }

    public List<Trener> ucitajTrenere() throws Exception {
        UcitajTrenereSO operacija =new UcitajTrenereSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER: "+operacija.getTreneri());
        return operacija.getTreneri();
    }

    public List<Termin> ucitajTermine() throws Exception {
        UcitajTermineSO operacija =new UcitajTermineSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER: "+operacija.getTermini());
        return operacija.getTermini();
    }

    public void dodajEvidenciju(EvidencijaTreninga ev) throws Exception {
        
        KreirajEvidencijuTreningaSO operacija = new KreirajEvidencijuTreningaSO();
        operacija.izvrsi(ev, null);
    
    }

    public void dodajSertifikat(Sertifikat s) throws Exception {
         UbaciSertifikatSO operacija = new UbaciSertifikatSO();
        operacija.izvrsi(s, null);
    }

    public List<EvidencijaTreninga> ucitajEvidencijeTreningaZaposlenog(Trener ulogovani) throws Exception {
        UcitajEvidencijeTreningaZaposlenogSO operacija = new UcitajEvidencijeTreningaZaposlenogSO();
        operacija.izvrsi(ulogovani, null);
        return operacija.getLista();
    }

    public void izmeniEvidencijuRadionice(EvidencijaTreninga ev) throws Exception {
        AzurirajEvidencijuTreningaSO operacija = new AzurirajEvidencijuTreningaSO();
        operacija.izvrsi(ev, null);
    }
    
}
