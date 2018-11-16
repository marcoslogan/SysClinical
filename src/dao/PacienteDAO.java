/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import idao.IPacienteDAO;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.Paciente;
import utils.ErroConectarException;
import utils.ErroDAOException;
import static utils.GerarQueries.gerarQueryPaciente;
import static utils.Conectar.getConexao;
import static utils.Conectar.close;
import java.sql.Date;
import model.Contato;
import model.Endereco;
import model.Funcionario;

/**
 *
 * @author MARCOS
 */
public class PacienteDAO implements IPacienteDAO {

    private Connection conexao;
    private PreparedStatement smt;
    private ResultSet set;
    private String sql;

    @Override
    public boolean verificarExistenciaByInsert(String nome, String login, String cpf) throws ErroDAOException, ErroConectarException, SQLException {
        boolean encontrou = false;

        try {
            conexao = getConexao();
            sql = gerarQueryPaciente("Verificar Existência ao Cadastrar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, nome);
            smt.setString(2, login);
            smt.setString(3, cpf);
            set = smt.executeQuery();
            if (set.next()) {
                encontrou = true;
            }
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar verificar a existência dos dados do Paciente no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            close(conexao, smt, set);
        }

        return encontrou;
    }

    @Override
    public boolean verificarExistencia(int codigo) throws ErroDAOException, ErroConectarException, SQLException {
        boolean encontrou = false;

        try {
            conexao = getConexao();
            sql = gerarQueryPaciente("Verificar Existência");
            smt = conexao.prepareStatement(sql);
            smt.setInt(1, codigo);
            set = smt.executeQuery();
            if (set.next()) {
                encontrou = true;
            }
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar verificar a existência dos dados do Paciente no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            close(conexao, smt, set);
        }

        return encontrou;
    }

    @Override
    public void cadastrarPaciente(Paciente paciente) throws ErroDAOException, ErroConectarException, SQLException {
        try {
            conexao = getConexao();
            sql = gerarQueryPaciente("Cadastrar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, paciente.getNome());
            smt.setString(2, paciente.getEndereco().getRua());
            smt.setString(3, paciente.getEndereco().getCidade());
            smt.setString(4, paciente.getEndereco().getBairro());
            smt.setString(5, paciente.getEndereco().getCep());
            smt.setString(6, paciente.getEndereco().getComplemento());
            smt.setString(7, paciente.getEndereco().getNumeroResidencia());
            smt.setString(8, paciente.getEndereco().getUf());
            smt.setString(9, paciente.getRg());
            smt.setString(10, paciente.getCpf());
            smt.setString(11, paciente.getLogin());
            smt.setString(12, paciente.getSenha());
            smt.setString(13, paciente.getGrupoSanguineo());
            smt.setDouble(14, paciente.getPeso());
            smt.setDouble(15, paciente.getAltura());
            smt.setDate(16, new Date(paciente.getDataNascimento().getTime()));
            smt.setDate(17, new Date(paciente.getDataCadastro().getTime()));
            smt.setString(18, paciente.getSexo());
            smt.setInt(19, paciente.getIdade());
            smt.setString(20, paciente.getContato().getTelefone());
            smt.setString(21, paciente.getContato().getCelular());
            smt.setString(22, paciente.getContato().getEmail());
            smt.setInt(23, paciente.getFuncionario().getCodigo());

            smt.executeUpdate();
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar cadastrar os dados " + (paciente.getSexo().equals("Masculino") ? "do Paciente " : "da Paciente ") + paciente.getNome() + " no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            close(conexao, smt, set);
        }
    }

    @Override
    public void alterarPaciente(Paciente paciente) throws ErroDAOException, ErroConectarException, SQLException {
        try {
            conexao = getConexao();
            sql = gerarQueryPaciente("Atualizar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, paciente.getNome());
            smt.setString(2, paciente.getEndereco().getRua());
            smt.setString(3, paciente.getEndereco().getCidade());
            smt.setString(4, paciente.getEndereco().getBairro());
            smt.setString(5, paciente.getEndereco().getCep());
            smt.setString(6, paciente.getEndereco().getComplemento());
            smt.setString(7, paciente.getEndereco().getNumeroResidencia());
            smt.setString(8, paciente.getEndereco().getUf());
            smt.setString(9, paciente.getRg());
            smt.setString(10, paciente.getCpf());
            smt.setString(11, paciente.getLogin());
            smt.setString(12, paciente.getSenha());
            smt.setString(11, paciente.getGrupoSanguineo());
            smt.setDouble(12, paciente.getPeso());
            smt.setDouble(13, paciente.getAltura());
            smt.setDate(14, new Date(paciente.getDataNascimento().getTime()));
            smt.setString(15, paciente.getSexo());
            smt.setInt(16, paciente.getIdade());
            smt.setString(17, paciente.getContato().getTelefone());
            smt.setString(18, paciente.getContato().getCelular());
            smt.setString(19, paciente.getContato().getEmail());
            smt.setString(20, paciente.getSituacao());
            smt.setInt(11, paciente.getCodigo());

            smt.executeUpdate();
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar excluir os dados " + (paciente.getSexo().equals("Masculino") ? "do Paciente " : "da Paciente ") + paciente.getNome() + " do sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            close(conexao, smt, set);
        }
    }

    @Override
    public void excluirPaciente(Paciente paciente) throws ErroDAOException, ErroConectarException, SQLException {
        try {
            conexao = getConexao();
            sql = gerarQueryPaciente("Excluir");
            smt = conexao.prepareStatement(sql);
            smt.setInt(1, paciente.getCodigo());
            smt.executeUpdate();
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar excluir os dados " + (paciente.getSexo().equals("Masculino") ? "do Paciente " : "da Paciente ") + paciente.getNome() + " do sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            close(conexao, smt, set);
        }

    }

    @Override
    public List<Paciente> listarPacientes(String consulta, String campo) throws ErroDAOException, ErroConectarException, SQLException {
        List<Paciente> pacientes = new ArrayList<>();

        try {
            conexao = getConexao();
            switch (campo) {
                case "Todos":
                    sql = gerarQueryPaciente("ListarTodos");
                    smt = conexao.prepareStatement(sql);
                    break;
                case "Código":
                    sql = gerarQueryPaciente("ListarPorCódigo");
                    smt = conexao.prepareStatement(sql);
                    try {
                        smt.setInt(1, Integer.parseInt(consulta));
                    } catch (NumberFormatException ex) {
                        throw new ErroDAOException("Essa pesquisa só aceita caracteres numéricos para ser realizada.\nDetalhes Técnicos:\n\n" + ex.getMessage());
                    }
                    break;
                case "Ativos":
                    sql = gerarQueryPaciente("ListarAtivos");
                    smt = conexao.prepareStatement(sql);
                    break;
                case "CPF":
                    sql = gerarQueryPaciente("ListarPorCPF");
                    smt = conexao.prepareStatement(sql);
                    smt.setString(1, consulta);
                    break;
                case "Nome":
                    sql = gerarQueryPaciente("Nome");
                    smt = conexao.prepareStatement(sql);
                    smt.setString(1, "%" + consulta + "%");
                    break;
                case "Bloqueados":
                    sql = gerarQueryPaciente("ListarBloqueados");
                    smt = conexao.prepareStatement(sql);
                    break;
                case "Suspensos":
                    sql = gerarQueryPaciente("ListarSuspensos");
                    smt = conexao.prepareStatement(sql);
                    break;
                case "Paciente Mais Recente":
                    sql = gerarQueryPaciente("ListarPacienteMaisRecente");
                    smt = conexao.prepareStatement(sql);
                    break;
            }
            
            set = smt.executeQuery();
            
            while(set.next()){
                Paciente paciente = new Paciente();
                paciente.setCodigo(set.getInt("codigo"));
                paciente.setNome(set.getString("nome"));
                Endereco endereco = new Endereco();
                endereco.setRua(set.getString("rua"));
                endereco.setCidade(set.getString("cidade"));
                endereco.setBairro(set.getString("bairro"));
                endereco.setCep(set.getString("cep"));
                endereco.setNumeroResidencia(set.getString("numero_residencia"));
                endereco.setUf(set.getString("uf"));
                endereco.setComplemento(set.getString("complemento"));
                paciente.setEndereco(endereco);
                paciente.setRg(set.getString("rg"));
                paciente.setCpf(set.getString("cpf"));
                paciente.setLogin(set.getString("login"));
                paciente.setSenha(set.getString("senha"));
                paciente.setSexo(set.getString("sexo"));
                paciente.setIdade(set.getInt("idade"));
                paciente.setPeso(set.getDouble("peso"));
                paciente.setAltura(set.getDouble("altura"));
                paciente.setGrupoSanguineo(set.getString("grupo_sanguineo"));
                paciente.setDataCadastro(set.getDate("data_cadastro"));
                paciente.setDataNascimento(set.getDate("data_nascimento"));
                paciente.setSituacao(set.getString("situacao_paciente"));
                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(set.getInt("f.codigo"));
                funcionario.setNome(set.getString("f.nome"));
                paciente.setFuncionario(funcionario);
                Contato contato = new Contato();
                contato.setTelefone(set.getString("telefone"));
                contato.setCelular(set.getString("celular"));
                contato.setEmail(set.getString("email"));
                paciente.setContato(contato);
                
                pacientes.add(paciente);
            }
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar listar os dados dos Pacientes no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            close(conexao, smt, set);
        }

        return pacientes;
    }

}
