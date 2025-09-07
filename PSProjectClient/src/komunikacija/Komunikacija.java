/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domain.Klijent;
import domain.NivoFizickeSpreme;
import domain.Trener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleksa
 */
public class Komunikacija {
    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;

    public Komunikacija() {
    }
    
    public static Komunikacija getInstance(){
        if(instance==null){
        instance = new Komunikacija();
        }
        return instance;
    }
    
    public void connection(){
        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("Server nije povezan.");
        }
    }

    public Trener login(String username, String password) throws Exception {
        Trener t = new Trener();
        t.setKorisnickoIme(username);
        t.setSifra(password);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, t);
        
        posiljalac.posalji(zahtev);
        Odgovor respo = (Odgovor) primalac.primi();
        t = (Trener) respo.getOdgovor();
       
        return t;
    }

    public List<Klijent> ucitajKlijente() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KLIJENTE, null);
        List<Klijent> klijenti = new ArrayList<>();
           
        posiljalac.posalji(zahtev);
        ////
        Odgovor odg = (Odgovor) primalac.primi();
        klijenti = (List<Klijent>) odg.getOdgovor();
        return klijenti;
    }

    public void obrisiKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }else{
            System.out.println("GRESKA");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Greska");
        }
    }

    public void dodajKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KLIJENTA, k);
        posiljalac.posalji(zahtev);
         Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor()==null){
            System.out.println("USPEŠNO");
        }else{
            Exception e = (Exception) odgovor.getOdgovor();
            throw e;
        }
        
    }

    public List<NivoFizickeSpreme> ucitajNivoe() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_NIVOE,null);
        List<NivoFizickeSpreme> nivoi = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        nivoi = (List<NivoFizickeSpreme>) odg.getOdgovor();
        return nivoi;
       }
}
