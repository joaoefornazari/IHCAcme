/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmetelas;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author joao
 */
public class IHCAcme extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        
        BorderPane root = FXMLLoader.load(getClass().getResource("AcmeLogin.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
