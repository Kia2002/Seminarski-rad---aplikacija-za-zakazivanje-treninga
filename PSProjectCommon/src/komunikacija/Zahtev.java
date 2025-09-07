/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author Aleksa
 */
public class Zahtev implements Serializable{
    private Operacija operacija;
    private Object parametar;

    public Zahtev() {
    }

    public Zahtev(Operacija operacija) {
        this.operacija = operacija;
    }

    public Zahtev(Operacija operacija, Object parametar) {
        this.operacija = operacija;
        this.parametar = parametar;
    }

    public Operacija getOperacija() {
        return operacija;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }

    public void setParameter(Object parametar) {
        this.parametar = parametar;
    }

    @Override
    public String toString() {
        return "Request{" + "operation=" + operacija + ", parameter=" + parametar + '}';
    }
    
    
}
