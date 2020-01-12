/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmetelas;

import acmebank.ReclamacaoData;
import ihcacme.Efeitos;
import ihcacme.Problema;
import ihcacme.Reclamacao;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author joao
 */
public class AcmeSolucaoController implements Initializable{
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private BorderPane realPane;
    
    @FXML
    private Label lSolucao;
    
    @FXML
    private Button bNovaReclamacao;
    
    @FXML
    private Button bMenu;
    
    private List<Reclamacao> reclamacaoList;
    private ReclamacaoData reclamacaoData;
    
    private Reclamacao reclamacao;
    
    public void LabelSolucao() throws SQLException, ClassNotFoundException {
        
        reclamacaoData = new ReclamacaoData("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/acme", "root", "root123!");
        
        reclamacaoList = reclamacaoData.getReclamacaoList();
        
        reclamacao = reclamacaoList.get(reclamacaoList.size() - 1);
        
        String problema = "";
        String efeitos = "";
        
        int tipoProblema = reclamacao.getProblemaNumber();
        int tipoEfeitos = reclamacao.getEfeitosNumber();
        
        System.out.println(tipoEfeitos);
        
        if(tipoProblema == 1){
            
            problema = "Vimos que seu problema foi com o tempo da entrega.\nIremos contatar nossos entregadores\n o mais rápido possível.";
            
        }
        
        if(tipoProblema == 2){
            
            problema = "Vimos que seu problema foi com produto defeituoso.\nIremos contatar nossos entregadores o mais rápido\n possível e dar uma bronca no Jorge, que é responsável pela vistoria.";
            
        }
        
        if(tipoProblema == 3){
            
            problema = "Vimos que seu problema foi que o produto veio errado\nIremos demitir um entregador e contratar um melhor.";
            
        }
        
        if(tipoProblema == 4){
            
            problema = "Vimos que seu problema foi bem peculiar...\nVocê tentou ligar para a gente?";
            
        }
        
        if(tipoEfeitos == 1){
            
            efeitos = "Por conta de seus ferimentos, estamos mandando\nassistência médica ACME\ne seu tratamento será por conta da empresa.";
            
        }
        
        if(tipoEfeitos == 2){
            
            efeitos = "Por conta de seu acidente, estamos mandando\nassistência médica ACME\ne esperamos que você tenha um seguro.";
            
        }
        
        if(tipoEfeitos == 3){
            
            efeitos = "Os produtos da ACME possuem garantia de três dias e duas horas.\nLigue para nossos atendentes se ainda estiver na garantia.";
            
        }
        
        if(tipoEfeitos == 4){
            
            efeitos = "Não conseguiu fazer o que queria? Iremos contatar quem escreveu o manual.\nA descrição do produto no site pode estar ruim também...";
            
        }
        
        if(tipoEfeitos == 5){
            
            efeitos = "O que aconteceu com você nos chocou.\nEstamos mandando um médico,\num advogado e um jornalista do Guinness.";
            
        }
            
        
        lSolucao.setText(problema + "\n" + efeitos);
        
    }
    
    public void handleButtonNovaReclamacao(ActionEvent e) throws IOException{
        
        try{
            
            Stage stage = (Stage) realPane.getParent().getScene().getWindow();
            //Scene scene = realPane.getParent().getScene(); 

            BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeReclam.fxml"));           
            realPane.getChildren().setAll(pane);

            stage.setTitle("Enviar Reclamação");

            stage.setHeight(pane.getPrefHeight());
            stage.setWidth(pane.getPrefWidth());  
            
        }catch (IOException ex){
            
            System.out.println("Erro ao migrar para a tela AcmeReclam.fxml.");
            System.out.println(ex);
        }
        
        
    }
    
    public void handleButtonMenuInicial(ActionEvent e) throws IOException{
        
        try{
            
            Stage stage = (Stage) realPane.getParent().getScene().getWindow();
            //Scene scene = realPane.getParent().getScene(); 
            
            BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeInitial.fxml"));
            realPane.getChildren().setAll(pane);
            
            stage.setTitle("Bem-vindo, Cliente!");

            stage.setHeight(pane.getPrefHeight() + 30);
            stage.setWidth(pane.getPrefWidth());
            
        }catch (IOException ex){
            
            System.out.println("Erro ao migrar para a tela AcmeInitial.fxml.");
            System.out.println(ex);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LabelSolucao();
        } catch (SQLException ex) {
            Logger.getLogger(AcmeSolucaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcmeSolucaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
}
