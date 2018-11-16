/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *<b>DataHoraSistema</b>
 * <p>Classe responsável por exibir a data e a hora do sistema.</p>
 * @author Marcos Antônio
 */
public class DataHoraSistema {
    
    
    public DataHoraSistema(JLabel label, JLabel hora){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        label.setText(format.format(new Date()));
        
        Timer timer = new Timer(1000, new Hora(hora));
        timer.start();
        
    }
    
    
    
    public DataHoraSistema(JLabel hora){
        Timer timerHora = new Timer(1000, new Hora(hora));
        timerHora.start();
    }
    
    
    
    
    private class Hora implements ActionListener{

        private JLabel labelMensagem;
        
        public Hora(JLabel label){
            this.labelMensagem = label;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar calendar = Calendar.getInstance();
            labelMensagem.setText(String.format("%1$tH:%1$tM:%1$tS", calendar));
        }
        
    }
    
    
}
