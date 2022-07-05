/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Server;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jinx.Server.Jogo.Jogo;

/**
 *
 * @author rfcm2
 */
public class Server {
    
    private ServerSocket ss;
    private int numJogadores = 0;
    private static ArrayList<ClientHandler> jogadores;
    private static Jogo jogo;
    private Color[] cores;

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
                System.out.println("Jogador #" + numJogadores + "conectado.");
                
                ClientHandler cl = new ClientHandler(s, numJogadores, cores[numJogadores]);
                jogadores.add(cl);
                Thread t = new Thread(cl);
                t.start();
                numJogadores++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setCores() {
        cores = new Color[6];
        cores[0] = Color.red;
        cores[1] = Color.blue;
        cores[2] = Color.green;
        cores[3] = Color.gray;
        cores[4] = Color.orange;
        cores[5] = Color.yellow;
    }
    
    static void comecarJogo() throws IOException {
        jogo = new Jogo(jogadores);
    }
    
    static Jogo getJogo(){
        return jogo;
    }
    
    public static void main(String[] args) {
        Server server = new Server();
        server.setCores();
        server.aceitaLigacoes();
    }
}
