/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author Aleksa
 */
public enum Operacija implements Serializable{
    LOGIN,
    
    UCITAJ_KLIJENTE, 
    OBRISI_KLIJENTA, 
    DODAJ_KLIJENTA, 
    UCITAJ_NIVOE,
    AZURIRAJ_KLIJENTA, UCITAJ_EVIDENCIJE, UCITAJ_STAVKE, UCITAJ_TRENERE, UCITAJ_TERMINE, DODAJ_EVIDENCIJU, UBACI_SERTIFIKAT, UCITAJ_EVIDENCIJE_ZAPOSLENOG, IZMENI_EVIDENCIJU_TRENINGA
}
