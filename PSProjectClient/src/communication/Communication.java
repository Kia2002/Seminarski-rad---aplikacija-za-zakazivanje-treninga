/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domain.Trener;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleksa
 */
public class Communication {
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private static Communication instance;

    public Communication() {
    }
    
    public static Communication getInstance(){
        if(instance==null){
        instance = new Communication();
        }
        return instance;
    }
    
    public void connection(){
        try {
            socket = new Socket("localhost", 9000);
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        } catch (IOException ex) {
            System.out.println("Server nije povezan.");
        }
    }

    public Trener login(String username, String password) throws Exception {
        Trener t = new Trener();
        t.setKorisnickoIme(username);
        t.setSifra(password);
        Request request = new Request(Operation.LOGIN, t);
        
        sender.send(request);
        Response respo = (Response) receiver.receive();
        t = (Trener) respo.getResponse();
       
        return t;
    }
}
