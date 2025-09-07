/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import kordinator.Kordinator;
import domain.Trener;
import forme.LoginForma;
import forme.GlavnaForma;

/**
 *
 * @author Aleksa
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;
    
    
    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListener();
    }

    private void addActionListener() {
        
    }

    public void otvoriFormu() {
        Trener ulogovani = Kordinator.getInstance().getUlogovani();
        gf.setVisible(true);
        gf.getLblUlogovani().setText("Zdravo," + " "+ulogovani.getIme()+ " "+ ulogovani.getPrezime());         
    }
}
