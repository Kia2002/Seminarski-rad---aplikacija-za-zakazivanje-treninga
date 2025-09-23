/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import kordinator.Kordinator;
import domain.Trener;
import forme.LoginForma;
import forme.GlavnaForma;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Aleksa
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;
    
    
    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
         customizeUI();
        addActionListener();
    }
private void customizeUI() {
        
        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 14);

        gf.getLblUlogovani().setFont(fontLabel);
        gf.getLblDatum().setFont(fontLabel);
        gf.getLblVreme().setFont(fontLabel);

        
        gf.getjButton1().setFont(fontButton);
        gf.getjButton2().setFont(fontButton);

        gf.getjButton1().setBackground(new Color(70, 130, 180));
        gf.getjButton1().setForeground(Color.WHITE);
        gf.getjButton1().setFocusPainted(false);
       gf.getjButton1().setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        gf.getjButton2().setBackground(new Color(220, 53, 69));
        gf.getjButton2().setForeground(Color.WHITE);
        gf.getjButton2().setFocusPainted(false);
        gf.getjButton2().setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        
        gf.getContentPane().setBackground(new Color(245, 245, 245));

        
        gf.getjMenuBar1().setBackground(new Color(230, 230, 230));
        gf.getjMenuBar1().setFont(fontLabel);
        for (int i = 0; i < gf.getjMenuBar1().getMenuCount(); i++) {
            JMenu menu = gf.getjMenuBar1().getMenu(i);
            menu.setFont(fontLabel);
            menu.setBackground(new Color(230, 230, 230));
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null) {
                    item.setFont(fontLabel);
                    item.setBackground(new Color(245, 245, 245));
                }
            }
        }
    }
    private void addActionListener() {
        
    }

    public void otvoriFormu() {
        Trener ulogovani = Kordinator.getInstance().getUlogovani();
        gf.setVisible(true);
        gf.getLblUlogovani().setText("Zdravo," + " "+ulogovani.getIme()+ " "+ ulogovani.getPrezime());         
    }
}
