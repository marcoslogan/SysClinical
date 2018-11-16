/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import idao.IEspecialidade;
import java.sql.SQLException;
import java.util.List;
import model.Especialidade;
import utils.ErroConectarException;
import utils.ErroDAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.GerarQueries;

/**
 *
 * @author MARCOS
 */
public class EspecialidadeDAO implements IEspecialidade{

    private Connection conexao;
    private PreparedStatement smt;
    private ResultSet set;
    private String sql;
   
    
    @Override
    public boolean verificarExistenciaEspecialidade(String descricaoEspecialidade) throws ErroDAOException, ErroConectarException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cadastrarEspecialidade(Especialidade especialidade) throws ErroDAOException, ErroConectarException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarEspecialidade(Especialidade especialidade) throws ErroDAOException, ErroConectarException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluirEspecialidade(Especialidade especialidade) throws ErroDAOException, ErroConectarException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Especialidade> listarEspecialidades(String consulta, String campo) throws ErroDAOException, ErroConectarException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
