/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Server;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rfcm2
 */
public class ClientHandler implements Runnable, Serializable{
    
    private final int ID;
    private String nome;
    private int rondasGanhas;
    private int pecas;
    private Color cor;
    
    private final transient Socket socket;
    private final transient ObjectOutputStream objOut;
    private final transient ObjectInputStream objIn;

    ClientHandler(Socket s, int ID, Color cor) throws IOException {
        this.ID = ID;
        this.socket = s;
        objIn = new ObjectInputStream(socket.getInputStream());
        objOut = new ObjectOutputStream(socket.getOutputStream());
        this.cor = cor;
        
        this.rondasGanhas = 0;
        pecas = 9;
    }
    
    public void sendToClient(Object object) throws IOException{
        objOut.writeObject(object);
        objOut.flush();
    }
    
    @Override
    public void run() {
        Object object;
        while(true){
            try {
                object = objIn.readObject();
                
                if(object instanceof String){
                    readString(object);
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void readString(Object object) throws IOException {
        StringTokenizer st = new StringTokenizer((String) object, "#");
        String comando = st.nextToken();
        String acao = st.nextToken();
        
        switch(comando){
            case "nome" : 
                System.out.println("Nome do jogador #" + ID + ":" + acao);
                sendToClient("id#" + ID);
                nome = acao;
                break;
            case "game" :
                if("start".equals(acao)){
                    Server.comecarJogo();
                }
                break;
            case "dados" :
                st = new StringTokenizer(acao, "$");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                
                Server.getJogo().movimento(x, y, this);
                break;
        }
    }
    
    public Color getCor(){
        return cor;
    }
    
    public void removePeca(){
        pecas--;
    }
    
    public void addPeca(){
        pecas++;
    }
    
    public int numPecas(){
        return pecas;
    }
    
    public String getNome(){
        return nome;
    }
    
    public int rondasGanhas(){
        return rondasGanhas;
    }
    
    public void rondasganhas(){
        rondasGanhas++;
    }
    
    public void setPecas(int pecas){
        this.pecas = pecas;
    }
}
