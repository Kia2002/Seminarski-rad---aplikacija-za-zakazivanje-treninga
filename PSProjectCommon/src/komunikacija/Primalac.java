/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Aleksa
 */
import java.io.ObjectInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class Primalac {
    private Socket socket;
    private ObjectInputStream in;

    public Primalac(Socket socket) {
        this.socket = socket;
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    public Object primi() {
        try {
            return in.readObject();
        } catch (SocketException | EOFException e) {
            
            System.out.println("Veza zatvorena od strane klijenta.");
        } catch (IOException | ClassNotFoundException e) {
          
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            if (in != null) in.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
