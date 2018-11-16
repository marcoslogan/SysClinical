/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import model.Especialidade;
import java.sql.SQLException;
import java.util.List;
import utils.ErroConectarException;
import utils.ErroDAOException;

/**
 *
 * @author MARCOS
 */
public interface IEspecialidade {
    
    public boolean verificarExistenciaEspecialidade(String descricaoEspecialidade) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void cadastrarEspecialidade(Especialidade especialidade) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void alterarEspecialidade(Especialidade especialidade) throws ErroDAOException, ErroConectarException, SQLException;
    
    public void excluirEspecialidade(Especialidade especialidade) throws ErroDAOException, ErroConectarException, SQLException;
    
    public List<Especialidade> listarEspecialidades(String consulta, String campo) throws ErroDAOException, ErroConectarException, SQLException;
    
}
