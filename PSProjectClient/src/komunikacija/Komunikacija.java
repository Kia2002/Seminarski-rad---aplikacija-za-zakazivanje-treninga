/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domain.EvidencijaTreninga;
import domain.Klijent;
import domain.NivoFizickeSpreme;
import domain.Sertifikat;
import domain.StavkaEvidencijeTreninga;
import domain.Termin;
import domain.Trener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import kordinator.Kordinator;

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

    public void azurirajKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_KLIJENTA, k);
        posiljalac.posalji(zahtev);
         Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor()==null){
            System.out.println("USPEŠNO");
            Kordinator.getInstance().osveziFormu();
        }else{
            Exception e = (Exception) odgovor.getOdgovor();
            throw e;
        }
        
    }

    public List<EvidencijaTreninga> ucitajEvidencijeTreninga() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_EVIDENCIJE, null);
        List<EvidencijaTreninga> evidencije = new ArrayList<>();
           
        posiljalac.posalji(zahtev);
        ////
        Odgovor odg = (Odgovor) primalac.primi();
        evidencije = (List<EvidencijaTreninga>) odg.getOdgovor();
        return evidencije;
    }

   

    public List<StavkaEvidencijeTreninga> ucitajStakve(Long idEvidencijaTreninga) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE, idEvidencijaTreninga);
        List<StavkaEvidencijeTreninga> stavke = new ArrayList<>();
           
        posiljalac.posalji(zahtev);
        ////
        Odgovor odg = (Odgovor) primalac.primi();
        stavke = (List<StavkaEvidencijeTreninga>) odg.getOdgovor();
        return stavke;
    }

    public List<Trener> ucitajTrenere() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TRENERE,null);
        List<Trener> treneri = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        treneri = (List<Trener>) odg.getOdgovor();
        return treneri;
    }

    public List<Termin> ucitajTermine() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TERMINE,null);
        List<Termin> termini = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        termini = (List<Termin>) odg.getOdgovor();
        return termini;
    }

    public void kreirajEvidenciju(EvidencijaTreninga ev) throws Exception {
    Zahtev zahtev = new Zahtev(Operacija.DODAJ_EVIDENCIJU, ev);
    posiljalac.posalji(zahtev);

    Odgovor odgovor = (Odgovor) primalac.primi();
    

    if (odgovor.getOdgovor() == null) {
        System.out.println("USPEŠNO");
    } else {
        Exception e = (Exception) odgovor.getOdgovor();
        throw e;
    }
}

    public void ubaciSertifikat(Sertifikat s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_SERTIFIKAT, s);
        posiljalac.posalji(zahtev);
         Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor()==null){
            System.out.println("USPEŠNO");
        }else{
            Exception e = (Exception) odgovor.getOdgovor();
            throw e;
        }
    }

    public List<EvidencijaTreninga> ucitajEvidencijeTreningaZaposlenog(Trener ulogovani) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_EVIDENCIJE_ZAPOSLENOG, ulogovani);
        List<EvidencijaTreninga> evidencije = new ArrayList<>();
           
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        evidencije = (List<EvidencijaTreninga>) odg.getOdgovor();
        return evidencije;
    }

    public void izmeniEvidenciju(EvidencijaTreninga ev) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_EVIDENCIJU_TRENINGA, ev);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPESNO");
        } else {
            System.out.println("NEUSPESNO");
        }
    }

        
}
