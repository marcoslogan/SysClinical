/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blinterfaces;

import model.Enfermeiro;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MARCOS
 */
public interface IEnfermeiroBL {
    
    public void cadastrarEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    public void alterarEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    public void excluirEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    public Enfermeiro logarEnfermeiro(String login, String senha) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    public List<Enfermeiro> listarEnfermeiros(String consulta, String campo) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException;
    
    
    
}
