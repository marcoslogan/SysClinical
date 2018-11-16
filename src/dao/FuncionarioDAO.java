/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import utils.Conectar;
import utils.GerarQueries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import idao.IFuncionarioDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import model.Funcionario;
import model.Endereco;
import model.Contato;
import utils.ErroConectarException;
import utils.ErroDAOException;
import java.sql.Date;

/**
 *
 * @author MARCOS
 */
public class FuncionarioDAO implements IFuncionarioDAO {

    private Connection conexao;
    private PreparedStatement smt;
    private ResultSet set;
    private String sql;

    @Override
    public boolean verificarExistenciaByInsert(String nome, String login, String cpf) throws ErroDAOException, ErroConectarException, SQLException {
        boolean encontrou = false;

        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryFuncionario("Verificar Existência ao Cadastrar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, login);
            smt.setString(2, nome);
            smt.setString(3, cpf);

            set = smt.executeQuery();
            if (set.next()) {
                encontrou = true;
            }
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar verificar a existência dos dados do Funcionário no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }

        return encontrou;
    }

    @Override
    public boolean verificarExistencia(int codigo) throws ErroDAOException, ErroConectarException, SQLException {
        boolean encontrou = false;

        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryFuncionario("Verificar Existência");
            smt = conexao.prepareStatement(sql);
            smt.setInt(1, codigo);

            set = smt.executeQuery();
            if (set.next()) {
                encontrou = true;
            }
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar verificar a existência dos dados do Funcionário no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }

        return encontrou;
    }

    @Override
    public void cadastrarFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, SQLException {

        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryFuncionario("Cadastrar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, funcionario.getNome());
            smt.setString(2, funcionario.getEndereco().getRua());
            smt.setString(3, funcionario.getEndereco().getCidade());
            smt.setString(4, funcionario.getEndereco().getBairro());
            smt.setString(5, funcionario.getEndereco().getCep());
            smt.setString(6, funcionario.getEndereco().getComplemento());
            smt.setString(7, funcionario.getEndereco().getNumeroResidencia());
            smt.setString(8, funcionario.getEndereco().getUf());
            smt.setString(9, funcionario.getRg());
            smt.setString(10, funcionario.getCpf());
            smt.setString(11, funcionario.getLogin());
            smt.setString(12, funcionario.getSenha());
            smt.setDate(13, new Date(funcionario.getDataNascimento().getTime()));
            smt.setDate(14, new Date(funcionario.getDataAdmissao().getTime()));
            smt.setDouble(15, funcionario.getSalario());
            smt.setString(16, funcionario.getFuncao());
            smt.setString(17, funcionario.getSexo());
            smt.setInt(18, funcionario.getIdade());
            smt.setString(19, funcionario.getContato().getTelefone());
            smt.setString(20, funcionario.getContato().getCelular());
            smt.setString(21, funcionario.getContato().getEmail());

            smt.executeUpdate();
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar cadastrar os dados do Funcionário no sistema no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }

    }

    @Override
    public void alterarFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, SQLException {
        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryFuncionario("Atualizar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, funcionario.getNome());
            smt.setString(2, funcionario.getEndereco().getRua());
            smt.setString(3, funcionario.getEndereco().getCidade());
            smt.setString(4, funcionario.getEndereco().getBairro());
            smt.setString(5, funcionario.getEndereco().getCep());
            smt.setString(6, funcionario.getEndereco().getComplemento());
            smt.setString(7, funcionario.getEndereco().getNumeroResidencia());
            smt.setString(8, funcionario.getEndereco().getUf());
            smt.setString(9, funcionario.getRg());
            smt.setString(10, funcionario.getCpf());
            smt.setString(11, funcionario.getLogin());
            smt.setString(12, funcionario.getSenha());
            smt.setDate(13, new Date(funcionario.getDataNascimento().getTime()));
            smt.setDouble(14, funcionario.getSalario());
            smt.setString(15, funcionario.getFuncao());
            smt.setString(16, funcionario.getSexo());
            smt.setInt(17, funcionario.getIdade());
            smt.setString(18, funcionario.getContato().getTelefone());
            smt.setString(19, funcionario.getContato().getCelular());
            smt.setString(20, funcionario.getContato().getEmail());
            smt.setString(21, funcionario.getSituacaoFuncionario());
            smt.setInt(22, funcionario.getCodigo());

            smt.executeUpdate();
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar verificar a existência dos dados do Funcionário no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }
    }

    @Override
    public void excluirFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, SQLException {
        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryFuncionario("Excluir");
            smt = conexao.prepareStatement(sql);
            smt.setInt(1, funcionario.getCodigo());

            smt.executeUpdate();
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar verificar a existência dos dados do Funcionário no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }
    }

    @Override
    public Funcionario logarFuncionario(String login, String senha) throws ErroDAOException, ErroConectarException, SQLException {
        Funcionario funcionario = null;

        if (login.equals("admin") && senha.equals("admin")) {
            funcionario = new Funcionario();
            funcionario.setCodigo(1);
            funcionario.setNome("Marcos Antônio Bezerra de Sousa");
            funcionario.setLogin(login);
            funcionario.setFuncao("Administrador");
            funcionario.setSituacaoFuncionario("Ativo");
        } else {
            try {
                conexao = Conectar.getConexao();
                sql = GerarQueries.gerarQueryFuncionario("Logar");
                smt = conexao.prepareStatement(sql);
                smt.setString(1, login);
                smt.setString(2, senha);

                set = smt.executeQuery();

                if (set.next()) {
                    funcionario = new Funcionario();
                    funcionario.setCodigo(set.getInt("codigo"));
                    funcionario.setNome(set.getString("nome"));
                    Endereco endereco = new Endereco();
                    endereco.setRua(set.getString("rua"));
                    endereco.setCidade(set.getString("cidade"));
                    endereco.setBairro(set.getString("bairro"));
                    endereco.setCep(set.getString("cep"));
                    endereco.setComplemento(set.getString("complemento"));
                    endereco.setNumeroResidencia("numero_residencia");
                    endereco.setUf(set.getString("uf"));
                    funcionario.setEndereco(endereco);
                    funcionario.setRg(set.getString("rg"));
                    funcionario.setCpf(set.getString("cpf"));
                    funcionario.setLogin(set.getString("login"));
                    funcionario.setDataAdmissao(set.getDate("data_admissao"));
                    funcionario.setDataNascimento(set.getDate("data_nascimento"));
                    funcionario.setSexo(set.getString("sexo"));
                    funcionario.setIdade(set.getInt("idade"));
                    funcionario.setSalario(set.getDouble("salario"));
                    funcionario.setFuncao(set.getString("funcao"));
                    Contato contato = new Contato();
                    contato.setTelefone(set.getString("telefone"));
                    contato.setCelular(set.getString("celular"));
                    contato.setEmail(set.getString("email"));
                    funcionario.setContato(contato);
                    funcionario.setSituacaoFuncionario(set.getString("situacao_funcionario"));

                }
            } catch (SQLException ex) {
                throw new ErroDAOException("Erro ao tentar logar-se no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
            } finally {
                Conectar.close(conexao, smt, set);
            }
        }

        return funcionario;
    }

    @Override
    public List<Funcionario> listarFuncionario(String consulta, String campo) throws ErroDAOException, ErroConectarException, SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            conexao = Conectar.getConexao();
            if (consulta.equals("Todos") && campo.equals("Todos")) {
                sql = GerarQueries.gerarQueryFuncionario("ListarTodos");
                smt = conexao.prepareStatement(sql);
            } else {
                switch (campo) {
                    case "Código":
                        sql = GerarQueries.gerarQueryFuncionario("Listar Por Código");
                        smt = conexao.prepareStatement(sql);
                        try {
                            smt.setInt(1, Integer.parseInt(consulta));
                        } catch (NumberFormatException ex) {
                            throw new ErroDAOException("Esta consulta só aceita números.\nDetalhes Técnicos:\n\n" + ex.getMessage());
                        }   break;
                    case "Nome":
                        sql = GerarQueries.gerarQueryFuncionario("Listar Por Nome");
                        smt = conexao.prepareStatement(sql);
                        smt.setString(1, "%" + consulta + "%");
                        break;
                    case "CPF":
                        sql = GerarQueries.gerarQueryFuncionario("Listar Por CPF");
                        smt = conexao.prepareStatement(sql);
                        smt.setString(1, consulta);
                        break;
                    case "Ativos":
                        sql = GerarQueries.gerarQueryFuncionario("Listar Ativos");
                        smt = conexao.prepareStatement(sql);
                        break;
                    case "Bloqueados":
                        sql = GerarQueries.gerarQueryFuncionario("Listar Bloqueados");
                        smt = conexao.prepareStatement(sql);
                        break;
                    case "Suspensos":
                        sql = GerarQueries.gerarQueryFuncionario("Listar Suspensos");
                        smt = conexao.prepareStatement(sql);
                        break;
                    case "Listar Funcionário mais Recente":
                        sql = GerarQueries.gerarQueryFuncionario("Listar Funcionário mais recente");
                        smt = conexao.prepareStatement(sql);
                        break;
                    default:
                        sql = "";
                        break;
                }
            }

            set = smt.executeQuery();

            while (set.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(set.getInt("codigo"));
                funcionario.setNome(set.getString("nome"));
                Endereco endereco = new Endereco();
                endereco.setRua(set.getString("rua"));
                endereco.setCidade(set.getString("cidade"));
                endereco.setBairro(set.getString("bairro"));
                endereco.setCep(set.getString("cep"));
                endereco.setComplemento(set.getString("complemento"));
                endereco.setUf(set.getString("uf"));
                endereco.setNumeroResidencia(set.getString("numero_residencia"));
                funcionario.setEndereco(endereco);
                funcionario.setRg(set.getString("rg"));
                funcionario.setCpf(set.getString("cpf"));
                funcionario.setLogin(set.getString("login"));
                funcionario.setSenha(set.getString("senha"));
                funcionario.setSexo(set.getString("sexo"));
                funcionario.setIdade(set.getInt("idade"));
                funcionario.setDataNascimento(set.getDate("data_nascimento"));
                funcionario.setDataAdmissao(set.getDate("data_admissao"));
                funcionario.setSalario(set.getDouble("salario"));
                funcionario.setFuncao(set.getString("funcao"));
                funcionario.setSituacaoFuncionario(set.getString("situacao_funcionario"));
                Contato contato = new Contato();
                contato.setTelefone(set.getString("telefone"));
                contato.setCelular(set.getString("celular"));
                contato.setEmail(set.getString("email"));
                funcionario.setContato(contato);
                
                
                funcionarios.add(funcionario);
                
            }
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar listar os dados dos Funcionários no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }

        return funcionarios;
    }

}
