/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rfcm2
 */
public class Server {
    
    private ServerSocket ss;
    private int numJogadores;
    private ArrayList<ClientHandler> jogadores;

    public Server() {
        System.out.println("---Server---");
        numJogadores = 0;
        jogadores = new ArrayList<>();
        
        try {
            ss = new ServerSocket(8888);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void aceitaLigacoes() {
        try {
            System.out.println("Aceita licações...");
            while(true){
                Socket s = ss.accept();
                numJogadores++;
                System.out.println("Jogador #" + numJogadores + "conectado.");
                
                ClientHandler cl = new ClientHandler(s, numJogadores);
                Thread t = new Thread(cl);
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        Server server = new Server();
        server.aceitaLigacoes();
    }
}
