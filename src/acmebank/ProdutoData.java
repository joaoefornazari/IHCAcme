/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmebank;

import ihcacme.Produto;
import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.PreparedStatement;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;

import java.util.List ;
import java.util.ArrayList;

/**
 *
 * @author joao
 */
public class ProdutoData {
    
    private Connection con;
    private List<Produto> produtoList;
    private Produto produto;
    
    private String nomeProduto;
    
    public ProdutoData(String driverClassName, String dbURL, String admin, String senha) throws SQLException, ClassNotFoundException{
        
        try{        
        
            Class.forName(driverClassName);
            con = DriverManager.getConnection(dbURL, admin, senha);
        
        }catch(SQLException e){
            
            System.out.println("SQL Exception.");
            e.printStackTrace();
            
        }catch(ClassNotFoundException e){
            
            System.out.println("ClassNotFound Exception.");
            e.printStackTrace();
            
        }
        
    }
        
        /**
         *
         * @throws SQLException
         */
        public void shutdown() throws SQLException{

            if(con != null){
                
                con.close();
            }

        }
        
        public void setToEdit(String string){
            
            try{
            
                String query = "INSERT INTO toEdit (nome) VALUES (?);";
                PreparedStatement stmt = con.prepareStatement(query);

                stmt.setString(1, string);
                
                stmt.execute();
                
            }catch(SQLException e){
                
                e.printStackTrace();
                
            }
            
        }
        
        public void removeToEdit(String string){
            
            try{
            
                String query = "DELETE FROM toEdit WHERE nome = ?;";
                PreparedStatement stmt = con.prepareStatement(query);

                stmt.setString(1, string);
                
                stmt.execute();
                
            }catch(SQLException e){
                
                e.printStackTrace();
                
            }
            
        }
        
        public String getToEdit(){
            
            try{
            
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT nome FROM toEdit;");
                
                String string = null;
                
                while(rs.next()){
                    
                    string = rs.getString("nome");
                }
                
                return string;
                
            }catch(SQLException e){
                
                e.printStackTrace();
                return null;
            }
            
            
        }
        
        
        public List<Produto> getProdutoList() throws SQLException{
            
            try(
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM produto;");
            ){
                
                produtoList = new ArrayList<>();
                while(rs.next()){
                    
                    String idProduto = rs.getString("idProduto");
                    String nome = rs.getString("nome");
                    int quantidade = Integer.parseInt(rs.getString("qtde"));
                    double preco = Double.parseDouble(rs.getString("preco"));
                    produto = new Produto(idProduto, nome, quantidade, preco);
                    
                    produtoList.add(produto);                    
                }
                
            }
            
            return produtoList;
            
        }
        
        public Produto createProduto(String nome, int qtde, double preco) throws SQLException{
            
            produto = null;
            
            try{
                
                String query = "INSERT INTO produto (nome, qtde, preco) VALUES (?, ?, ?);";
                PreparedStatement stmt = con.prepareStatement(query);
                
                stmt.setString(1, nome);
                stmt.setInt(2, qtde);
                stmt.setDouble(3, preco);
                
                stmt.execute();
                
                produtoList = getProdutoList();
                
            }catch(SQLException e){
                
                System.out.println("Erro ao escrever no banco de dados.");
                e.printStackTrace();
                
            }
            
            return produto;
        }
        
        public void updateProduto(String nome, int qtde, int qtdeOld, double preco, double precoOld) throws SQLException{            
                    
            
            if(qtde != qtdeOld)
            {
                try{ 
                    
                    String query = "UPDATE produto SET qtde = ? WHERE nome = ?;";
                    PreparedStatement stmt = con.prepareStatement(query);
                    
                    stmt.setInt(1, qtde);
                    stmt.setString(2, nome);
                    
                    stmt.execute();
                    
                }catch(SQLException e){

                    System.out.println("Erro ao escrever no banco de dados.");

                }
                
            }
            
            if(preco != precoOld){
                
                try{                

                    String query = "UPDATE produto SET preco = ? WHERE nome = ?;";
                    PreparedStatement stmt = con.prepareStatement(query);
                    
                    stmt.setDouble(1, preco);
                    stmt.setString(2, nome);
                    
                    stmt.execute();              

                }catch(SQLException e){

                    System.out.println("Erro ao escrever no banco de dados.");

                }
            }
        }
        
        public void deleteProduto(String nome) throws SQLException{
            
            if(nome.contains("\'"))
                nome.replace("\'", "");
            
            try{
                
                String query = "DELETE FROM produto WHERE nome = ?;";
                PreparedStatement stmt = con.prepareStatement(query);

                stmt.setString(1, nome);

                stmt.execute();   
                
            }catch(SQLException e){
                
                e.printStackTrace();
                
            }
        }
        
        public Produto getProduto(String nome) throws SQLException{
            
            produto = null;
            
            try{
                
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM produto WHERE nome = \"" + nome + "\";");  
                
                while(rs.next())
                {
                    String idProduto = rs.getString("idProduto");
                    //String nome = rs.getString("nome");
                    int quantidade = Integer.parseInt(rs.getString("qtde"));
                    double preco = Double.parseDouble(rs.getString("preco"));
                    produto = new Produto(idProduto, nome, quantidade, preco);
                }
                
            }catch(SQLException e){
                
               e.printStackTrace();
            }
            
            return produto;
            
        }
        
        public Produto getProdutoById(String id) throws SQLException{
            
            produto = null;
            
            //try{
                
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM produto WHERE idProduto = " + id + ";");   
                
                while(rs.next()){
                    
                    String nome = rs.getString("nome");
                    //System.out.println(nome);
                    int quantidade = Integer.parseInt(rs.getString("qtde"));
                    double preco = Double.parseDouble(rs.getString("preco"));
                    produto = new Produto(id, nome, quantidade, preco);
                                   
                }
                
           // }catch(SQLException e){
                
               /* System.out.println("Erro ao escrever no banco de dados.");
                System.out.println(e.getErrorCode());
                System.out.println(e.getCause());*/
           // }
            
            return produto;
            
        }
    
}
