/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinx.Jogo;

import java.awt.Color;
import static java.lang.String.valueOf;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle; 
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import jinx.Client.Client;
import jinx.Server.*;
import jinx.Server.Jogo.*;

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
    ArrayList<Movimento> movimentos =  new ArrayList<>();
    
    @FXML
    private ImageView img_tabuleiro;
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
    @FXML
    private ImageView img_titulo;
    @FXML
    private TableView<?> jogadores;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    }    

    @FXML
    private void lancarDados(ActionEvent event) {        
        int x = (int)(Math.random() * 6);  // 1 to 6
        int y = (int)(Math.random() * 6);  // 1 to 6
        
        
        client.sendToServer("dados#" + x + "$" + y);
        lbl_dado1.setText(valueOf(x + 1));
        lbl_dado2.setText(valueOf(y + 1));
        
        /*
        Circle pino = new Circle(15);
        pino.setFill(javafx.scene.paint.Color.BLUE);
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
                        //Platform.runLater(()->{atualizaJogadores((ArrayList<ClientHandler>) object);});
                        
                    } else if (object instanceof Movimento) {
                        
                        Platform.runLater(() -> {
                            movimentos.add((Movimento) object);
                            pintaCasa();
                        });
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
            case "info":
                System.out.println(acao);
                break;
            case "jogador":
                atualizaBotoes(Integer.parseInt(acao));
        }
    }
    
    private void pintaCasa() {
        for(Movimento movimento: movimentos){
            int x = movimento.x;
            int y = movimento.y;    
            if("colocar".equals(movimento.acao)){
                Color corCasa = movimento.cor;
                Circle pino = new Circle(15);
                int r = corCasa.getRed();
                int g = corCasa.getGreen();
                int b = corCasa.getBlue();
                int a = corCasa.getAlpha();
                double opacity = a / 255.0 ;
                javafx.scene.paint.Color cor = javafx.scene.paint.Color.rgb(r, g, b, opacity);
                pino.setFill(cor);
                gridPaneTabuleiro.add(pino, x, y);
                System.out.println("Adiciona: dado 1: " + x + " dado 2: " + y);
                movimentos.remove(movimento);
            }
            else if("remover".equals(movimento.acao)){
                Iterator<Node> it = gridPaneTabuleiro.getChildren().iterator();
                while(it.hasNext()) {
                    Node pino = it.next();
                    if(GridPane.getRowIndex(pino) == movimento.x && GridPane.getColumnIndex(pino) == movimento.y) {
                        it.remove();
                        System.out.println("Remove: dado 1: " + x + " dado 2: " + y);
                    }
                }
                /*
                for(Node pino: gridPaneTabuleiro.getChildren()){
                    if(GridPane.getRowIndex(pino) == movimento.y && GridPane.getColumnIndex(pino) == movimento.x){
                        gridPaneTabuleiro.getChildren().remove(pino);
                    }
                }
    */
                movimentos.remove(movimento);
            }
        }
        
    }
    /*
    private ObservableList<String> atualizaJogadores(ArrayList<ClientHandler> jogadores) {
        for(ClientHandler cl: jogadores){
            tbl_nome.setText();
        }
    }*/

    private void tabuleiro(Object object) {
        System.out.println("tabuleiro recebido");
    }

    private void atualizaBotoes(int atual) {
        if(atual == ID){
            btn_lancarDados.setVisible(true);
        } else {
            btn_lancarDados.setVisible(false);
        }
    }
}
