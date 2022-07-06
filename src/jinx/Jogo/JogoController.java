/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Jogo;

import static java.lang.String.valueOf;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle; 
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import jinx.Client.Client;
import jinx.Server.ClientHandler;
import jinx.Server.Jogo.Movimento;

/**
 * FXML Controller class
 *
 * @author fraba
 */
public class JogoController implements Initializable {

    private Client client;
    private Thread readObj;
    private Object object;
    private int ID;
    private Circle pino;
    
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
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    }    

    @FXML
    private void lancarDados(ActionEvent event) {        
        int x = (int)(Math.random() * 5);  // 1 to 6
        int y = (int)(Math.random() * 5);  // 1 to 6
        
        
        client.sendToServer("dados#" + x + "$" + y);
        lbl_dado1.setText(valueOf(x + 1));
        lbl_dado2.setText(valueOf(y + 1));
        
        
        
        /*
        pino = new Circle(15);
        pino.setFill(Color.BLUE);
        gridPaneTabuleiro.add(pino, x, y);
        */

    }

    @FXML
    private void comecaJogo(ActionEvent event) {

        client.sendToServer("game#start");
    }
  
    public void setValues(String ip, int porta, String nome) throws IOException {
        client = new Client(ip, porta);
        startReadObj();
        client.sendToServer("nome#" + nome);
    }

    private void startReadObj() {
        readObj = new Thread(() -> {
            while(true){
                try {
                object = client.getObjIn().readObject();
                if(object instanceof String){
                    readString(object);
                } else if(object instanceof ArrayList) {
                    atualizaJogadores((ArrayList<ClientHandler>) object);
                } else if(object instanceof Movimento){
                    desenhaTabuleiro((Movimento) object);
                }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(JogoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        readObj.start();

    }
    
    private void readString(Object object){
        StringTokenizer st = new StringTokenizer((String) object, "#");
        String comando = st.nextToken();
        String acao = st.nextToken();
        
        switch(comando){
            case "id":
                ID = Integer.parseInt(acao);
                System.out.println("ID: " + ID);
                break;
            case "game":
                if("start".equals(acao)){
                    System.out.println("comecar jogo");
                    btn_comecaJogo.setVisible(false);
                }
                else if("end".equals(acao)){
                    //fecha ligaçao
                }
        }
    }

    private void desenhaTabuleiro(Movimento movimento) {
        System.out.println(movimento.mensagem);
    }

    private void atualizaJogadores(ArrayList<ClientHandler> jogadores) {
        
        System.out.println("atualiza jogadores");
    }
}
