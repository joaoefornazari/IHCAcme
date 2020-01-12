/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmetelas;

import acmebank.AdminData;
import ihcacme.Admin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import java.sql.*;
import java.util.List;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author joao
 */
public class AcmeLoginController implements Initializable{
    
    @FXML
    private CheckBox cbAdmin;

    @FXML
    private Button bLogin;

    @FXML
    private Button bLoginCliente;

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfSenha;
    
    @FXML
    private BorderPane realPane;
    
    private List<Admin> adminList;    
    private AdminData admData;
    private Admin adm;


    public Admin connectDatabase() throws SQLException, ClassNotFoundException{
        
        adm = null;

        admData = new AdminData("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/acme", "root", "root123!");

        adminList = admData.getAdminList();

        String email = tfEmail.getText();
        String senha = tfSenha.getText();

        adminList.forEach((item) -> {

            if(email.equals(item.getEmail())){

                if(senha.equals(item.getSenha())){

                    adm = new Admin(email, senha);

                }

            }          

        });
        
        if(adm == null){
            
            try{
            
                BorderPane root = FXMLLoader.load(getClass().getResource("/confirmtelas/LoginIncorrect.fxml"));             

                Stage stage2 = new Stage();

                Scene scene = new Scene(root);

                stage2.setScene(scene);
                stage2.setResizable(false);
                stage2.show();
            
            }catch(IOException ex){
                
                ex.printStackTrace();
                
            }
            
        }
            
        return adm;
        
    }
    
    
    public void handleCheckBox(ActionEvent e){

        
    }
    
    public void handleTextEmail(ActionEvent e){
        
        
        
    }    
    
    public void handleTextSenha(ActionEvent e){
        
        
        
    }
    
    public void handleButtonLogin(ActionEvent e) throws IOException, SQLException, ClassNotFoundException{
        
       if(connectDatabase() == null){
           
          //System.out.println("Usuario ou senha incorretos!");
           
       }else{
           
            //Stage stage = (Stage) realPane.getParent().getScene().getWindow();
            //Scene scene = realPane.getParent().getScene(); 

            BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeAdmin.fxml"));
            admData.shutdown();
            realPane.getChildren().setAll(pane);

            //tage.setTitle("Bem-vindo, Admin!");

            //stage.setMinHeight(pane.getPrefHeight());
            //stage.setMinWidth(pane.getPrefWidth());    
            
       
       }
        
    }

    public void handleButtonCliente(ActionEvent e) throws IOException, SQLException{
        
        //Stage stage = (Stage) realPane.getParent().getScene().getWindow();
       // Scene scene = realPane.getParent().getScene(); 

        BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeInitial.fxml"));
        //admData.shutdown();
        realPane.getChildren().setAll(pane);

        //stage.setTitle("Bem-vindo, Cliente!");

       // stage.setMinHeight(pane.getPrefHeight());
        //stage.setMinWidth(pane.getPrefWidth());
    }    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        
    }
    
}
