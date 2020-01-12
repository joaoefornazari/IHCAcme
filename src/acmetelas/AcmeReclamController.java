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
import java.time.MonthDay;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author joao
 */
public class AcmeReclamController implements Initializable{
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private BorderPane realPane;
    
    @FXML
    private TextField tfEmail;
    
    @FXML
    private TextField tfTelefone;
    
    @FXML
    private ComboBox cbxProduto;
    
    @FXML
    private DatePicker dpDataCompra;
    
    @FXML
    private DatePicker dpDataReclam;
    
    
    @FXML
    private CheckBox cbFerimentos;
    
    @FXML
    private CheckBox cbAcidentes;
    
    @FXML
    private CheckBox cbDestruicao;
    
    @FXML
    private CheckBox cbFinalidade;
    
    @FXML
    private CheckBox cbOutroE;
    
    ////////////////////////////
    
    @FXML
    private CheckBox cbAtraso;
    
    @FXML
    private CheckBox cbDefeito;
    
    @FXML
    private CheckBox cbErro;
    
    @FXML
    private CheckBox cbOutroN;
    
    @FXML
    private TextField tfOutroN;
    
    ////////////////////////////
    
    @FXML
    private TextField tfOutroE;
    
    @FXML
    private Button bEnviar;
    
    @FXML
    private Button bVoltar;
    
    private ReclamacaoData reclamacaoData;
    private Reclamacao reclamacao;
    private List<Reclamacao> reclamacaoList;
    
    private Produto produto;
    
    public List<Produto> listaProduto;
    public ProdutoData produtoData;
    
    private int effect;
    private int problem;
    private int verification;
    private int tf = 0;
    
    
    public List<Produto> readListaProduto() throws SQLException, ClassNotFoundException{
        
        produtoData = new ProdutoData("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/acme", "root", "root123!");
        
        listaProduto = produtoData.getProdutoList();
        
        //produtoData.shutdown();
        
        return listaProduto;
    }   
    
    
    public Reclamacao registerReclam() throws SQLException, ClassNotFoundException{
        
        reclamacao = null;        
        reclamacaoData = new ReclamacaoData("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/acme", "root", "root123!");
        
        String email = tfEmail.getText();
        String telefone = tfTelefone.getText();
        
        String problema = tfOutroN.getText();
        String efeitos = tfOutroE.getText();
        
        LocalDate localdate = dpDataCompra.getValue();
        //Instant instant = Instant.from(localdate.atStartOfDay(ZoneId.systemDefault()));
        Date data_compra = (Date) Date.valueOf(localdate);
        
        localdate = dpDataReclam.getValue();
        //instant = Instant.from(localdate.atStartOfDay(ZoneId.systemDefault()));
        Date data_reclam = (Date) Date.valueOf(localdate);
        
        String nome_produto = (String) cbxProduto.getSelectionModel().getSelectedItem();
        
        listaProduto = produtoData.getProdutoList();
        
        listaProduto.forEach((item)->{
                
            if(item.getNome().equals(nome_produto))
                produto = item;              
                
        });
        
        String idProduto = produto.getIdProduto();
        
        int problema_cat = getProblem();
        System.out.println(problema_cat);
        int efeitos_cat = getEffect();
        System.out.println(efeitos_cat);
        
        reclamacao = reclamacaoData.createReclamacao(email, problema, efeitos, problema_cat, telefone, data_compra, data_reclam, efeitos_cat, idProduto);
        
        System.out.println(reclamacao.getEfeitosNumber());
        return reclamacao;
        
        
    }
    
    public int verifyFields(){
        
        //String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        
        if(!tfEmail.getText().contains("@"))
            return 1;
        
        if(cbAtraso.isDisabled() && cbErro.isDisabled() && cbDefeito.isDisabled() && cbOutroN.isDisabled())
            return 2;
        
        if(cbAcidentes.isDisabled() && cbFerimentos.isDisabled() && cbFinalidade.isDisabled() && cbDestruicao.isDisabled() && cbOutroE.isDisabled())
            return 3;
        
        if(dpDataCompra.getValue() == null)
            return 4;
        
        if(dpDataReclam.getValue() == null)
            return 5;
        
        if(cbxProduto.getSelectionModel().getSelectedItem() == null)
            return 6;
        
        if(tfEmail.getText().equals(""))
            return 7;
        
        if(tfTelefone.getText().equals(""))
            return 8;
        
        if(tfTelefone.getText().matches(".*[a-zA-Z].*"))
            return 9;
        
        
        return 0;
        
    }
    
    
    public void handleButtonEnviar(ActionEvent e) throws IOException, SQLException, ClassNotFoundException{  
                
       try{    
           
            verification = verifyFields();
           
            if(verification == 0)
            {
                if(registerReclam() != null)
                {
                    try{

                        BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeSolucao.fxml"));

                        Stage stage = (Stage) realPane.getParent().getScene().getWindow();
                        //Scene scene = realPane.getParent().getScene();

                        stage.setTitle("Sugestões");

                        stage.setHeight(pane.getPrefHeight() + 30);
                        stage.setWidth(pane.getPrefWidth());

                        realPane.getChildren().setAll(pane);

                    }catch(IOException ex){

                        ex.printStackTrace();

                    }

                }
                
            }else{
                
                BorderPane root = FXMLLoader.load(getClass().getResource("/confirmtelas/ErroRelatorio.fxml"));             
                
                Stage stage2 = new Stage();
        
                Scene scene = new Scene(root);

                stage2.setScene(scene);
                stage2.setResizable(false);
                stage2.show();
                
            }
            
       }catch(ClassNotFoundException ce){
           
           ce.printStackTrace();
       }
        
    }
    
    public void handleComboBoxProduto(ActionEvent e) throws IOException{
        
       // TO-DO
        
    }
    
    public void handleTextFieldEmail(ActionEvent e) throws IOException{
        
       // TO-DO
        
    }
    
    public void handleTextFieldTelefone(MouseEvent e) throws IOException{
        
        if(tf == 0){
            tfTelefone.setText("");
            tf++;
        }
            
    }
    
    public void handleDatePickerDataCompra(ActionEvent e) throws IOException{
        
       // TO-DO
        
    }
    
    public void handleDatePickerDataReclam(ActionEvent e) throws IOException{
        
       // TO-DO
        
    }
    
    public void handleButtonVoltar(ActionEvent e) throws IOException{      
        
        
        BorderPane pane = FXMLLoader.load(getClass().getResource("AcmeInitial.fxml"));
        
        Stage stage = (Stage) realPane.getParent().getScene().getWindow();
        //Scene scene = realPane.getParent().getScene();
        
        stage.setTitle("Bem-vindo, Cliente!");
           
        stage.setHeight(pane.getPrefHeight() + 30);
        stage.setWidth(pane.getPrefWidth());
           
        realPane.getChildren().setAll(pane);
        
    }
    
    public void handleCheckBoxAtraso(ActionEvent e) throws IOException{
        
       setProblem(1);
        
    }
    
    public void handleCheckBoxDefeito(ActionEvent e) throws IOException{
        
       setProblem(2);
        
    }
    
    public void handleCheckBoxErro(ActionEvent e) throws IOException{
        
       setProblem(3);
        
    }
    
    public void handleCheckBoxOutroN(ActionEvent e) throws IOException{
        
       setProblem(4);
        
    }
    
    public void handleCheckBoxOutroE(ActionEvent e) throws IOException{
        
       setEffect(5);
        
    }
    
    public void handleCheckBoxFerimentos(ActionEvent e) throws IOException{
        
       setEffect(3);
        
    }
    
    public void handleCheckBoxAcidentes(ActionEvent e) throws IOException{
        
       setEffect(1);
        
    }
    
    public void handleCheckBoxDestruicao(ActionEvent e) throws IOException{
        
       setEffect(2);
        
    }
    
    public void handleCheckBoxFinalidade(ActionEvent e) throws IOException{
        
       setEffect(4);
        
    }    
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            
            List<Produto> lp = readListaProduto();
            
            lp.forEach((item)->{
            
                cbxProduto.getItems().add(item.getNome());
            
            });
            
            Callback<DatePicker, javafx.scene.control.DateCell> dayCellFactory = (final DatePicker datePicker) -> {
                return new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isAfter(LocalDate.now())) {
                            // Tomorrow is too soon.
                            setDisable(true);
                        }
                    }
                };
            };
            dpDataCompra.setDayCellFactory(dayCellFactory);
            dpDataReclam.setValue(LocalDate.now());
            dpDataReclam.setDisable(true);
            
            tfTelefone.setText("Digite apenas números e informe o DDD.");
            
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
       
        } catch (ClassNotFoundException ex) {
            
            ex.printStackTrace();
            
        }
        
        
    }  

    /**
     * @return the effect
     */
    public int getEffect() {
        return effect;
    }

    /**
     * @param effect the effect to set
     */
    public void setEffect(int effect) {
        this.effect = effect;
    }

    /**
     * @return the problem
     */
    public int getProblem() {
        return problem;
    }

    /**
     * @param problem the problem to set
     */
    public void setProblem(int problem) {
        this.problem = problem;
    }
    
}
