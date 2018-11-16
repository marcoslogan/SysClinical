/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;

/**
 *
 * @author WIN7
 */
public class CloseSystem extends WindowAdapter {

    private JDesktopPane painel;

    public void setPainel(JDesktopPane painel) {
        this.painel = painel;
    }

    public void closing() {
        Object[] botoes = {"Sim", "Não"};
        int choice = JOptionPane.showOptionDialog(painel, "Deseja encerrar o sistema?", "Deseja realmente fechar o SysClinical?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[1]);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    public void closingInternal(){
        Object[] botoes = {"Sim", "Não"};
        int choice = JOptionPane.showInternalOptionDialog(painel, "Deseja encerrar o sistema?", "Deseja realmente fechar o SysClinical?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[1]);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void windowClosing(WindowEvent evt) {
        
    }
}
