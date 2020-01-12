/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmetelas;

import acmebank.ProdutoData;
import acmebank.ReclamacaoData;
import ihcacme.Produto;
import ihcacme.Reclamacao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author joao
 */
public class AcmeSACController implements Initializable{
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private BorderPane realPane;
                
    @FXML
    private Button bVoltar;
    
    @FXML
    private Button bSair;
    
    
    public void handleButtonSair(ActionEvent e) throws IOException{
        
        realPane.getChildren().removeAll(realPane);
        System.exit(0);
        
    }
    
    public void handleButtonVoltar(ActionEvent e) throws IOException{
        
        Stage stage = (Stage) realPane.getParent().getScene().getWindow();
        //Scene scene = realPane.getParent().getScene(); 

        BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeInitial.fxml"));           
        realPane.getChildren().setAll(pane);

        stage.setTitle("Bem-vindo, Cliente!");

        stage.setHeight(pane.getPrefHeight() + 30);
        stage.setWidth(pane.getPrefWidth());     
        
    }
   
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
        
    }  
    
}
