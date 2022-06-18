/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author fraba
 */
public class MenuController implements Initializable {

    @FXML
    private Label lbl_titulo;
    @FXML
    private Button btn_entrarJogo;
    @FXML
    private Button btn_instrucoes;
    @FXML
    private Button btn_sair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void abrirJogo(ActionEvent event) {
    }

    @FXML
    private void abrirInstrucoes(ActionEvent event) {
    }

    @FXML
    private void sair(ActionEvent event) {
    }
    
}
