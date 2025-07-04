/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Trener;
import so.login.LoginSO;

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
        loginso.execute(t, null);
        System.out.println("Klasa kontroller: "+loginso.getUlogovaniTrener());
        return loginso.getUlogovaniTrener();
    }
    
}
