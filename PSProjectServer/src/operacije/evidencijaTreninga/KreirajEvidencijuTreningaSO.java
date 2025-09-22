/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.evidencijaTreninga;

import domain.EvidencijaTreninga;
import domain.StavkaEvidencijeTreninga;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Aleksa
 */
public class KreirajEvidencijuTreningaSO extends ApstraktnaGenerickaOperacija {

        @Override
    protected void preduslovi(Object param) throws Exception {

        if (param == null || !(param instanceof EvidencijaTreninga)) {
            throw new Exception("Sistem ne moze da kreira evidenciju treninga.");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        EvidencijaTreninga evidencija = (EvidencijaTreninga) param;
        PreparedStatement ps = broker.add(param);
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        Long id = rs.getLong(1);
        evidencija.setIdEvidencijaTreninga(id);
        
        for (StavkaEvidencijeTreninga se : evidencija.getStavke()) {
            se.setEvidencijaTreninga(evidencija);
            broker.add(se);
        }
        




    }
}