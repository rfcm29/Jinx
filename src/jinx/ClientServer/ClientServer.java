/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.ClientServer;

import java.net.InetAddress;

/**
 *
 * @author rfcm2
 */
public class ClientServer {
    private int port;
    private InetAddress ip;
    
    public ClientServer(int port, InetAddress ip) {
        this.port = port;
        this.ip = ip;
    }
    
    
}
