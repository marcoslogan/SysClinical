/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bl.PacienteBL;
import java.sql.SQLException;
import java.util.List;
import model.Paciente;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;

/**
 *
 * @author MARCOS
 */
public class PacienteController {
    
    private PacienteBL blPaciente;
    private static PacienteController inst;
    
    private PacienteController(){
        blPaciente = new PacienteBL();
    }
    
    public static PacienteController getInstance(){
        if(inst == null){
            inst = new PacienteController();
        }
        
        return inst;
    }
    
    
    /**
     * <b>cadastrarPaciente</b>
     * <p>Método responsável por cadastrar os dados do novo Paciente no sistema aplicando as regras de negócio.</p>
     * @param paciente Objeto contendo os dados do novo Paciente que serão cadastrados no sistema.
     * @throws ErroDAOException
     * @throws ErroNegocioException
     * @throws ErroConectarException
     * @throws SQLException
     */
    
    public void cadastrarPaciente(Paciente paciente) throws ErroDAOException, ErroNegocioException, ErroConectarException, SQLException{
        blPaciente.cadastrarPaciente(paciente);
    }
    
    
    /**
     * <b>alterarPaciente</b>
     * <p>Método responsável por atualizar/alterar os dados do Paciente no sistema aplicando também as regras de negócio (Validação dos dados)</p>
     * @param paciente Objeto contendo os dados atualizados do Paciente a serem alterados no sistema.
     * @throws ErroDAOException
     * @throws ErroNegocioException
     * @throws ErroConectarException
     * @throws SQLException
     */
    public void alterarPaciente(Paciente paciente) throws ErroDAOException, ErroNegocioException, ErroConectarException, SQLException{
        blPaciente.alterarPaciente(paciente);
    }
    
    
    /**
     * <b>excluirPaciente</b>
     * <p>Método responsável por excluir os dados do Paciente no sistema também aplicando as regras de negócio (Validação dos dados).</p>
     * @param paciente Objeto contendo o código do Paciente que será excluído do sistema.
     * @throws ErroDAOException
     * @throws ErroNegocioException
     * @throws ErroConectarException
     * @throws SQLException
     */
    
    public void excluirPaciente(Paciente paciente) throws ErroDAOException, ErroNegocioException, ErroConectarException, SQLException{
        blPaciente.excluirPaciente(paciente);
    }
    
    
    
    /**
     * <b>listarPacientes</b>
     * <p>Método responsável por realizar pesquisas genéricas no sistema, também aplicando as regras de negócio (Validação dos dados).</p>
     * @param consulta
     * @param campo
     * @throws ErroDAOException
     * @throws ErroNegocioException
     * @throws ErroConectarException
     * @throws SQLException
     * @return List - Lista contendo os dados dos Paciente previamente cadastrados no sistema.
     */
    
    public List<Paciente> listarPacientes(String consulta, String campo) throws ErroDAOException, ErroNegocioException, ErroConectarException, SQLException{
        return blPaciente.listarPacientes(consulta, campo);
    }
}
