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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author joao
 */public class AcmeListaProdutosController implements Initializable{
    
    @FXML
    private BorderPane realPane;
    
    @FXML
    private ComboBox cbLista;
    
    @FXML
    private Button bCadastrar;
    
    @FXML
    private Button bExcluir;
    
    @FXML
    private Button bVoltar;
    
    @FXML
    private Button bEditar;
    
    private Produto produto;
    
    private List<Produto> listaProduto;
    private ProdutoData produtoData;
    
    public List<Produto> readListaProduto() throws SQLException, ClassNotFoundException{
        
        produtoData = new ProdutoData("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/acme", "root", "root123!");
        
        listaProduto = produtoData.getProdutoList();
        
       //produtoData.shutdown();
        
        return listaProduto;
    }
    
    public void handleButtonCadastrar(ActionEvent e) throws IOException, SQLException{
        
        try{
            
            produtoData.shutdown();
            
            Stage stage = (Stage) realPane.getParent().getScene().getWindow();
            //Scene scene = realPane.getParent().getScene(); 
            
            BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeCrUpProduct.fxml"));
            realPane.getChildren().setAll(pane);
            
            stage.setTitle("Cadastrar Produto");

            stage.setHeight(pane.getPrefHeight());
            stage.setWidth(pane.getPrefWidth());
            
        }catch(IOException ex){
            
            ex.printStackTrace();
            
        }         
        
    }
    
    public void handleButtonExcluir(ActionEvent e) throws IOException, SQLException, ClassNotFoundException{
        
        produtoData = new ProdutoData("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/acme", "root", "root123!");        
        
        String nome = cbLista.getSelectionModel().getSelectedItem().toString();
        
        System.out.println(nome);
        
        int index = 0;
        
        produtoData.deleteProduto(nome);
        
        for(int i = 0; i < listaProduto.size(); i++){
            
            if(listaProduto.get(i) == cbLista.getSelectionModel().getSelectedItem()){
                
                index = i;
                break;
            }
                
            
        }
        
        
        cbLista.getItems().remove(index);
        
        //produtoData.shutdown();
    }
    
    public void handleButtonVoltar(ActionEvent e) throws IOException, SQLException{
        
        try{
            produtoData.shutdown();
            
            Stage stage = (Stage) realPane.getParent().getScene().getWindow();
            //Scene scene = realPane.getParent().getScene(); 

            BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeAdmin.fxml"));
            realPane.getChildren().setAll(pane);

            stage.setTitle("Bem-vindo, Administrador!");

            stage.setHeight(pane.getPrefHeight());
            stage.setWidth(pane.getPrefWidth());
            
        }catch(IOException ex){
            
            ex.printStackTrace();
            
        }
        
    }
    
    public void handleButtonEditar(ActionEvent e) throws IOException, SQLException{
        
        try{
            
            produtoData.setToEdit(cbLista.getSelectionModel().getSelectedItem().toString());
            
            //produtoData.shutdown();
            
            Stage stage = (Stage) realPane.getParent().getScene().getWindow();
            //Scene scene = realPane.getParent().getScene(); 
            
            BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeCrUpProduct.fxml"));
            realPane.getChildren().setAll(pane);
            
            //pane.;
            
            stage.setTitle("Editar Produto");

            stage.setHeight(pane.getPrefHeight());
            stage.setWidth(pane.getPrefWidth());
            
        }catch(IOException ex){
            
            ex.printStackTrace();
            
        } 
        
        
    }
    
    public void handleComboBoxLista(ActionEvent e) throws IOException{
        
        if(cbLista.getSelectionModel().getSelectedItem() != null)
        {            
            bExcluir.setDisable(false);
            bEditar.setDisable(false);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            
            List<Produto> lp = readListaProduto();
            
            lp.forEach((item)->{
                
                cbLista.getItems().add(item.getNome());
                
            });
            
            this.bExcluir.setDisable(true);           
            this.bEditar.setDisable(true);
            
        } catch (SQLException ex) {
            
            System.out.println("Falha ao conectar ao banco de dados.");
       
        } catch (ClassNotFoundException ex) {
            
            System.out.println("Falha ao encontrar a classe do driver.");
            
        }
        
    }  
    
}
