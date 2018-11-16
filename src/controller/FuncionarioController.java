/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bl.FuncionarioBL;
import java.sql.SQLException;
import model.Funcionario;
import java.util.List;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;
/**
 *
 * @author MARCOS
 */
public class FuncionarioController {
    
    private static FuncionarioController inst;
    
    private FuncionarioBL funcBL;
    
    private FuncionarioController(){
        funcBL = new FuncionarioBL();
    }
    
    public static FuncionarioController getCurrentInstance(){
        if(inst == null){
            inst = new FuncionarioController();
        }
        
        return inst;
    }
    
    
    /**
     * <b>cadastrarFuncionario</b>
     * <p>Método responsável por cadastrar os dados do novo funcionário no sistema.</p>
     * @param funcionario Objeto contendo os novos dados do funcionário a serem cadastrados no sistema.
     * @throws ErroDAOException,
     * @throws ErroConectarException
     * @throws ErroNegocioException
     * @throws SQLException
     */
    public void cadastrarFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        funcBL.cadastrarFuncionario(funcionario);
    }
    
    /**
     * <b>alterarFuncionario</b>
     * <p>Método responsável por alterar | atualizar os dados do funcionário no sistema.</p>
     * @param funcionario Objeto contendo os dados atualizados do funcionário a serem inseridos no sistema.
     * @throws ErroDAOException,
     * @throws ErroConectarException,
     * @throws ErroNegocioException,
     * @throws SQLException
     */
    
    public void alterarFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        funcBL.alterarFuncionario(funcionario);
    }
    
    /**
     * <b>excluirFuncionario</b>
     * <p>Método responsável por excluir os dados do funcionário no sistema.</p>
     * @param funcionario Objeto contendo os dados do funcionário que serão excluídos do sistema.
     * @throws ErroDAOException,
     * @throws ErroConectarException
     * @throws ErroNegocioException
     * @throws SQLException
     */
    
    public void excluirFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        funcBL.excluirFuncionario(funcionario);
    }
    
    
    /**
     * <b>logarFuncionario</b>
     * <p>Método responsável por realizar o login do funcionário mas também retornar o seu nível de acesso no sistema.</p>
     * @param login Login do funcionário cadastrado anteriormente no sistema.
     * @param senha Senha do funcionário cadastrado anteriormente no sistema.
     * @throws ErroDAOException,
     * @throws ErroConectarException,
     * @throws ErroNegocioException,
     * @throws SQLException
     * @return Funcionario - Objeto contendo os dados do funcionário.
     */
    public Funcionario logarFuncionario(String login, String senha) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        return funcBL.logarFuncionario(login, senha);
    }
    
    /**
     * <b>listarFuncionarios</b>
     * <p>Método responsável por realizar pesquisas genéricas no sistema.</p>
     * @param consulta Consulta na qual o funcionário irá inserir para realizar uma pesquisa.
     * @param campo Definirá qual o campo irá ser utilizado como o filtro da pesquisa.
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws ErroNegocioException
     * @throws SQLException
     * @return List - Lista dos funcionários previamente cadastrados no sistema.
     */
    
    public List<Funcionario> listarFuncionarios(String consulta, String campo) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException{
        return funcBL.listarFuncionarios(consulta, campo);
    }
    
}

