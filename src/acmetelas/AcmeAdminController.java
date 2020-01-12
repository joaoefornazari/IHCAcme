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
public class AcmeAdminController implements Initializable{
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private BorderPane realPane;
    
    @FXML
    private Button bCadProduto;
    
    @FXML
    private Button bCadAdmin;
    
    @FXML
    private Button bSair;
    
    public void handleButtonCadAdmin(ActionEvent e) throws IOException{
        
        try{
            
            Stage stage = (Stage) realPane.getParent().getScene().getWindow();
            //Scene scene = realPane.getParent().getScene(); 

            BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeCadAdmin.fxml"));
            
            stage.setHeight(pane.getPrefHeight());
            stage.setWidth(pane.getPrefWidth());
            
            realPane.getChildren().setAll(pane);

            stage.setTitle("Cadastrar Novo Administrador");

            
            
        }catch(IOException ex){
            
            System.out.println("IO - Erro ao migrar para a tela AcmeReclam.fxml.");
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
            
        }
        
    }
    
    public void handleButtonCadProduto(ActionEvent e) throws IOException{
        
       try{
            
            Stage stage = (Stage) realPane.getParent().getScene().getWindow();
            //Scene scene = realPane.getParent().getScene(); 

            BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeListaProdutos.fxml"));
            
            stage.setHeight(pane.getPrefHeight()+50);
            stage.setWidth(pane.getPrefWidth());
            
            realPane.getChildren().setAll(pane);

            stage.setTitle("Lista de Produtos");

            
            
        }catch(IOException ex){
            
            System.out.println("IO - Erro ao migrar para a tela AcmeListaProdutos.fxml.");
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
            
        }
        
    }
    
    public void handleButtonSair(ActionEvent e) throws IOException{
        
        realPane.getChildren().removeAll(realPane);
        System.exit(0);
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
}
