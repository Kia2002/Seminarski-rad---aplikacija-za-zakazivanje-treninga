/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.HandleClientThread;

/**
 *
 * @author Aleksa
 */
public class Server {
    boolean end = false;
    ServerSocket serverSocket;
    List<HandleClientThread> clients;
    
    public Server(){
        clients = new LinkedList<>();
    }
    
    public void startServer() throws IOException{
    ServerSocket serverSocket = new ServerSocket(9000);
    while(!end){
    Socket s = serverSocket.accept();
        System.out.println("Client is connected!");
        
        HandleClientThread hct = new HandleClientThread(s);
        clients.add(hct);
        hct.start();
    }
    }
    
    public void stopServer(){
    end = true;
        try {
            for(HandleClientThread c : clients){
            c.stopp();
            }
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
