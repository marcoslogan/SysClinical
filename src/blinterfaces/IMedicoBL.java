/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blinterfaces;

import model.Medico;
import java.sql.SQLException;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;
import java.util.List;

/**
 *
 * @author MARCOS
 */
public interface IMedicoBL {
    
    public void cadastrarMedico(Medico medico) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    public void alterarMedico(Medico medico) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    public void excluirMedico(Medico medico) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    public Medico logarMedico(String login, String senha) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    public List<Medico> listarMedicos(String consulta, String campo) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
}
