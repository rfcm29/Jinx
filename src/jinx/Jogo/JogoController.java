/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Jogo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author fraba
 */
public class JogoController implements Initializable {

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
        // TODO
    }    

    @FXML
    private void lancarDados(ActionEvent event) {
    }
    
}
