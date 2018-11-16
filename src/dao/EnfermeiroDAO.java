/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import idao.IEnfermeiroDAO;
import java.sql.SQLException;
import java.util.List;
import model.Enfermeiro;
import utils.ErroConectarException;
import utils.ErroDAOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Contato;
import model.Endereco;
import utils.Conectar;
import utils.GerarQueries;

/**
 *
 * @author MARCOS
 */
public class EnfermeiroDAO implements IEnfermeiroDAO {

    private Connection conexao;
    private PreparedStatement smt;
    private ResultSet set;
    private String sql;

    @Override
    public void cadastrarEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, SQLException {
        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryEnfermeiro("Cadastrar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, enfermeiro.getNome());
            smt.setString(2, enfermeiro.getEndereco().getRua());
            smt.setString(3, enfermeiro.getEndereco().getCidade());
            smt.setString(4, enfermeiro.getEndereco().getBairro());
            smt.setString(5, enfermeiro.getEndereco().getCep());
            smt.setString(6, enfermeiro.getEndereco().getComplemento());
            smt.setString(7, enfermeiro.getEndereco().getNumeroResidencia());
            smt.setString(8, enfermeiro.getEndereco().getUf());
            smt.setString(9, enfermeiro.getRg());
            smt.setString(10, enfermeiro.getCpf());
            smt.setString(11, enfermeiro.getLogin());
            smt.setString(12, enfermeiro.getSenha());
            smt.setDate(13, new Date(enfermeiro.getDataAdmissao().getTime()));
            smt.setDate(14, new Date(enfermeiro.getDataNascimento().getTime()));
            smt.setString(15, enfermeiro.getSexo());
            smt.setInt(16, enfermeiro.getIdade());
            smt.setDouble(17, enfermeiro.getSalario());
            smt.setString(18, enfermeiro.getFuncao());
            smt.setString(19, enfermeiro.getContato().getTelefone());
            smt.setString(20, enfermeiro.getContato().getCelular());
            smt.setString(21, enfermeiro.getContato().getEmail());
            smt.setString(22, enfermeiro.getCorem());

            smt.executeUpdate();
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar cadastrar os dados " + (enfermeiro.getSexo().equals("Masculino") ? "do Enfermeiro " : "da Enfermeira ") + enfermeiro.getNome() + "no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }
    }

    @Override
    public void alterarEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, SQLException {
        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryEnfermeiro("Atualizar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, enfermeiro.getNome());
            smt.setString(2, enfermeiro.getEndereco().getRua());
            smt.setString(3, enfermeiro.getEndereco().getCidade());
            smt.setString(4, enfermeiro.getEndereco().getBairro());
            smt.setString(5, enfermeiro.getEndereco().getCep());
            smt.setString(6, enfermeiro.getEndereco().getComplemento());
            smt.setString(7, enfermeiro.getEndereco().getUf());
            smt.setString(8, enfermeiro.getEndereco().getNumeroResidencia());
            smt.setString(9, enfermeiro.getRg());
            smt.setString(10, enfermeiro.getCpf());
            smt.setString(11, enfermeiro.getLogin());
            smt.setString(12, enfermeiro.getSenha());
            smt.setDate(13, new Date(enfermeiro.getDataNascimento().getTime()));
            smt.setDouble(14, enfermeiro.getSalario());
            smt.setString(15, enfermeiro.getFuncao());
            smt.setString(16, enfermeiro.getContato().getTelefone());
            smt.setString(17, enfermeiro.getContato().getCelular());
            smt.setString(18, enfermeiro.getContato().getEmail());
            smt.setString(19, enfermeiro.getCorem());
            smt.setString(20, enfermeiro.getSituacaoFuncionario());
            smt.setInt(21, enfermeiro.getCodigo());
            
            smt.executeUpdate();
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar atualizar os dados " + (enfermeiro.getSexo().equals("Masculino") ? "do Enfermeiro " : "da Enfermeira ") + enfermeiro.getNome() + "no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }
    }

    @Override
    public void excluirEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, SQLException {
        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryEnfermeiro("Excluir");
            smt = conexao.prepareStatement("Excluir");
            smt.setInt(1, enfermeiro.getCodigo());
            smt.executeUpdate();
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar excluir os dados " + (enfermeiro.getSexo().equals("Masculino") ? "do Enfermeiro " : "da Enfermeira ") + enfermeiro.getNome() + "no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }

    }

    @Override
    public Enfermeiro logarEnfermeiro(String login, String senha) throws ErroDAOException, ErroConectarException, SQLException {
        Enfermeiro enfermeiro = null;

        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryEnfermeiro("Logar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, login);
            smt.setString(2, senha);

            set = smt.executeQuery();
            if (set.next()) {
                enfermeiro = new Enfermeiro();
                enfermeiro.setCodigo(set.getInt("codigo"));
                enfermeiro.setNome(set.getString("nome"));
                Endereco endereco = new Endereco();
                endereco.setRua(set.getString("rua"));
                endereco.setCidade(set.getString("cidade"));
                endereco.setBairro(set.getString("bairro"));
                endereco.setCep(set.getString("cep"));
                endereco.setComplemento(set.getString("complemento"));
                endereco.setNumeroResidencia(set.getString("numero_residencia"));
                endereco.setUf(set.getString("uf"));
                enfermeiro.setEndereco(endereco);
                enfermeiro.setRg(set.getString("rg"));
                enfermeiro.setCpf(set.getString("cpf"));
                enfermeiro.setLogin(set.getString("login"));
                enfermeiro.setSenha(set.getString("senha"));
                enfermeiro.setFuncao(set.getString("funcao"));
                enfermeiro.setSituacaoFuncionario(set.getString("situacao_enfermeiro"));
                Contato contato = new Contato();
                contato.setTelefone(set.getString("telefone"));
                contato.setCelular(set.getString("celular"));
                contato.setEmail(set.getString("email"));
                enfermeiro.setContato(contato);
            }
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar cadastrar os dados " + (enfermeiro.getSexo().equals("Masculino") ? "do Enfermeiro " : "da Enfermeira ") + enfermeiro.getNome() + "no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }

        return enfermeiro;
    }

    @Override
    public List<Enfermeiro> listarEnfermeiros(String consulta, String campo) throws ErroDAOException, ErroConectarException, SQLException {
        List<Enfermeiro> enfermeiros = new ArrayList<>();

        try {
            conexao = Conectar.getConexao();
            switch (campo) {
                case "Todos":
                    sql = GerarQueries.gerarQueryEnfermeiro("ListarTodos");
                    smt = conexao.prepareStatement(sql);
                    break;
                case "Código":
                    sql = GerarQueries.gerarQueryEnfermeiro("Listar Por Código");
                    smt = conexao.prepareStatement(sql);
                    try {
                        smt.setInt(1, Integer.parseInt(consulta));
                    } catch (NumberFormatException ex) {
                        throw new ErroDAOException("Pesquisa Inválida!\nPara pesquisar por Código só é aceito números.\nDetalhes Técnicos:\n\n" + ex.getMessage());
                    }
                    break;
                case "Nome":
                    sql = GerarQueries.gerarQueryEnfermeiro("Listar Por Nome");
                    smt = conexao.prepareStatement(sql);
                    smt.setString(1, "%" + consulta + "%");
                    break;
                case "CPF":
                    sql = GerarQueries.gerarQueryEnfermeiro("Listar Por CPF");
                    smt = conexao.prepareStatement(sql);
                    smt.setString(1, consulta);
                    break;
                case "Ativos":
                    sql = GerarQueries.gerarQueryEnfermeiro("Ativos");
                    smt = conexao.prepareStatement(sql);
                    break;
                case "Bloqueados":
                    sql = GerarQueries.gerarQueryEnfermeiro("Bloqueados");
                    smt = conexao.prepareStatement(sql);
                    break;
                case "Suspensos":
                    sql = GerarQueries.gerarQueryEnfermeiro("Suspensos");
                    smt = conexao.prepareStatement(sql);
                    break;
                case "COREM":
                case "Corem":
                case "corem":
                case "coren":
                case "Coren":
                case "COREN":
                    sql = GerarQueries.gerarQueryEnfermeiro("Listar Por COREM");
                    smt = conexao.prepareStatement(sql);
                    smt.setString(1, consulta);
                    break;
                case "Último Enfermeiro Cadastrado":
                    sql = GerarQueries.gerarQueryEnfermeiro("Listar Enfermeiro mais Recente");
                    smt = conexao.prepareStatement(sql);
                    break;
            }

            set = smt.executeQuery();
            while (set.next()) {
                Enfermeiro enfermeiro = new Enfermeiro();
                enfermeiro.setCodigo(set.getInt("codigo"));
                enfermeiro.setNome(set.getString("nome"));
                Endereco endereco = new Endereco();
                endereco.setRua(set.getString("rua"));
                endereco.setCidade(set.getString("cidade"));
                endereco.setBairro(set.getString("bairro"));
                endereco.setCep(set.getString("cep"));
                endereco.setComplemento(set.getString("complemento"));
                endereco.setNumeroResidencia(set.getString("numero_residencia"));
                endereco.setUf(set.getString("uf"));
                enfermeiro.setEndereco(endereco);
                enfermeiro.setRg(set.getString("rg"));
                enfermeiro.setCpf(set.getString("cpf"));
                enfermeiro.setLogin(set.getString("login"));
                enfermeiro.setSenha(set.getString("senha"));
                enfermeiro.setSalario(set.getDouble("salario"));
                enfermeiro.setFuncao(set.getString("funcao"));
                enfermeiro.setSituacaoFuncionario(set.getString("situacao_enfermeiro"));
                Contato contato = new Contato();
                contato.setTelefone(set.getString("telefone"));
                contato.setCelular(set.getString("celular"));
                contato.setEmail(set.getString("email"));
                enfermeiro.setContato(contato);

            }

        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar listar os dados dos Enfermeiros(as) cadastrados(as) no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }

        return enfermeiros;
    }

    @Override
    public boolean verificarExistenciaByInsert(String nome, String login, String cpf) throws ErroDAOException, ErroConectarException, SQLException {
        boolean encontrou = false;
        
        try{
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryEnfermeiro("Verificar Existência Ao Cadastrar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, nome);
            smt.setString(2, login);
            smt.setString(3, cpf);
            
            set = smt.executeQuery();
            
            if(set.next()){
                encontrou = true;
            }
        }catch(SQLException ex){
            throw new ErroDAOException("Erro ao tentar verificar existência ao cadastrar os dados do Enfermeiro(a) no sistema." + ex.getMessage());
        }finally{
            Conectar.close(conexao, smt, set);
        }
        
        return encontrou;
    }

    @Override
    public boolean verificarExistencia(int codigo) throws ErroDAOException, ErroConectarException, SQLException {
        boolean encontrou = false;
        
        try{
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryEnfermeiro("Verificar Existência");
            smt = conexao.prepareStatement(sql);
            smt.setInt(1, codigo);
            
            set = smt.executeQuery();
            
            if(set.next()){
                encontrou = true;
            }
        }catch(SQLException ex){
            throw new ErroDAOException("Erro ao tentar verificar existência ao cadastrar os dados do Enfermeiro(a) no sistema." + ex.getMessage());
        }finally{
            Conectar.close(conexao, smt, set);
        }
        
        return encontrou;
    }

}
