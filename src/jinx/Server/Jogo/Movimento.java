/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Server.Jogo;

import java.io.Serializable;


/**
 *
 * @author rfcm2
 */
public class Movimento implements Serializable{
    public final Casa[][] tabuleiro;
    public final int proximo;
    public final String mensagem;

    public Movimento(Casa[][] tabuleiro, int proximo, String mensagem) {
        this.tabuleiro = tabuleiro;
        this.proximo = proximo;
        this.mensagem = mensagem;
    }
    
}
