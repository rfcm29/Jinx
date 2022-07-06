/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.PreLobby;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jinx.Jogo.JogoController;

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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void entrarJogo(ActionEvent event) throws IOException {
        URL url = new File("src/jinx/Jogo/Jogo.fxml").toURI().toURL();
        FXMLLoader fxmlloader = new FXMLLoader(url);
        Parent preLobby = (Parent)fxmlloader.load();

        JogoController controller = fxmlloader.<JogoController>getController();
        controller.setValues(txt_ip.getText(), Integer.parseInt(txt_porta.getText()), txt_nome.getText());

        Scene scenePreLobby = new Scene(preLobby);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scenePreLobby);
        window.show();
    }
}
