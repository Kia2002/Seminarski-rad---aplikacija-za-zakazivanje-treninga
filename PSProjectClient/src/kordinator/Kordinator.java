/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kordinator;

import controller.KreirajKlijentaController;
import controller.LoginController;
import controller.GlavnaFormaController;
import controller.IzmeniEvidencijuTreningaController;
import controller.KreirajEvidencijuTreningaController;
import controller.PrikazEvidencijaController;
import controller.PrikazKlijenataController;
import controller.UbaciSertifikatController;
import domain.Trener;
import forme.FormaMod;
import forme.KreirajKlijentaForma;
import forme.LoginForma;
import forme.GlavnaForma;
import forme.IzmeniEvidencijuTreningaForma;
import forme.KreirajEvidencijuTreningaForma;
import forme.PrikazEvidencijaTreningaForma;
import forme.PrikazKlijenataForma;
import forme.UbaciSertifikatForma;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;

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
    private PrikazEvidencijaController peController; 
    private KreirajEvidencijuTreningaController keController;    
       private UbaciSertifikatController usController;       
       private IzmeniEvidencijuTreningaController ieController;
            
       
       
       
    private Kordinator() {
        parametri = new HashMap<>();
    }
    public static void applyGlobalTheme() {
    UIManager.put("OptionPane.background", Color.WHITE);
    UIManager.put("Panel.background", Color.WHITE);
    UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
    UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 14));
    UIManager.put("Button.background", new Color(45, 137, 239));
    UIManager.put("Button.foreground", Color.WHITE);
    UIManager.put("Button.focusPainted", false);
}
    public static Kordinator getInstance(){
    
    if(instance == null){
    instance = new Kordinator();
    applyGlobalTheme();
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
    public void otvoriPrikazEvidencijaFormu() throws Exception {
        peController = new PrikazEvidencijaController(new PrikazEvidencijaTreningaForma()); 
        peController.otvoriFormu();
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

    public void otvoriDetaljiKlijentaFormu() throws Exception {
        dkController = new KreirajKlijentaController(new KreirajKlijentaForma());
    dkController.otvoriFormu(FormaMod.DETALJI);
    }

    public void otvoriKreirajEvidencijuForma() throws Exception {
        keController = new KreirajEvidencijuTreningaController(new KreirajEvidencijuTreningaForma());
        keController.otvoriFormu(FormaMod.DODAJ);
    
    }

    public void otvoriDetaljiEvidencijeFormu() throws Exception {
        keController = new KreirajEvidencijuTreningaController(new KreirajEvidencijuTreningaForma());
    keController.otvoriFormu(FormaMod.DETALJI);
    }

    public void otvoriUbaciSeritikatFormu() throws Exception {
        usController = new UbaciSertifikatController(new UbaciSertifikatForma());
        usController.otvoriFormu();
    }

    public void otvoriIzmeniEvidencijeFormu() throws Exception {
        ieController = new IzmeniEvidencijuTreningaController(new IzmeniEvidencijuTreningaForma());
        ieController.otvoriFormu();
    }

    public void otvoriIzmeniEvidencijeZaposlenogFormu() throws Exception {
        keController = new KreirajEvidencijuTreningaController(new KreirajEvidencijuTreningaForma());
    keController.otvoriFormu(FormaMod.IZMENI);
    }

    public void obrisiParam(String kljuc) {
    parametri.remove(kljuc);
}
    
    
}
