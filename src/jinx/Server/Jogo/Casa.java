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
class Casa implements Serializable{
    private Peca peca;

    public Casa() {
        
    }
    
    private void setPeca(Peca peca){
        this.peca = peca;
    }
    
    private void removePeca(){
        this.peca = null;
    }
    
    private Peca getPeca(){
        return peca;
    }
}
