/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    private void abrirJogo(ActionEvent event) throws IOException {
        Parent preLobby = FXMLLoader.load(getClass().getResource("PreLobby/PreLobby.fxml"));
        
        Scene scenePreLobby = new Scene(preLobby);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scenePreLobby);
        window.show();
    }

    @FXML
    private void abrirInstrucoes(ActionEvent event) throws IOException {
        Parent instrucoes = FXMLLoader.load(getClass().getResource("Instrucoes/Instrucoes.fxml"));
        
        Scene sceneInstrucoes = new Scene(instrucoes);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneInstrucoes);
        window.show();
    }

    @FXML
    private void sair(ActionEvent event) {
        Platform.exit();
    }
    
}
