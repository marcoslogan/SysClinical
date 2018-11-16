/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import idao.IMedicoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Medico;
import utils.ErroConectarException;
import utils.ErroDAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import model.Contato;
import model.Endereco;
import utils.Conectar;
import utils.GerarQueries;

/**
 *
 * @author MARCOS
 */
public class MedicoDAO implements IMedicoDAO {

    private Connection conexao;
    private PreparedStatement smt;
    private ResultSet set;
    private String sql;

    /**
     * <b>cadastrarMedico</b>
     * <p>
     * Método responsável por cadastrar os dados do novo Médico no banco de
     * dados do sistema.</p>
     * <p>
     * Caso o cadastro não seja efetuado uma exceção será lançada.</p>
     *
     * @param medico Objeto contendo os dados do novo Médico que será cadastrado
     * no sistema.
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws SQLException
     */
    @Override
    public void cadastrarMedico(Medico medico) throws ErroDAOException, ErroConectarException, SQLException {
        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryMedico("Cadastrar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, medico.getNome());
            smt.setString(2, medico.getEndereco().getRua());
            smt.setString(3, medico.getEndereco().getCidade());
            smt.setString(4, medico.getEndereco().getBairro());
            smt.setString(5, medico.getEndereco().getCep());
            smt.setString(6, medico.getEndereco().getComplemento());
            smt.setString(7, medico.getEndereco().getNumeroResidencia());
            smt.setString(8, medico.getEndereco().getUf());
            smt.setString(9, medico.getRg());
            smt.setString(10, medico.getCpf());
            smt.setString(11, medico.getLogin());
            smt.setString(12, medico.getSenha());
            smt.setDate(13, new Date(medico.getDataAdmissao().getTime()));
            smt.setDate(14, new Date(medico.getDataNascimento().getTime()));
            smt.setString(15, medico.getSexo());
            smt.setInt(16, medico.getIdade());
            smt.setDouble(17, medico.getSalario());
            smt.setString(18, medico.getFuncao());
            smt.setString(19, medico.getContato().getTelefone());
            smt.setString(20, medico.getContato().getCelular());
            smt.setString(21, medico.getContato().getEmail());
            smt.setString(22, medico.getCrm());

            smt.executeUpdate();

        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar cadastrar os dados " + (medico.getSexo().equals("Masculino") ? "do Médico " : "da Médica ") + "no sistema." + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }
    }

    /**
     * <b>alterarMedico</b>
     * <p>
     * Método responsável por atualizar/alterar os dados do Médico no banco de
     * dados do sistema.</p>
     * <p>
     * Caso a atualização não seja efetuada uma exceção será lançada.</p>
     *
     * @param medico Objeto contendo os dados atualizados do Médico que serão
     * alterados no sistema.
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws SQLException
     */
    @Override
    public void alterarMedico(Medico medico) throws ErroDAOException, ErroConectarException, SQLException {
        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryMedico("Atualizar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, medico.getNome());
            smt.setString(2, medico.getEndereco().getRua());
            smt.setString(3, medico.getEndereco().getCidade());
            smt.setString(4, medico.getEndereco().getBairro());
            smt.setString(5, medico.getEndereco().getCep());
            smt.setString(6, medico.getEndereco().getComplemento());
            smt.setString(7, medico.getEndereco().getNumeroResidencia());
            smt.setString(8, medico.getEndereco().getUf());
            smt.setString(9, medico.getRg());
            smt.setString(10, medico.getCpf());
            smt.setString(11, medico.getLogin());
            smt.setString(12, medico.getSenha());
            smt.setDate(13, new Date(medico.getDataNascimento().getTime()));
            smt.setDouble(14, medico.getSalario());
            smt.setString(15, medico.getSexo());
            smt.setInt(16, medico.getIdade());
            smt.setString(17, medico.getContato().getTelefone());
            smt.setString(18, medico.getContato().getCelular());
            smt.setString(19, medico.getContato().getEmail());
            smt.setString(20, medico.getSituacaoFuncionario());
            smt.setString(21, medico.getCrm());
            smt.setString(22, medico.getEspecialidade());

            smt.executeUpdate();
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar alterar os dados do Médico no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }
    }

    /**
     * <b>excluirMedico</b>
     * <p>
     * Método responsável por excluir os dados do Médico do sistema.</p>
     * <p>
     * Caso a exclusão não seja efetuada uma exceção será lançada.</p>
     *
     * @param medico Objeto contendo os dados do novo Médico que será cadastrado
     * no sistema.
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws SQLException
     */
    @Override
    public void excluirMedico(Medico medico) throws ErroDAOException, ErroConectarException, SQLException {
        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryMedico("Excluir");
            smt = conexao.prepareStatement(sql);
            smt.setInt(1, medico.getCodigo());

            smt.executeUpdate();
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar excluir os dados do Médico no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }
    }

    /**
     * <b>cadastrarMedico</b>
     * <p>
     * Método responsável por cadastrar os dados do novo Médico no banco de
     * dados do sistema.</p>
     * <p>
     * Caso o cadastro não seja efetuado uma exceção será lançada.</p>
     *
     * @param login
     * @param senha
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws SQLException
     * @return Medico - Objeto contendo os dados do Médico cadastrado no
     * sistema.
     */
    @Override
    public Medico logarMedico(String login, String senha) throws ErroDAOException, ErroConectarException, SQLException {
        Medico medico = null;

        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryMedico("Logar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, login);
            smt.setString(2, senha);
            set = smt.executeQuery();
            if (set.next()) {
                medico = new Medico();
                medico.setCodigo(set.getInt("codigo"));
                medico.setNome(set.getString("nome"));
                Endereco endereco = new Endereco();
                endereco.setRua(set.getString("rua"));
                endereco.setCidade(set.getString("cidade"));
                endereco.setBairro(set.getString("bairro"));
                endereco.setCep(set.getString("cep"));
                endereco.setComplemento(set.getString("complemento"));
                endereco.setNumeroResidencia(set.getString("numero_residencia"));
                endereco.setUf(set.getString("uf"));
                medico.setEndereco(endereco);
                medico.setRg(set.getString("rg"));
                medico.setCpf(set.getString("cpf"));
                medico.setLogin(set.getString("login"));
                medico.setSalario(set.getDouble("salario"));
                medico.setFuncao(set.getString("funcao"));
                medico.setCrm(set.getString("crm"));
                medico.setEspecialidade(set.getString("especialidade"));
            }
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar logar no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }

        return medico;
    }

    /**
     * <b>listarMedicos</b>
     * <p>
     * Método responsável por realizar pesquisas genéricas no sistema.</p>
     * <p>
     * Caso os dados não sejam encontrados uma exceção será lançada.</p>
     *
     * @param consulta Aqui onde o usuário irá informar a consulta a ser
     * realizada no sistema.
     * @param campo Aqui é o campo no qual serão filtradas as informações.
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws SQLException
     * @return List - Lista contendo os dados dos Médicos previamente
     * cadastrados no sistema.
     */
    @Override
    public List<Medico> listarMedicos(String consulta, String campo) throws ErroDAOException, ErroConectarException, SQLException {
        List<Medico> medicos = new ArrayList<>();

        try {
            conexao = Conectar.getConexao();

            if (consulta.equals("Todos") && campo.equals("Todos")) {
                sql = GerarQueries.gerarQueryMedico("ListarTodos");
                smt = conexao.prepareStatement(sql);
            } else {
                switch (campo) {
                    case "Código":
                        sql = GerarQueries.gerarQueryMedico("Listar Por Código");
                        smt = conexao.prepareStatement(sql);
                        try {
                            smt.setInt(1, Integer.parseInt(consulta));
                        } catch (NumberFormatException ex) {
                            throw new ErroDAOException("Formato de número inválido!\nEsta pesquisa só aceita caracteres numéricos.");
                        }
                        break;
                    case "Nome":
                        sql = GerarQueries.gerarQueryMedico("Listar Por Nome");
                        smt = conexao.prepareStatement(sql);
                        smt.setString(1, "%" + consulta + "%");
                        break;
                    case "CPF":
                        sql = GerarQueries.gerarQueryMedico("Listar Por CPF");
                        smt = conexao.prepareStatement(sql);
                        smt.setString(1, consulta);
                        break;
                    case "Ativos":
                        sql = GerarQueries.gerarQueryMedico("Ativos");
                        smt = conexao.prepareStatement(sql);
                        break;
                    case "Bloqueados":
                        sql = GerarQueries.gerarQueryMedico("Bloqueados");
                        smt = conexao.prepareStatement(sql);
                        break;
                    case "Suspensos":
                        sql = GerarQueries.gerarQueryMedico("Suspensos");
                        smt = conexao.prepareStatement(sql);
                        break;
                    case "Médico Cadastrado recentemente":
                        sql = GerarQueries.gerarQueryMedico("Listar Médico mais recente");
                        smt = conexao.prepareStatement(sql);
                        break;
                }
            }

            set = smt.executeQuery();
            while (set.next()) {
                Medico medico = new Medico();
                medico.setCodigo(set.getInt("codigo"));
                medico.setNome(set.getString("nome"));
                Endereco endereco = new Endereco();
                endereco.setRua(set.getString("rua"));
                endereco.setCidade(set.getString("cidade"));
                endereco.setBairro(set.getString("bairro"));
                endereco.setCep(set.getString("cep"));
                endereco.setComplemento(set.getString("complemento"));
                endereco.setNumeroResidencia(set.getString("numero_residencia"));
                endereco.setUf(set.getString("uf"));
                medico.setEndereco(endereco);
                medico.setRg(set.getString("rg"));
                medico.setCpf(set.getString("cpf"));
                medico.setLogin(set.getString("login"));
                medico.setSenha(set.getString("senha"));
                medico.setEspecialidade(set.getString("especialidade"));
                medico.setSalario(set.getDouble("salario"));
                medico.setFuncao(set.getString("funcao"));
                medico.setDataAdmissao(set.getDate("data_admissao"));
                medico.setDataNascimento(set.getDate("data_nascimento"));
                medico.setCrm(set.getString("crm"));
                medico.setSexo(set.getString("sexo"));
                medico.setIdade(set.getInt("idade"));
                medico.setSituacaoFuncionario(set.getString("situacao_medico"));
                Contato contato = new Contato();
                contato.setTelefone(set.getString("telefone"));
                contato.setCelular(set.getString("celular"));
                contato.setEmail(set.getString("email"));
                medico.setContato(contato);

                medicos.add(medico);
            }

        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar listar os dados dos Médicos previamente cadastrados no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }

        return medicos;
    }

    /**
     * <b>verificarExistenciaByInsert</b>
     * <p>
     * Método responsável por verificar a existência dos dados do Médico ao
     * cadastrá-lo no sistema. O método retornará true se o mesmo já estiver
     * cadastrado no sistema, caso contrário retornará false.
     * </p>
     * <p>
     * Caso a verificação falhe uma exceção será lançada.</p>
     *
     * @param nome
     * @param login
     * @param cpf
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws SQLException
     * @return boolean - Este método retornará true se esses dados já existirem
     * caso contrário retornará false.
     */
    @Override
    public boolean verificarExistenciaByInsert(String nome, String login, String cpf) throws ErroDAOException, ErroConectarException, SQLException {
        boolean encontrou = false;

        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryMedico("Verificar Existência ao Cadastrar");
            smt = conexao.prepareStatement(sql);
            smt.setString(1, nome);
            smt.setString(2, login);
            smt.setString(3, cpf);
            set = smt.executeQuery();
            if (set.next()) {
                encontrou = true;
            }
        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar validar a existência dos dados do Médico ao cadastrar.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }

        return encontrou;
    }

    /**
     * <b>cadastrarMedico</b>
     * <p>
     * Método responsável por cadastrar os dados do novo Médico no banco de
     * dados do sistema.</p>
     * <p>
     * Caso o cadastro não seja efetuado uma exceção será lançada.</p>
     *
     * @param medico Objeto contendo os dados do novo Médico que será cadastrado
     * no sistema.
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws SQLException
     */
    @Override
    public boolean verificarExistencia(int codigo) throws ErroDAOException, ErroConectarException, SQLException {
        boolean existe = false;

        try {
            conexao = Conectar.getConexao();
            sql = GerarQueries.gerarQueryMedico("Verificar Existência");
            smt = conexao.prepareStatement(sql);
            smt.setInt(1, codigo);
            set = smt.executeQuery();
            if (set.next()) {
                existe = true;
            }

        } catch (SQLException ex) {
            throw new ErroDAOException("Erro ao tentar verificar a existência dos dados do Médico no sistema.\nDetalhes Técnicos:\n\n" + ex.getMessage());
        } finally {
            Conectar.close(conexao, smt, set);
        }

        return existe;
    }

}
