/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import model.Enfermeiro;
import utils.ErroConectarException;
import utils.ErroDAOException;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author MARCOS
 */
public interface IEnfermeiroDAO {
    
    public void cadastrarEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void alterarEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void excluirEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, SQLException;
    
    public Enfermeiro logarEnfermeiro(String login, String senha) throws ErroDAOException, ErroConectarException, SQLException;
    
    public List<Enfermeiro> listarEnfermeiros(String consulta, String campo) throws ErroDAOException, ErroConectarException, SQLException;
    
    public boolean verificarExistenciaByInsert(String nome, String login, String cpf) throws ErroDAOException, ErroConectarException, SQLException;
    
    public boolean verificarExistencia(int codigo) throws ErroDAOException, ErroConectarException, SQLException;
    
}
