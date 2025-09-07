/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kordinator;

import controller.KreirajKlijentaController;
import controller.LoginController;
import controller.GlavnaFormaController;
import controller.PrikazKlijenataController;
import domain.Trener;
import forme.KreirajKlijentaForma;
import forme.LoginForma;
import forme.GlavnaForma;
import forme.PrikazKlijenataForma;

/**
 *
 * @author Aleksa
 */
public class Kordinator {
    private static Kordinator instance;
    private LoginController loginController;
    private GlavnaFormaController mainFormController;
    private Trener ulogovani;
        private PrikazKlijenataController pkController;
            private KreirajKlijentaController dkController;
    
    private Kordinator() {
    }
    
    public static Kordinator getInstance(){
    
    if(instance == null){
    instance = new Kordinator();
    
    }
    return instance;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    } 

    public void otvoriGlavnuFormu() {
        mainFormController = new GlavnaFormaController(new GlavnaForma());
         mainFormController.otvoriFormu();
    }
    public void otvoriPrikazKlijenataFormu() throws Exception {
        pkController = new PrikazKlijenataController(new PrikazKlijenataForma()); 
        pkController.otvoriFormu();
    }
    
    public Trener getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Trener ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriDodajKlijentaFormu() throws Exception {
        dkController = new KreirajKlijentaController(new KreirajKlijentaForma());
        dkController.otvoriFormu();
    }

   

    
    
    
}
