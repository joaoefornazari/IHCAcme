/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmetelas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author joao
 */
public class AcmeInitialController implements Initializable{
    
    @FXML
    private BorderPane rootPane;
    
    @FXML
    private Button bReclamacao;
         
    @FXML
    private Button bLigacao;
    
    @FXML
    private Button bSair;
    
    public void handleButtonLigacao(ActionEvent e) throws IOException{
        
        try{
            
           Stage stage = (Stage) rootPane.getParent().getScene().getWindow();
           //Scene scene = rootPane.getParent().getScene(); 
                       
           BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeSAC.fxml"));           
           rootPane.getChildren().setAll(pane);
           
           stage.setTitle("Realizar Ligação");

           stage.setHeight(pane.getPrefHeight() + 30);
           stage.setWidth(pane.getPrefWidth());
            
        }catch (IOException ex){
            
            System.out.println("IO - Erro ao migrar para a tela AcmeReclam.fxml.");
            System.out.println(ex);
            System.out.println(ex.getCause());
            
        }
        
    }
    
    public void handleButtonReclamacao(ActionEvent e) throws IOException{
               
        try{     
            Stage stage = (Stage) rootPane.getParent().getScene().getWindow();
            //Scene scene = rootPane.getParent().getScene();

            BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeReclam.fxml"));
            stage.setTitle("Enviar Reclamação");

            stage.setHeight(pane.getPrefHeight());
            stage.setWidth(pane.getPrefWidth());

            rootPane.getChildren().setAll(pane);
        
        }catch(IOException ex){
            
            ex.printStackTrace();
            
        }      
        
    }
    
    public void handleButtonSair(ActionEvent e) throws IOException{
        
        rootPane.getChildren().removeAll(rootPane);
        System.exit(0);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*Stage stage = (Stage) rootPane.getParent().getScene().getWindow();
        
        stage.setTitle("Bem-vindo, Cliente!");
        stage.setMinHeight(rootPane.getPrefHeight());
        stage.setMinWidth(rootPane.getPrefWidth());*/
    }  
    
}
