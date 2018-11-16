/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import model.Medico;
import java.sql.SQLException;
import java.util.List;
import utils.ErroConectarException;
import utils.ErroDAOException;

/**
 *
 * @author MARCOS
 */
public interface IMedicoDAO {
    
    public void cadastrarMedico(Medico medico) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void alterarMedico(Medico medico) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void excluirMedico(Medico medico) throws ErroDAOException, ErroConectarException, SQLException;
    
    public Medico logarMedico(String login, String senha) throws ErroDAOException, ErroConectarException, SQLException;
    
    public List<Medico> listarMedicos(String consulta, String campo) throws ErroDAOException, ErroConectarException, SQLException;
    
    public boolean verificarExistenciaByInsert(String nome, String login, String cpf) throws ErroDAOException, ErroConectarException, SQLException;
    
    public boolean verificarExistencia(int codigo) throws ErroDAOException, ErroConectarException, SQLException;
}
