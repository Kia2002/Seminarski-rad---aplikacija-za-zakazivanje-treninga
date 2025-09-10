/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import komunikacija.Operacija;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import controller.Controller;
import domain.Klijent;
import domain.NivoFizickeSpreme;
import domain.Trener;
import exception.KlijentVecPostojiException;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Aleksa
 */
// ====== ObradaKlijentskihZahteva.java ======
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObradaKlijentskihZahteva extends Thread {
    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    @Override
public void run() {
    while(!kraj) {
        Object obj = primalac.primi();
        if (obj == null) { 
            System.out.println("Klijent je zatvorio vezu, nit se prekida.");
            prekini();
            break;
        }

        try {
            Zahtev zahtev = (Zahtev) obj;
            Odgovor odgovor = new Odgovor();

            switch(zahtev.getOperacija()) {
                case LOGIN:
                    Trener t = (Trener) zahtev.getParametar();
                    t = Controller.getInstance().login(t);
                    odgovor.setOdgovor(t);
                    break;
                case UCITAJ_KLIJENTE:
                    List<Klijent> klijenti = Controller.getInstance().ucitajKlijente();
                    odgovor.setOdgovor(klijenti);
                    break;
                case OBRISI_KLIJENTA:
                    try {
                        Klijent k = (Klijent) zahtev.getParametar();
                        Controller.getInstance().obrisiKlijenta(k);
                        odgovor.setOdgovor(null);
                    } catch(Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                case UCITAJ_NIVOE:
                    List<NivoFizickeSpreme> nivoi = Controller.getInstance().ucitajNivoe();
                    odgovor.setOdgovor(nivoi);
                    break;
                case DODAJ_KLIJENTA:
                        Klijent klijent = (Klijent) zahtev.getParametar();
                        try {
                            controller.Controller.getInstance().dodajKlijenta(klijent);
                            odgovor.setOdgovor(null);

                        } catch (KlijentVecPostojiException kvp) {

                            odgovor.setOdgovor(kvp);

                        } catch (Exception excp) {

                            odgovor.setOdgovor(excp);
                        }
                        break;
                        
                        case AZURIRAJ_KLIJENTA:
                        Klijent aKlijent = (Klijent) zahtev.getParametar();
                        try {
                            controller.Controller.getInstance().azurirajKlijenta(aKlijent);
                            odgovor.setOdgovor(null);

                        } catch (KlijentVecPostojiException kvp) {

                            odgovor.setOdgovor(kvp);

                        } catch (Exception excp) {

                            odgovor.setOdgovor(excp);
                        }
                        break;
                default:
                    System.out.println("GRESKA, NE POSTOJI OPERACIJA");
            }

            posiljalac.posalji(odgovor);

        } catch(Exception ex) {
            ex.printStackTrace();
            prekini(); 
            break;
        }
    }
}

    public void prekini() {
        kraj = true;
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
}

    
   

