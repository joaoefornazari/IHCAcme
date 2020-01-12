/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmebank;

import ihcacme.Reclamacao;
import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;

import java.util.List ;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author joao
 */
public class ReclamacaoData {
    
    private Connection con;
    private List<Reclamacao> ReclamacaoList;
    
    public ReclamacaoData(String driverClassName, String dbURL, String admin, String senha) throws SQLException, ClassNotFoundException{
        
        try{
        
            Class.forName(driverClassName);
            
            if(con == null)
                con = DriverManager.getConnection(dbURL, admin, senha);
        
        }catch(SQLException e){
            
            System.out.println("SQL Exception.");
            System.out.println(e.getErrorCode());
            System.out.println(e.getCause());
            
            
        }catch(ClassNotFoundException e){
            
            System.out.println("ClassNotFound Exception.");
            System.out.println(e.getException());
            System.out.println(e.getCause());
            
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
        
        
        public List<Reclamacao> getReclamacaoList() throws SQLException, ClassNotFoundException{
            
            try(
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM reclamacao;");
            ){
                
                ReclamacaoList = new ArrayList<>();
                while(rs.next()){
                    
                    String emailC = rs.getString("email_cliente");
                    String problema = rs.getString("problem");
                   
                    String telefone = rs.getString("telefone");
                    Date data_compra = rs.getDate("data_compra");
                    Date data_reclam = rs.getDate("data_reclamacao");
                    
                    String efeitos = rs.getString("efeitos");
                    String idProduto = rs.getString("idProduto");
                    int problema_cat = rs.getInt("problema_cat");
                    int efeitos_cat = rs.getInt("efeito_cat");
                    
                    Reclamacao reclamacao = new Reclamacao(emailC, problema, efeitos, problema_cat, telefone, data_compra, data_reclam, efeitos_cat, idProduto);
                    
                    ReclamacaoList.add(reclamacao);                  
                }
                
            }
            
            return ReclamacaoList;
            
        }
        
        public Reclamacao createReclamacao(String emailC, String problema, String efeitos, int problema_cat, String telefone, Date data_compra, Date data_reclam, int efeitos_cat, String idProduto) throws SQLException, ClassNotFoundException{
            
            Reclamacao reclamacao = null;          
            
            //System.out.println(data_compra);
            
            
            
            String query = "INSERT INTO reclamacao (email_cliente, problem, telefone, data_compra, data_reclamacao, efeitos, idProduto, efeito_cat, problema_cat)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            //String d_cmp = data_compra.toString();
                
            PreparedStatement stmt = con.prepareStatement(query);
            
            reclamacao = new Reclamacao(emailC, problema, efeitos, problema_cat, telefone, data_compra, data_reclam, efeitos_cat, idProduto);
            
            stmt.setString(1, emailC);
            stmt.setString(2, problema);
            stmt.setString(3, telefone);
            stmt.setDate(4, data_compra);
            stmt.setDate(5, data_reclam);
            stmt.setString(6, efeitos);
            stmt.setInt(7, Integer.parseInt(idProduto));           
            stmt.setInt(8, problema_cat);
            stmt.setInt(9, efeitos_cat);
            
            stmt.execute();         
            
            ReclamacaoList = getReclamacaoList();
            ReclamacaoList.add(reclamacao);
                
            
            
            return reclamacao;
        }
    
}
