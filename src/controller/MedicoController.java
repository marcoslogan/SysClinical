/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bl.MedicoBL;
import java.sql.SQLException;
import model.Medico;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;
import java.util.List;

/**
 *
 * @author MARCOS
 */
public class MedicoController {
    
    private static MedicoController inst;
    MedicoBL blMedico = null;
    
    private MedicoController(){
        blMedico = new MedicoBL();
    }
    
    public static MedicoController getInstance(){
        if(inst == null){
            inst = new MedicoController();
        }
        
        return inst;
    }
    
    public void cadastrarMedico(Medico medico) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        blMedico.cadastrarMedico(medico);
    }
    
    public void alterarMedico(Medico medico) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        blMedico.alterarMedico(medico);
    }
    
    public void excluirMedico(Medico medico) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        blMedico.excluirMedico(medico);
    }
    
    public Medico logarMedico(String login, String senha) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        return blMedico.logarMedico(login, senha);
    }
    
    public List<Medico> listarMedicos(String consulta, String campo) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        return blMedico.listarMedicos(consulta, campo);
    }
    
}
