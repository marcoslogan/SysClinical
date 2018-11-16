/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;



/**
 *
 * @author Marcos Antônio
 */
public class Age {
    
    
    
    public static int calculateAge(String data, String pattern){
        int age = 0;
        
        DateFormat sdf = new SimpleDateFormat(pattern);
        
        Date dataNasc = null;
        
        
        try{
            
            dataNasc = sdf.parse(data);
            
        }catch(Exception ex){}
        
        Calendar dateOfBirth = new GregorianCalendar();
        
        dateOfBirth.setTime(dataNasc);
        
        Calendar hoje = Calendar.getInstance();
        
        age = hoje.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        
        
        return age;
        
    }
    
    
    
}
