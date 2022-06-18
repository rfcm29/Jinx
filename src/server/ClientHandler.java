/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 *
 * @author rfcm2
 */
class ClientHandler implements Runnable{
    
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    boolean isloggedin;

    ClientHandler(Socket s, String string, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.name = string;
        this.isloggedin = true;
    }

    @Override
    public void run() {
        String recebido;
        
        while(true){
            try{
                recebido = dis.readUTF();
                System.out.println(recebido);
                
                if(recebido.endsWith("logout")){
                    this.isloggedin = false;
                    this.s.close();
                    break;
                }
                StringTokenizer st = new StringTokenizer(recebido, "#");
                String MsgToSend = st.nextToken();
                String recipient = st.nextToken();
                
                for(ClientHandler mc: Server.players){
                    if(mc.name.equals(recipient) && mc.isloggedin){
                        mc.dos.writeUTF(name + ": " + MsgToSend);
                        break;
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
}
