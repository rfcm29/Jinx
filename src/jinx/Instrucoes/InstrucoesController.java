/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Instrucoes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fraba
 */
public class InstrucoesController implements Initializable {

    @FXML
    private Label lbl_titulo;
    @FXML
    private Text txt_instrucoes;
    @FXML
    private Button btn_voltar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void voltarMenu(ActionEvent event) throws IOException {
        //Parent menu = FXMLLoader.load(getClass().getResource("Menu/Menu.fxml"));
        
        URL url = new File("src/jinx/Menu/Menu.fxml").toURI().toURL();
        Parent menu = FXMLLoader.load(url);
        
        Scene sceneMenu = new Scene(menu);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneMenu);
        window.show();
    }
    
}
