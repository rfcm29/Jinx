/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author rfcm2
 */
class ClientHandler implements Runnable{
    
    private int ID;
    private Socket socket;
    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;

    ClientHandler(Socket s, int ID) throws IOException {
        this.ID = ID;
        this.socket = s;
        objIn = new ObjectInputStream(socket.getInputStream());
        objOut = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        
    }
    
}
