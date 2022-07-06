/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Menu;

import java.io.File;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fraba
 */
public class MenuController implements Initializable {

    @FXML
    private Button btn_instrucoes;
    @FXML
    private Button btn_sair;
    @FXML
    private ImageView img_titulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void abrirJogo(ActionEvent event) throws IOException {
        URL url = new File("src/jinx/PreLobby/PreLobby.fxml").toURI().toURL();
        Parent preLobby = FXMLLoader.load(url);
        
        Scene scenePreLobby = new Scene(preLobby);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scenePreLobby);
        window.show();
    }

    @FXML
    private void abrirInstrucoes(ActionEvent event) throws IOException {
        URL url = new File("src/jinx/Instrucoes/Instrucoes.fxml").toURI().toURL();
        Parent instrucoes = FXMLLoader.load(url);
        
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
