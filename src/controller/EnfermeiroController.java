/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bl.EnfermeiroBL;
import java.sql.SQLException;
import model.Enfermeiro;
import java.util.List;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;

/**
 *
 * @author MARCOS
 */
public class EnfermeiroController {
    
    private static EnfermeiroController inst;
    private EnfermeiroBL blEnfermeiro;
    
    private EnfermeiroController(){
        blEnfermeiro = new EnfermeiroBL();
    }
    
    public static EnfermeiroController getInstance(){
        if(inst == null){
            inst = new EnfermeiroController();
        }
        
        return inst;
    }
    
    public void cadastrarEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        blEnfermeiro.cadastrarEnfermeiro(enfermeiro);
    }
    
    public void alterarEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        blEnfermeiro.alterarEnfermeiro(enfermeiro);
        
    }
    
    public void excluirEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        blEnfermeiro.excluirEnfermeiro(enfermeiro);
    }
    
    public List<Enfermeiro> listarEnfermeiros(String consulta, String campo) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        return blEnfermeiro.listarEnfermeiros(consulta, campo);
    }
    
    public Enfermeiro logarEnfermeiro(String login, String senha) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        return blEnfermeiro.logarEnfermeiro(login, senha);
    }
    
    
}
