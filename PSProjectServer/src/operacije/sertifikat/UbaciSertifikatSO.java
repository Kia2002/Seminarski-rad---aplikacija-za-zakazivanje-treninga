/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.sertifikat;

import domain.Klijent;
import domain.Sertifikat;
import exception.KlijentVecPostojiException;
import exception.SertifikatVecPostojiException;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Aleksa
 */
public class UbaciSertifikatSO extends ApstraktnaGenerickaOperacija{

    @Override
protected void preduslovi(Object param) throws Exception {
    if (param == null || !(param instanceof Sertifikat)) {
        throw new Exception("Sistem ne može da zapamti sertifikat.");
    }

    Sertifikat noviSertifikat = (Sertifikat) param;

    
    String uslov = " WHERE naziv = '" + noviSertifikat.getNaziv() + "'";
Sertifikat postojeci = (Sertifikat) broker.get(noviSertifikat, uslov);
    if (postojeci != null) {
        throw new SertifikatVecPostojiException(
            "Sistem ne može da zapamti sertifikat."
        );
    }
}
    

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
       broker.add((Sertifikat)param);
        
    }
    
}