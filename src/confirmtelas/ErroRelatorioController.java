/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package confirmtelas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author joao
 */
public class ErroRelatorioController implements Initializable{
    
    @FXML
    private BorderPane rootPane;
    
    @FXML
    private Button bOK;
    
    public void handleButtonOK(ActionEvent e) throws IOException{
        
        Window window = rootPane.getScene().getWindow();
        window.hide();
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
}
