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
public enum Efeitos {
    
    FERIMENTOS(1), ACIDENTES(2), DESTRUIÇÃO(3), FINALIDADE(4), OUTRO(5); 
    
    public int codEfeitos;   
    
    Efeitos(int cod){
        
        codEfeitos = cod;
        
    }
    
}