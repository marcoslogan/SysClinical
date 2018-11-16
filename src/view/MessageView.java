/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author MARCOS
 */
public class MessageView {
    
    
    public static String[] requiredField(String field){
        String[] mensagens = new String[2];
        
        mensagens[0] = "O Campo " + field + " é um campo obrigatório portanto não pode ficar vazio.";
        mensagens[1] = "Campo Obrigatório!";
        
        
        return mensagens;
    }
    
    
}
