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
public class Casa implements Serializable{
    private Peca peca;
    
    public Casa(Peca peca) {
      this.peca = peca;
    }
    
    public void setPeca(Peca peca){
        this.peca = peca;
    }
    
    public void removePeca(){
        this.peca = null;
    }
    
    public Peca getPeca(){
        return peca;
    }
    
    public boolean temPeca(){
        if(peca == null) return false;
        return peca.getCor() != null;
    }
}
