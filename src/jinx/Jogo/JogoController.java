/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Jogo;

import static java.lang.String.valueOf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
    @FXML
    private Button btn_comecaJogo;
    @FXML
    private GridPane gridPaneTabuleiro;
    
    //final HBox r1 = new HBox();
    
    private Circle pino;
    private int x, y;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void lancarDados(ActionEvent event) {        
        x = (int)(1 + Math.random() * 6);  // 1 to 6
        y = (int)(1 + Math.random() * 6);  // 1 to 6
        
        lbl_dado1.setText(valueOf(x));
        lbl_dado2.setText(valueOf(y));
        
        pino = new Circle(15);
        pino.setFill(Color.BLUE);
        gridPaneTabuleiro.add(pino, x-1, y-1);
    }

    @FXML
    private void comecaJogo(ActionEvent event) {
        
        
    }
    
}
