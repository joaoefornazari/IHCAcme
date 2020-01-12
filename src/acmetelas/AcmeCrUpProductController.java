/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmetelas;

import acmebank.ProdutoData;
import ihcacme.Produto;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

/**
 *
 * @author joao
 */
public class AcmeCrUpProductController implements Initializable{  
    
    @FXML
    private BorderPane realPane;
    
    @FXML
    private TextField tfNome;
    
    @FXML
    private ComboBox cbQuant;
    
    @FXML
    private Button bVoltar;
    
    @FXML
    private Button bSalvar;
    
    @FXML
    private Button bLimpar;
    
    @FXML
    private TextField tfPreco;
    
    private Produto produto;
    private ProdutoData produtoData;
    private String string;

    public void handleButtonLimpar(ActionEvent e) throws IOException{
        
        tfPreco.setText("");
        tfNome.setText("");
        
    }    
    
    public void handleButtonSalvar(ActionEvent e) throws IOException, SQLException, ClassNotFoundException{
        
        produtoData = new ProdutoData("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/acme", "root", "root123!");
        
        int num = Integer.parseInt(cbQuant.getSelectionModel().getSelectedItem().toString());      
        
        String precoB = tfPreco.getText();
        
        if(precoB.matches(".*[a-zA-Z].*")){
            
            BorderPane root = FXMLLoader.load(getClass().getResource("/confirmtelas/ErroRelatorio.fxml"));             
                
            Stage stage2 = new Stage();

            Scene scene = new Scene(root);

            stage2.setScene(scene);
            stage2.setResizable(false);
            stage2.show();
            
            return;
            
        }
        
        String[] precoA = precoB.split(",");        
        
        double preco = Double.parseDouble(precoA[0]) + (Double.parseDouble(precoA[1]) / 100);      
        
        try{
            
            if(string == null){
                
                produto = produtoData.createProduto(tfNome.getText(), num, preco);
                
            }else{
                
                produto = produtoData.getProduto(tfNome.getText());               
                produtoData.updateProduto(tfNome.getText(), num, produto.getQtde(), preco, produto.getPreco());
                produtoData.removeToEdit(string);
            }
            
        }catch(SQLException ex){
            
            ex.printStackTrace();
        }
        
        BorderPane root = FXMLLoader.load(getClass().getResource("/confirmtelas/ProdutoCadastrado.fxml"));             
                
        Stage stage2 = new Stage();

        Scene scene = new Scene(root);

        stage2.setScene(scene);
        stage2.setResizable(false);
        stage2.show();
        
        if(produto == null){
            
            System.out.println("Deu ruim.");
        }
    }
    
    public void handleButtonVoltar(ActionEvent e) throws IOException{
        
        if(string != null)
            produtoData.removeToEdit(string);
        
        Stage stage = (Stage) realPane.getParent().getScene().getWindow();
        //Scene scene = realPane.getParent().getScene();
        
        BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeListaProdutos.fxml"));         
        realPane.getChildren().setAll(pane);
        
        stage.setTitle("Lista de Produtos");

        stage.setHeight(pane.getPrefHeight()+50);
        stage.setWidth(pane.getPrefWidth());
       
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            if(produtoData == null)
                produtoData = new ProdutoData("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/acme", "root", "root123!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        string = produtoData.getToEdit();
        
        if(!(string == null))
            this.tfNome.setText(string);
        
        for(int i = 1; i < 100; i++){
            
            cbQuant.getItems().add(i);
        }
        
        
    }  
    
}
