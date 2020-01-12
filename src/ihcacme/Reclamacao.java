/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihcacme;

import acmebank.ProdutoData;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author joao
 */
public class Reclamacao {

    private String email_cliente;
    private String telefone;
    private Problema problema; //combo box
    private Date data_compra;
    private Date data_reclamacao;
    private Efeitos efeitos;
    
    private Produto produto;
    private ProdutoData produtoData;

    public Reclamacao(String emailC, String problema, String efeitos, int problema_cat, String telefone, Date data_compra, Date data_reclam, int efeitos_cat, String idProduto) throws SQLException, ClassNotFoundException {
        
        this.email_cliente = emailC;
        this.telefone = telefone;
        this.data_compra = (Date) data_compra;
        this.data_reclamacao = (Date) data_reclam;
        
        switch(problema_cat){
            
            case 1:
            {
                this.problema = Problema.ATRASO;
            }break;
            
            case 2:
            {
                this.problema = Problema.DEFEITO;
            }break;
            
            case 3:
            {
                this.problema = Problema.ERRO;
            }break;
            
            case 4:
            {
                this.problema = Problema.OUTRO;
            }break;
        }
        
        switch(efeitos_cat){
            
            case 1:
            {
                this.efeitos = Efeitos.ACIDENTES;
            }break;
            
            case 2:
            {
                this.efeitos = Efeitos.DESTRUIÇÃO;
            }break;
            
            case 3:
            {
                this.efeitos = Efeitos.FERIMENTOS;
            }break;
            
            case 4:
            {
                this.efeitos = Efeitos.FINALIDADE;
            }break;
            
            case 5:
            {
                this.efeitos = Efeitos.OUTRO;
            }break;
                
               
        }       
        
        produtoData = new ProdutoData("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/acme", "root", "root123!");
        produto = produtoData.getProdutoById(idProduto);
    }

    /**
     * @return the email_cliente
     */
    public String getEmail_cliente() {
        return email_cliente;
    }

    /**
     * @param email_cliente the email_cliente to set
     */
    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

   

    /**
     * @return the data_compra
     */
    public Date getData_compra() {
        return data_compra;
    }

    /**
     * @param data_compra the data_compra to set
     */
    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

    /**
     * @return the data_reclamacao
     */
    public Date getData_reclamação() {
        return data_reclamacao;
    }

    /**
     * @param data_reclamacao
     */
    public void setData_reclamacao(Date data_reclamacao) {
        this.data_reclamacao = data_reclamacao;
    }

    /**
     * @return the Problema
     */
    public Problema getProblema() {
        return problema;
    }

    /**
     */
    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    /**
     * @return the Efeitos
     */
    public Efeitos getEfeitos() {
        return efeitos;
    }

    /**
     * @param efeitos the Efeitos to set
     */
    public void setEfeitos(Efeitos efeitos) {
        this.efeitos = efeitos;
    }
    
    public int getProblemaNumber(){
        
        if(problema == Problema.ATRASO){
            
            return 1;
            
        }else if(problema == Problema.DEFEITO){
            
            return 2;
            
        }else if(problema == Problema.ERRO){
            
            return 3;
            
        }else if(problema == Problema.OUTRO){
            
            return 4;
            
        }else{
            
            return 0;
            
        }           
        
    }
    
    public int getEfeitosNumber(){
        
        if(efeitos == Efeitos.ACIDENTES){
            
            return 1;
            
        }else if(efeitos == Efeitos.DESTRUIÇÃO){
            
            return 2;
            
        }else if(efeitos == Efeitos.FERIMENTOS){
            
            return 3;
            
        }else if(efeitos == Efeitos.FINALIDADE){
            
            return 4;
            
        }else if(efeitos == Efeitos.OUTRO){
            
            return 5;
            
        }else{
            
            return 0;
            
        }           
        
    }

    
    
}
