/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Server.Jogo;

import java.io.IOException;
import java.util.ArrayList;
import jinx.Server.ClientHandler;

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
        tabuleiro = new Casa[6][6];
        criarTabuleiro();
        comecar();
    }

    private void comecar() throws IOException{
        for(ClientHandler cl: jogadores){
            cl.sendToClient("game#start");
            cl.sendToClient(jogadores);
            cl.sendToClient("jogador#" + jogadorAtual);
        }
            
    }

    private void criarTabuleiro() {
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                tabuleiro[i][j] = new Casa(null);
            }
        }
    }
    
    public void movimento(int x, int y, ClientHandler jogador) throws IOException{
        if(jogador.numPecas() == 0){
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 6; j++){
                    if(tabuleiro[i][j].temPeca()){
                        if(tabuleiro[i][j].getPeca().getCor().equals(jogador.getCor())){
                           System.out.println("remove peca");
                           tabuleiro[i][j].removePeca();
                           enviaParaCliente(new Movimento(i, j, null, "remover"));
                           jogador.addPeca();
                           
                        }   
                    }
                }
            }
            //enviaParaCliente("info#" + jogador.getNome() + ": ficou sem peças para jogar. Todas as suas peças foram removidas do tabuleiro para poder jogar");
        }
        
        if(tabuleiro[x][y].temPeca()){
            if(tabuleiro[x][y].getPeca().getCor().equals(jogador.getCor())){
                //enviaParaCliente("info#" + jogador.getNome() + ": caiu numa casa com peça sua. As suas peças foram removidas do tabuleiro");
                proximoJogador();
                enviaParaCliente("jogador#" + jogadorAtual);
                enviaParaCliente(jogadores);
                for(int i = 0; i < 6; i++){
                    for(int j = 0; j < 6; j++){
                        if(tabuleiro[i][j].temPeca()){
                            if(tabuleiro[i][j].getPeca().getCor().equals(jogador.getCor())){
                                System.out.println("remove peca");
                                tabuleiro[i][j].removePeca();
                                enviaParaCliente(new Movimento(i, j, null, "remover"));
                                jogador.addPeca();
                            }
                        }
                    }
                }
                //enviaParaCliente(jogadores);
                
            }
            else{
                for(ClientHandler cl: jogadores){
                    if(tabuleiro[x][y].getPeca().getCor().equals(cl.getCor())){
                        tabuleiro[x][y].removePeca();
                        proximoJogador();
                        enviaParaCliente("jogador#" + jogadorAtual);
                        enviaParaCliente(jogadores);
                        enviaParaCliente(new Movimento(x, y, null, "remover"));
                        cl.addPeca();
                        tabuleiro[x][y].setPeca(new Peca(jogador.getCor()));
                        enviaParaCliente(new Movimento(x, y, jogador.getCor(), "colocar"));
                        jogador.removePeca();
                        
                        boolean jinx = verificaJinx();
                        if(jinx){
                            jogador.rondasganhas();
                            if(jogador.rondasGanhas() == 3){
                                fimJogo(jogador);
                                return;
                            }
                            resetTabuleiro();
                            //enviaParaCliente(jogadores);
                            //enviaParaCliente("info#" + jogador.getNome() + ": ganhou a ronda. é o primeiro a jogar na proxima ronda!");
                            return;
                        }
                        //enviaParaCliente(jogadores);
                        //enviaParaCliente("info#" + jogador.getNome() + ": caiu numa casa de " + cl.getNome() + " e roubou-lhe o lugar na posição: " + (x + 1) + ", " + (y + 1) + ".");
                        return;
                    }
                }
            }
        } else {
            tabuleiro[x][y].setPeca(new Peca(jogador.getCor()));
            jogador.removePeca();
            boolean jinx = verificaJinx();
            if(jinx){
                jogador.rondasganhas();
                if(jogador.rondasGanhas() == 3){
                    fimJogo(jogador);
                    return;
                }
                resetTabuleiro();
                return;
            }
            proximoJogador();
            enviaParaCliente("jogador#" + jogadorAtual);
            enviaParaCliente(jogadores);
            //enviaParaCliente(jogadores);
            //enviaParaCliente("info#" + jogador.getNome() + ": colocou uma peça na posição:" + y + " - " + x);
            enviaParaCliente(new Movimento(x, y, jogador.getCor(), "colocar"));
        }
    }

    private boolean verificaJinx() {
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(verificaHorizontal(i, j))return true;
                if(verificaVertical(i, j))return true;
                if(verificaDiagonal(i, j))return true;
            }
        }
        
        return false;
    }

    private boolean verificaHorizontal(int i, int j) {
        if(j < 4){
            if(tabuleiro[i][j+1].temPeca() && tabuleiro[i][j+2].temPeca()){
                if(tabuleiro[i][j].getPeca() == tabuleiro[i][j+1].getPeca() && tabuleiro[i][j].getPeca() == tabuleiro[i][j+2].getPeca()){
                    return true;
                }
            }
        }
        if(j > 0 && j < 5){
            if(tabuleiro[i][j-1].temPeca() && tabuleiro[i][j+1].temPeca()){
                if(tabuleiro[i][j].getPeca() == tabuleiro[i][j-1].getPeca() && tabuleiro[i][j].getPeca() == tabuleiro[i][j+1].getPeca()){
                    return true;
                }
            }
        }
        if(j > 1){
            if(tabuleiro[i][j-2].temPeca() && tabuleiro[i][j-1].temPeca()){
                if(tabuleiro[i][j].getPeca() == tabuleiro[i][j-2].getPeca() && tabuleiro[i][j].getPeca() == tabuleiro[i][j-1].getPeca()){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificaVertical(int i, int j) {
        if(i < 4){
            if(tabuleiro[i+1][j].temPeca() && tabuleiro[i+2][j].temPeca()){
                if(tabuleiro[i][j].getPeca() == tabuleiro[i+1][j].getPeca() && tabuleiro[i][j].getPeca() == tabuleiro[i+2][j].getPeca()){
                    return true;
                }
            }
        } 
        if(i > 0 && i < 5){
            if(tabuleiro[i-1][j].temPeca() && tabuleiro[i+1][j].temPeca()){
                if(tabuleiro[i][j].getPeca() == tabuleiro[i-1][j].getPeca() && tabuleiro[i][j].getPeca() == tabuleiro[i+1][j].getPeca()){
                    return true;
                }
            }
        }
        if(i > 1){
            if(tabuleiro[i-2][j].temPeca() && tabuleiro[i-1][j].temPeca()){
                if(tabuleiro[i][j].getPeca() == tabuleiro[i-2][j].getPeca() && tabuleiro[i][j].getPeca() == tabuleiro[i-1][j].getPeca()){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificaDiagonal(int i, int j) {
        /*
        if(i < 4 && j < 4){
            if(tabuleiro[i+1][j+1].temPeca() && tabuleiro[i+2][j+2].temPeca()){
                if(tabuleiro[i][j].getPeca() == tabuleiro[i+1][j+1].getPeca() && tabuleiro[i][j].getPeca() == tabuleiro[i+2][j+2].getPeca()){
                    return true;
                }
            }
        }
        if(i > 0 && i < 5 && j > 0 && j < 5){
            if(tabuleiro[i-1][j-1].temPeca() && tabuleiro[i+1][j+1].temPeca()){
                if(tabuleiro[i][j].getPeca() == tabuleiro[i-1][j-1].getPeca() && tabuleiro[i][j].getPeca() == tabuleiro[i+1][j+1].getPeca()){
                    return true;
                }
            }
        }
        if(i > 1 && j > 1){
            if(tabuleiro[i-2][j-2].temPeca() && tabuleiro[i-1][j-1].temPeca()){
                if(tabuleiro[i][j].getPeca() == tabuleiro[i-2][j-2].getPeca() && tabuleiro[i][j].getPeca() == tabuleiro[i-1][j-1].getPeca()){
                    return true;
                }
            }
        }
        if(i < 4 && j > 1){
            if(tabuleiro[i+1][j-1].temPeca() && tabuleiro[i+2][j-2].temPeca()){
                if(tabuleiro[i][j].getPeca() == tabuleiro[i+1][j-1].getPeca() && tabuleiro[i][j].getPeca() == tabuleiro[i+2][j-2].getPeca()){
                    return true;
                }
            }
        }
        if(i > 0 && i < 5 && j > 0 && j < 5){
            if(tabuleiro[i+1][j-1].temPeca() && tabuleiro[i-1][j+1].temPeca()){
                if(tabuleiro[i][j].getPeca() == tabuleiro[i+1][j-1].getPeca() && tabuleiro[i][j].getPeca() == tabuleiro[i-1][j+1].getPeca()){
                    return true;
                }
            }
        }
        if(i > 1 && j < 4){
            if(tabuleiro[i+2][j-2].temPeca() && tabuleiro[i+1][j-1].temPeca()){
                if(tabuleiro[i][j].getPeca() == tabuleiro[i-1][j+2].getPeca() && tabuleiro[i][j].getPeca() == tabuleiro[i-2][j+2].getPeca()){
                    return true;
                }
            }
        }
        */
        return false;
    }

    private void proximoJogador() {
        if(jogadorAtual == (jogadores.size() - 1)) jogadorAtual = 0;
        else jogadorAtual++;
    }

    private void fimJogo(ClientHandler jogador) throws IOException {
        for(ClientHandler cl: jogadores){
            cl.sendToClient("O jogador" + jogador.getNome() + "ganhou 3 rondas. É o vencedor do jogo");
            cl.sendToClient(jogadores);
            cl.sendToClient("game#end");
        }
    }

    private void enviaParaCliente(Object object) throws IOException {
        for(ClientHandler cl: jogadores){
            cl.sendToClient(object);
        }
    }

    private void resetTabuleiro() {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                tabuleiro[i][j].removePeca();
            }
        }
        for(ClientHandler cl: jogadores){
            cl.setPecas(9);
        }
    }
}
