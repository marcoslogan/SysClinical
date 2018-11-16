/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import model.Funcionario;
import java.util.List;
import java.sql.SQLException;
import utils.ErroConectarException;
import utils.ErroDAOException;

/**
 *
 * @author MARCOS
 */
public interface IFuncionarioDAO {
    
    public boolean verificarExistenciaByInsert(String nome, String login, String cpf) throws ErroDAOException, ErroConectarException, SQLException;
    
    public boolean verificarExistencia(int codigo) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void cadastrarFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void alterarFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void excluirFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, SQLException;
    
    public Funcionario logarFuncionario(String login, String senha) throws ErroDAOException, ErroConectarException, SQLException;
    
    public List<Funcionario> listarFuncionario(String consulta, String campo) throws ErroDAOException, ErroConectarException, SQLException;
    
}
