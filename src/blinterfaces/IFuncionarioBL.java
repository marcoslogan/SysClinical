/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blinterfaces;

import model.Funcionario;
import java.util.List;
import java.sql.SQLException;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;

/**
 *
 * @author MARCOS
 */
public interface IFuncionarioBL {
    
    public void cadastrarFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    public void alterarFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    public void excluirFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    public Funcionario logarFuncionario(String login, String senha) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    public List<Funcionario> listarFuncionarios(String consulta, String campo) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
}
