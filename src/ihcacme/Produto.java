/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihcacme;

/**
 *
 * @author joao
 */
public class Produto {

    /**
     * @return the idProduto
     */
    public String getIdProduto() {
        return idProduto;
    }

    /**
     * @param idProduto the idProduto to set
     */
    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }
    
    private String nome;
    private int qtde;
    private double preco;
    private String idProduto;

    public Produto(String idProduto, String nome, int quantidade, double preco) {
        
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;    
        this.qtde = quantidade;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the qtde
     */
    public int getQtde() {
        return qtde;
    }

    /**
     * @param qtde the qtde to set
     */
    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    /**
     * @return the preço
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preço the preço to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
    
}