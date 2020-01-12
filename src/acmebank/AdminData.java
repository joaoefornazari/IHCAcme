/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmebank;

import ihcacme.Admin;
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
public class AdminData {
    
    private Connection con;
    private List<Admin> adminList;
    
    public AdminData(String driverClassName, String dbURL, String admin, String senha) throws SQLException, ClassNotFoundException{
        
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
        
        
        public List<Admin> getAdminList() throws SQLException{
            
            try(
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT email, senha FROM admin");
            ){
                
                adminList = new ArrayList<>();
                while(rs.next()){
                    
                    String adm = rs.getString("email");
                    String senha = rs.getString("senha");
                    Admin admin = new Admin(adm, senha);
                    
                    adminList.add(admin);                    
                }
                
                rs.close();
                
            }
            
            return adminList;
            
        }
        
        public void createAdmin(String email, String senha) throws SQLException{
           
            try{                
                
                String query = "INSERT INTO admin (email, senha) VALUES (?, ?);";
                PreparedStatement stmt = con.prepareStatement(query);
                 
                stmt.setString(1, email);
                stmt.setString(2, senha);
                
                stmt.execute();
                                
                Admin admin = new Admin(email, senha);
                
                if(adminList == null)
                    adminList = getAdminList();
                
                adminList.add(admin);

                System.out.println(admin.getEmail());
                
            }catch(SQLException e){
                
                System.out.println("Erro ao escrever no banco de dados.");
                
                e.printStackTrace();
                
            }
        }
        
        public void updateAdmin(String oldEmail, String email, String senha) throws SQLException{
            
            
            if(!oldEmail.equals(email))
            {
                try{                

                    String query = "UPDATE admin SET (email) = ?, senha = ? WHERE email = ?;";
                    PreparedStatement stmt = con.prepareStatement(query);
                    
                    stmt.setString(1, email);
                    stmt.setString(2, senha);
                    stmt.setString(3, email);
                    
                    stmt.execute();
                    
                }catch(SQLException e){

                    e.printStackTrace();

                }
                
            }else{
                
                try{                
                    //System.out.println(oldEmail + "\n" + email + "\n" + senha + "\n");                    
                    
                   String query = "UPDATE admin SET senha = ? WHERE email= ?;";
                   PreparedStatement stmt = con.prepareStatement(query);                 
                    
                   stmt.setString(1, senha);
                   stmt.setString(2, oldEmail);
                   
                   stmt.execute();
                   
                }catch(SQLException e){

                   e.printStackTrace();

                }
            }
        }
        
        public void deleteAdmin(String email) throws SQLException{
            
           try{
                
                String query = "DELETE FROM admin WHERE email = ?;";
                PreparedStatement stmt = con.prepareStatement(query);
                
                stmt.setString(1, email);
                
                stmt.execute();
                
               // rs.close();
                
            }catch(SQLException e){
                
                e.printStackTrace();
                
            }
        }
    
}
