/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author rfcm2
 */
public class Server {
    static Vector<ClientHandler> players = new Vector<>();
    static int i = 0;
    
    public static void main(String[] args) throws IOException {
        System.out.println("Server open!");
        ServerSocket ss = new ServerSocket(8080);
        
        Socket s;
        while(true){
            s = ss.accept();
            System.out.println("New player: " + s);
            
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            ClientHandler mtch = new ClientHandler(s, "client" + i, dis, dos);
            
            Thread t = new Thread(mtch);
            
            players.add(mtch);
            t.start();
            
            i++;
        }
    }
}
