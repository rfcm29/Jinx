/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Final;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author fraba
 */
public class FinalController implements Initializable {

    @FXML
    private Label lbl_titulo;
    @FXML
    private Label lbl_vencedor;
    @FXML
    private TableColumn<?, ?> tbl_nome;
    @FXML
    private TableColumn<?, ?> tbl_cor;
    @FXML
    private TableColumn<?, ?> tbl_rondas;
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
    private void sair(ActionEvent event) {
        Platform.exit();
    }
    
}
