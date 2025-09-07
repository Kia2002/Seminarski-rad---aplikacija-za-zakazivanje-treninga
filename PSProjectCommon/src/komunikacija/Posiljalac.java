/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Aleksa
 */
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Posiljalac {
    private Socket socket;
    private ObjectOutputStream out;

    public Posiljalac(Socket socket) {
        this.socket = socket;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void posalji(Object object) throws Exception {
        try {
            if (out != null) {
                out.writeObject(object);
                out.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception("Sending object error: \n" + ex.getMessage());
        }
    }

    public void close() {
        try {
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
