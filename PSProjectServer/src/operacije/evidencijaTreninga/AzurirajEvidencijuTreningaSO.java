/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.evidencijaTreninga;

import domain.EvidencijaTreninga;
import domain.StavkaEvidencijeTreninga;
import java.util.Date;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Aleksa
 */
public class AzurirajEvidencijuTreningaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof EvidencijaTreninga)) {
            throw new Exception("Sistem ne može da zapamti evidenciju treninga");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        EvidencijaTreninga ev = (EvidencijaTreninga) param;
        List<StavkaEvidencijeTreninga> stavke = ev.getStavke();

        for (StavkaEvidencijeTreninga s : stavke) {
            s.setEvidencijaTreninga(ev);

            switch (s.getStatus()) {
                case NOVA:
                    broker.add(s);
                    break;
                case OBRISANA:
                    broker.delete(s);
                    break;
                case IZMENJENA:
                    broker.edit(s);  
                    break;
                case POSTOJECA:
                   
                    break;
            }
        }

        broker.edit(ev);  
    }
}