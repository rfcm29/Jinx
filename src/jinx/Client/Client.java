/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Client;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rfcm2
 */
public class Client{

    private Socket socket;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;
    private int playerID;
    
    public Client(String ip, int port) throws IOException {
        System.out.println("---Client---");
        
        socket = new Socket(ip, port);
        objOut = new ObjectOutputStream(socket.getOutputStream());
        objOut.flush();
        objIn = new ObjectInputStream(socket.getInputStream());
    }
    
    public void sendToServer(Object object){
        try {
            objOut.writeObject(object);
            objOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObjectInputStream getObjIn() {
        return objIn;
    }
}
