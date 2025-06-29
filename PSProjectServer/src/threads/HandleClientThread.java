/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Trener;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleksa
 */
public class HandleClientThread extends Thread{
    Socket socket;
    Sender sender;
    Receiver receiver;
    boolean kraj = false;

    public HandleClientThread(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    
    
    
    @Override
    public void run() {
        while(!kraj){
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                switch(request.getOperation()){
                    case LOGIN:
                        Trener t = (Trener) request.getParameter();
                        t = Controller.getInstance().login(t);
                        response.setResponse(t);
                        break;
                    //
                    //
                    //
                    default :
                        System.out.println("GRESKA, NE POSTOJI OPERACIJA");
                }
                sender.send(response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
       
    }
    
    public void stopp(){
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(HandleClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
    
    
} 
    
   

