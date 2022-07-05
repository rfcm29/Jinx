/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Server.Jogo;

import java.io.IOException;
import java.util.ArrayList;
import jinx.Server.ClientHandler;
import jinx.Server.Server;

/**
 *
 * @author rfcm2
 */
public class Jogo {
    private final ArrayList<ClientHandler> jogadores;
    private Casa[][] tabuleiro;
    private int jogadorAtual;
    
    public Jogo(ArrayList<ClientHandler> jogadores) throws IOException {
        this.jogadores = jogadores;
        jogadorAtual = 0;
        tabuleiro = new Casa[5][5];
        
        criarTabuleiro();
        comecar();
    }

    private void comecar() throws IOException{
        for(ClientHandler cl: jogadores){
            cl.sendToClient("game#start");
            cl.sendToClient(jogadores);
            cl.sendToClient(new Movimento(tabuleiro, jogadorAtual, "Jogo começou"));
        }
            
    }

    private void criarTabuleiro() {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                tabuleiro[i][j] = new Casa();
            }
        }
    }
    
    public void movimento(){
        
    }
    
}
