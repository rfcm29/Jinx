/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Jogo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import jinx.Client.Client;

/**
 * FXML Controller class
 *
 * @author fraba
 */
public class JogoController implements Initializable {

    private Client client;
    private String ip;
    private int porta;
    
    @FXML
    private ImageView img_tabuleiro;
    @FXML
    private Label lbl_titulo;
    @FXML
    private TableColumn<?, ?> tbl_nome;
    @FXML
    private TableColumn<?, ?> tbl_pecas;
    @FXML
    private TableColumn<?, ?> tbl_cor;
    @FXML
    private TableColumn<?, ?> tbl_rondas;
    @FXML
    private Label lbl_ronda;
    @FXML
    private Button btn_lancarDados;
    @FXML
    private Label lbl_dado1;
    @FXML
    private Label lbl_dado2;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Thread(()-> {
            client = new Client(ip, porta);
        }).start();
    }    

    @FXML
    private void lancarDados(ActionEvent event) {
    }

    public void setValues(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;
    }
    
}
