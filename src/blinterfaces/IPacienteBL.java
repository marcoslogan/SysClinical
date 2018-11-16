/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blinterfaces;

import model.Paciente;
import java.sql.SQLException;
import java.util.List;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;

/**
 *
 * @author MARCOS
 */
public interface IPacienteBL {
    
    
    public void cadastrarPaciente(Paciente paciente) throws ErroDAOException, ErroNegocioException, ErroConectarException, SQLException;
    
    public void alterarPaciente(Paciente paciente) throws ErroDAOException, ErroNegocioException, ErroConectarException, SQLException;
    
    public void excluirPaciente(Paciente paciente) throws ErroDAOException, ErroNegocioException, ErroConectarException, SQLException;
    
    public List<Paciente> listarPacientes(String consulta, String campo) throws ErroDAOException, ErroNegocioException, ErroConectarException, SQLException;
    
    
    
    
    
    
}
