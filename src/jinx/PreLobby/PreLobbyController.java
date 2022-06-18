/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.PreLobby;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author fraba
 */
public class PreLobbyController implements Initializable {

    @FXML
    private Label lbl_titulo;
    @FXML
    private TextField txt_nome;
    @FXML
    private Button btn_entrarJogo;
    @FXML
    private TextField txt_ip;
    @FXML
    private TextField txt_porta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void entrarJogo(ActionEvent event) {
    }
    
}
