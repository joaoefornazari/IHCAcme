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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author joao
 */
public class AcmeCadAdminController implements Initializable{
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private BorderPane realPane;
    
    @FXML
    private Button bCadastrar;
    
    @FXML
    private Button bVoltar;
    
    @FXML
    private TextField tfEmail;
    
    @FXML
    private TextField tfSenha;
    
    @FXML
    private ComboBox cbxAdmin;
    
    @FXML
    private Button bExcluir;
    
    private AdminData adminData;
    private Admin admin;
    private List<Admin> listAdmin;
    private int option;
    private String oldEmail;
    private String newEmail;
    //private String oldSenha;
    
    public List<Admin> readAdminList() throws SQLException, ClassNotFoundException{
        
        adminData = new AdminData("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/acme", "root", "root123!");
        
        listAdmin = adminData.getAdminList();
        
        adminData.shutdown();
        
        return listAdmin;
        
    }
    
    public void handleButtonCadastrar(ActionEvent e) throws IOException, SQLException, ClassNotFoundException{
        
        option = 0;
        
        adminData = new AdminData("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/acme", "root", "root123!");
        
        listAdmin.forEach((item)->{
                
            if(tfEmail.getText().equals(item.getEmail()))
            {    
                option = 1;
                oldEmail = item.getEmail();
            }
        });
        
        //System.out.println(tfSenha.getText());
        newEmail = tfEmail.getText();
        System.out.println(newEmail);      
        
        
        if(option == 0){
            System.out.println(option);
            this.adminData.createAdmin(newEmail, tfSenha.getText());
        }else
            adminData.updateAdmin(oldEmail, oldEmail, tfSenha.getText());
        
        cbxAdmin.getItems().add(tfEmail.getText());
        
        BorderPane root = FXMLLoader.load(getClass().getResource("/confirmtelas/AdminCadastrado.fxml"));             
                
        Stage stage2 = new Stage();

        Scene scene = new Scene(root);

        stage2.setScene(scene);
        stage2.setResizable(false);
        stage2.show();
        
        adminData = null;
        
    }
    
    public void handleButtonVoltar(ActionEvent e) throws IOException, SQLException{
        
        Stage stage = (Stage) realPane.getParent().getScene().getWindow();
        
        BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeAdmin.fxml"));
        realPane.getChildren().setAll(pane);
        
        stage.setHeight(pane.getPrefHeight());
        stage.setWidth(pane.getPrefWidth());

        realPane.getChildren().setAll(pane);        
        
        stage.setTitle("Bem-vindo, Admin!");
        
    }
    
    public void handleButtonExcluir(ActionEvent e) throws IOException, SQLException, ClassNotFoundException{
       
        adminData = new AdminData("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/acme", "root", "root123!");
        adminData.deleteAdmin(tfEmail.getText());
        
        int index = 0;        
        
        for(int i = 0; i < listAdmin.size(); i++){
            
            if(listAdmin.get(i) == cbxAdmin.getSelectionModel().getSelectedItem()){
                
                index = i;
                break;
            }                
            
        }        
        
        cbxAdmin.getItems().remove(index);
    }
    
    public void handleComboBoxAdmin(ActionEvent e) throws IOException{
       
        if(cbxAdmin.getSelectionModel().getSelectedItem() != null)
        {
            tfEmail.setText((String) cbxAdmin.getSelectionModel().getSelectedItem());
            bExcluir.setDisable(false);
            bCadastrar.setDisable(false);
        }//System.out.println(cbxAdmin.getSelectionModel().getSelectedItem());
        
    }
    
    public void handleTextFieldEmail(MouseEvent e) throws IOException{
       
        bExcluir.setDisable(false);
        bCadastrar.setDisable(false);
        
    }
    
    public void handleTextFieldSenha(ActionEvent e) throws IOException{
       
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            
            listAdmin = readAdminList();
            
            listAdmin.forEach((item)->{
            
                cbxAdmin.getItems().add(item.getEmail());
            
            });
            
            bExcluir.setDisable(true);
            bCadastrar.setDisable(true);
            
        } catch (SQLException ex) {
            
            System.out.println("Falha ao conectar ao banco de dados.");
       
        } catch (ClassNotFoundException ex) {
            
            System.out.println("Falha ao encontrar a classe do driver.");
            
        }
        
        
        
     
    }
    
}
