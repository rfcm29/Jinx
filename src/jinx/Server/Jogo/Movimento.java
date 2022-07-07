/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Server.Jogo;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author rfcm2
 */
public class Movimento implements Serializable {
    public final int x;
    public final int y;
    public final Color cor;
    public final String acao;

    public Movimento(int x, int y, Color cor, String acao) {
        this.x = x;
        this.y = y;
        this.cor = cor;
        this.acao = acao;
    }
}
