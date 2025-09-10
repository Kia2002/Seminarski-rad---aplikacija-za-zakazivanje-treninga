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
import forme.FormaMod;
import forme.KreirajKlijentaForma;
import forme.LoginForma;
import forme.GlavnaForma;
import forme.PrikazKlijenataForma;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Object> parametri;
            
            
            
            
    private Kordinator() {
        parametri = new HashMap<>();
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
        dkController.otvoriFormu(FormaMod.DODAJ);
    }

   public void dodajParam(String s, Object o){
   parametri.put(s, o);
   }
   
   public Object vratiParam(String s){
   return parametri.get(s);
   }

    public void otvoriIzmeniKlijentaFormu() throws Exception {
    dkController = new KreirajKlijentaController(new KreirajKlijentaForma());
    dkController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormu() throws Exception {
        pkController.osveziFormu();
    }

    
    
    
}
