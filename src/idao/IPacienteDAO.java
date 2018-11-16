/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import model.Paciente;
import java.sql.SQLException;
import java.util.List;
import utils.ErroConectarException;
import utils.ErroDAOException;

/**
 *
 * @author MARCOS
 */
public interface IPacienteDAO {
    
    public boolean verificarExistenciaByInsert(String nome, String login, String cpf) throws ErroDAOException, ErroConectarException, SQLException;
    
    public boolean verificarExistencia(int codigo) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void cadastrarPaciente(Paciente paciente) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void alterarPaciente(Paciente paciente) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void excluirPaciente(Paciente paciente) throws ErroDAOException, ErroConectarException, SQLException;
    
    public List<Paciente> listarPacientes(String consulta, String campo) throws ErroDAOException, ErroConectarException, SQLException;
    
}
