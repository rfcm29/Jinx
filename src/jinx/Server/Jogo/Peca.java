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
public class Peca implements Serializable{
    private Color cor;

    public Peca(Color cor) {
        this.cor = cor;
    }
    
    public Color getCor(){
        return cor;
    }
}
