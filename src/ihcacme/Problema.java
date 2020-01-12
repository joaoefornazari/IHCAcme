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
public enum Problema {
    
    ATRASO(1), DEFEITO(2), ERRO(3), OUTRO(4); 
    
    public int codProblema;   
    
    Problema(int cod){
        
        codProblema = cod;
        
    }
    
}
