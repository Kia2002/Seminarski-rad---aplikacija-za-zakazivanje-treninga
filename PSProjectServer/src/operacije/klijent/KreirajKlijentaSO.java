/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijent;

import domain.Klijent;
import exception.KlijentVecPostojiException;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Aleksa
 */
 public class KreirajKlijentaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Klijent)) {

            throw new Exception("Sistem ne moze da kreira klijenta.");
        }
        
        Klijent noviKlijent = (Klijent) param;

        String uslov = " JOIN nivofizickespreme ON klijent.idnivofizickespreme = nivofizickespreme.idnivofizickespreme WHERE email = '" + noviKlijent.getEmail() + "'";
        Klijent postojeci = (Klijent) broker.get(noviKlijent, uslov);
        if (postojeci != null) {
            throw new KlijentVecPostojiException("Sistem ne moze da kreira klijenta.");
        }
    }
    

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        broker.add((Klijent)param);
    }
    
}
