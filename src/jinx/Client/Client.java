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
public class Client {

    private Socket socket;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;
    
    public Client(String ip, int porta) {
        System.out.println("---Client---");
        
        try {
            socket =  new Socket(ip, porta);
            objIn = new ObjectInputStream(socket.getInputStream());
            objOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
